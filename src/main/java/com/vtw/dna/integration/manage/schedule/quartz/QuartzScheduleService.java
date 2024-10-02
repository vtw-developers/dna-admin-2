package com.vtw.dna.integration.manage.schedule.quartz;

import com.vtw.dna.schedule.FlowScheduleJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.DirectSchedulerFactory;
import org.quartz.impl.jdbcjobstore.JobStoreTX;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.quartz.simpl.SimpleThreadPool;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.quartz.TriggerBuilder.newTrigger;

@Slf4j
@RequiredArgsConstructor
@Service
public class QuartzScheduleService {

    private final ApplicationService applicationService;
    private final QuartzProperties quartzProperties;

    public List<ScheduleView> findAll() throws Exception {
        List<Application> apps = applicationService.findAll();

        List<ScheduleView> schedules = new ArrayList<>();
        for (Application app : apps) {
            Scheduler scheduler = createScheduler(app.getId());
            List<String> triggerGroupNames = scheduler.getTriggerGroupNames();
            for (String groupName : triggerGroupNames) {
                for (TriggerKey triggerKey : scheduler.getTriggerKeys(GroupMatcher.triggerGroupEquals(groupName))) {
                    String triggerName = triggerKey.getName();
                    CronTriggerImpl trigger = (CronTriggerImpl) scheduler.getTrigger(triggerKey);

                    String appId = app.getId();
                    String flowId = triggerName;
                    String cronExpr = trigger.getCronExpression();
                    Trigger.TriggerState triggerState = scheduler.getTriggerState(triggerKey);
                    String status = triggerState.name();

                    ScheduleView schedule = ScheduleView.builder().appId(appId).flowId(flowId).cronExpr(cronExpr).status(status).build();
                    schedules.add(schedule);
                }
            }
        }
        return schedules;
    }

    public void create(String app, String flow, String cronExpr) throws Exception {
        Scheduler scheduler = createScheduler(app);

        JobDetail jobDetail = JobBuilder
                .newJob(FlowScheduleJob.class)
                .withIdentity(flow)
                .storeDurably()
                .build();
        scheduler.addJob(jobDetail, true);

        String triggerName = flow;
        Trigger trigger = newTrigger()
                .forJob(flow)
                .withIdentity(triggerName)
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpr).withMisfireHandlingInstructionDoNothing())
                .build();

        scheduler.scheduleJob(trigger);
        scheduler.pauseTrigger(trigger.getKey());
    }

    public void runOnce(String app, String flow) throws Exception {
        Scheduler scheduler = createScheduler(app);

        JobDetail jobDetail = JobBuilder
                .newJob(FlowScheduleJob.class)
                .withIdentity(flow)
                .storeDurably()
                .build();
        scheduler.addJob(jobDetail, true);

        String triggerName = flow;
        SimpleTrigger trigger = (SimpleTrigger) newTrigger()
                .forJob(flow)
                .withIdentity(triggerName, "Simple")
                .build();

        scheduler.scheduleJob(trigger);
    }

    public void update(String app, String flow, String cronExpr) throws Exception {
        Scheduler scheduler = createScheduler(app);
        TriggerKey triggerKey = new TriggerKey(flow);
        CronTriggerImpl trigger = (CronTriggerImpl) scheduler.getTrigger(triggerKey);
        trigger.setCronExpression(cronExpr);
        scheduler.rescheduleJob(triggerKey, trigger);
        scheduler.pauseTrigger(trigger.getKey());
    }

    public void delete(String app, String flow) throws Exception {
        Scheduler scheduler = createScheduler(app);
        scheduler.deleteJob(JobKey.jobKey(flow));
    }

    public void start(String app, String flow) throws Exception {
        Scheduler scheduler = createScheduler(app);
        TriggerKey triggerKey = TriggerKey.triggerKey(flow);
        scheduler.resumeTrigger(triggerKey);
    }

    public void stop(String app, String flow) throws Exception {
        Scheduler scheduler = createScheduler(app);
        TriggerKey triggerKey = TriggerKey.triggerKey(flow);
        scheduler.pauseTrigger(triggerKey);
    }

    private Scheduler createScheduler(String schedulerName) throws Exception {
        JobStoreTX jobStore = new JobStoreTX();
        jobStore.setDriverDelegateClass(quartzProperties.getProperties().get("org.quartz.jobStore.driverDelegateClass"));
        jobStore.setUseProperties(quartzProperties.getProperties().get("org.quartz.jobStore.useProperties"));
        jobStore.setTablePrefix(quartzProperties.getProperties().get("org.quartz.jobStore.tablePrefix"));
        jobStore.setMisfireThreshold(Long.parseLong(quartzProperties.getProperties().get("org.quartz.jobStore.misfireThreshold")));
        jobStore.setClusterCheckinInterval(Long.parseLong(quartzProperties.getProperties().get("org.quartz.jobStore.clusterCheckinInterval")));
        jobStore.setIsClustered(Boolean.parseBoolean(quartzProperties.getProperties().get("org.quartz.jobStore.isClustered")));
        jobStore.setDataSource(quartzProperties.getProperties().get("org.quartz.jobStore.dataSource"));

        SimpleThreadPool simpleThreadPool = new SimpleThreadPool();
        simpleThreadPool.setThreadCount(Integer.parseInt(quartzProperties.getProperties().get("org.quartz.threadPool.threadCount")));
        simpleThreadPool.setThreadPriority(Integer.parseInt(quartzProperties.getProperties().get("org.quartz.threadPool.threadPriority")));

        Scheduler scheduler = DirectSchedulerFactory.getInstance().getScheduler(schedulerName);
        if (scheduler == null) {
            DirectSchedulerFactory.getInstance().createScheduler(schedulerName, "DnaAdminRemoteScheduler", simpleThreadPool, jobStore);
            scheduler = DirectSchedulerFactory.getInstance().getScheduler(schedulerName);
        }
        return scheduler;
    }
}
