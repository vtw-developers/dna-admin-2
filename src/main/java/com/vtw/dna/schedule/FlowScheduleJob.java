package com.vtw.dna.schedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@DisallowConcurrentExecution
@Component
public class FlowScheduleJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        // Admin은 중앙 제어 역할이라 Job을 실행할 일이 없음
        // Job Class 등록을 위함
    }
}
