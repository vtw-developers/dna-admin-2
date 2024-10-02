package com.vtw.dna.integration.shell;

import com.vtw.dna.integration.manage.schedule.quartz.QuartzScheduleService;
import com.vtw.dna.integration.manage.schedule.quartz.ScheduleView;
import de.vandermeer.asciitable.AsciiTable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.ObjectAlreadyExistsException;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@ShellComponent
public class ScheduleCommand {

    private final QuartzScheduleService scheduleService;

    @ShellMethod(key = "show schedules")
    public String showSchedules() throws Exception {
        List<ScheduleView> schedules = scheduleService.findAll();

        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("App", "Flow", "Cron Expr", "Status");
        at.addRule();

        for (ScheduleView schedule : schedules) {
            at.addRow(schedule.getAppId(), schedule.getFlowId(), schedule.getCronExpr(), schedule.getStatus());
            at.addRule();
        }

        return at.render();
    }

    @ShellMethod(key = "create schedule")
    public String createSchedule(
            @ShellOption String appId,
            @ShellOption String flowId,
            @ShellOption String cronExpr
    ) {
        try {
            scheduleService.create(appId, flowId, cronExpr);
            return "Flow [" + flowId + "] was scheduled successfully";
        } catch (NoSuchElementException elementEx) {
            if (elementEx.getMessage().equals("AppNotExists")) {
                return "[Error] App [" + appId + "] is not exists.";
            }
            return "[Error] Flow [" + flowId + "] is not exists.";
        } catch (ObjectAlreadyExistsException e) {
            return "[Error] Flow [" + flowId + "] was already scheduled.";
        } catch (Exception e) {
            log.error("[Error] Flow [" + flowId + "] cannot be scheduled.", e);
            return "[Error] Flow [" + flowId + "] cannot be scheduled. message: " + e.getMessage();
        }
    }

    @ShellMethod(key = "update schedule")
    public String updateSchedule(
            @ShellOption String appId,
            @ShellOption String flowId,
            @ShellOption String cronExpr
    ) {
        try {
            scheduleService.update(appId, flowId, cronExpr);
            return "Flow [" + flowId + "] Schedule was updated successfully";
        } catch (NoSuchElementException elementEx) {
            if (elementEx.getMessage().equals("AppNotExists")) {
                return "[Error] App [" + appId + "] is not exists.";
            }
            return "[Error] Flow [" + flowId + "] is not exists.";
        } catch (Exception e) {
            log.error("[Error] Flow [" + flowId + "] Schedule cannot be updated.", e);
            return "[Error] Flow [" + flowId + "] Schedule cannot be updated. message: " + e.getMessage();
        }
    }

    @ShellMethod(key = "delete schedule")
    public String deleteSchedule(
            @ShellOption String appId,
            @ShellOption String flowId
    ) {
        try {
            scheduleService.delete(appId, flowId);
            return "Flow [" + flowId + "] Schedule was deleted successfully";
        } catch (NoSuchElementException elementEx) {
            if (elementEx.getMessage().equals("AppNotExists")) {
                return "[Error] App [" + appId + "] is not exists.";
            }
            return "[Error] Flow [" + flowId + "] is not exists.";
        } catch (Exception e) {
            log.error("[Error] Flow [" + flowId + "] Schedule cannot be deleted.", e);
            return "[Error] Flow [" + flowId + "] Schedule cannot be deleted. message: " + e.getMessage();
        }
    }

    @ShellMethod(key = "start schedule")
    public String startSchedule(
            @ShellOption String appId,
            @ShellOption String flowId
    ) {
        try {
            scheduleService.start(appId, flowId);
            return "Flow [" + flowId + "] Schedule was started successfully";
        } catch (NoSuchElementException elementEx) {
            if (elementEx.getMessage().equals("AppNotExists")) {
                return "[Error] App [" + appId + "] is not exists.";
            }
            return "[Error] Flow [" + flowId + "] is not exists.";
        } catch (Exception e) {
            log.error("[Error] Flow [" + flowId + "] Schedule cannot be started.", e);
            return "[Error] Flow [" + flowId + "] Schedule cannot be started. message: " + e.getMessage();
        }
    }

    @ShellMethod(key = "stop schedule")
    public String stopSchedule(
            @ShellOption String appId,
            @ShellOption String flowId
    ) {
        try {
            scheduleService.stop(appId, flowId);
            return "Flow [" + flowId + "] Schedule was stopped successfully";
        } catch (NoSuchElementException elementEx) {
            if (elementEx.getMessage().equals("AppNotExists")) {
                return "[Error] App [" + appId + "] is not exists.";
            }
            return "[Error] Flow [" + flowId + "] is not exists.";
        } catch (Exception e) {
            log.error("[Error] Flow [" + flowId + "] Schedule cannot be stopped.", e);
            return "[Error] Flow [" + flowId + "] Schedule cannot be stopped. message: " + e.getMessage();
        }
    }

    @ShellMethod(key = "run flow")
    public String runFlow(
            @ShellOption String appId,
            @ShellOption String flowId
    ) {
        try {
            scheduleService.runOnce(appId, flowId);
            return "Flow [" + flowId + "] executed successfully";
        } catch (NoSuchElementException elementEx) {
            if (elementEx.getMessage().equals("AppNotExists")) {
                return "[Error] App [" + appId + "] is not exists.";
            }
            return "[Error] Flow [" + flowId + "] is not exists.";
        } catch (Exception e) {
            log.error("[Error] Flow [" + flowId + "] Schedule cannot be started.", e);
            return "[Error] Flow [" + flowId + "] Schedule cannot be started. message: " + e.getMessage();
        }
    }
}
