package com.vtw.dna.integration.manage.schedule.quartz;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ScheduleView {

    private String appId;
    private String flowId;
    private String cronExpr;
    private String status;

}
