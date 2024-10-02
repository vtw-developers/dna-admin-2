package com.vtw.dna.integration.manage.datasource.dto;

import com.vtw.dna.common.author.AuditQuery;
import lombok.Data;

@Data
public class DatasourceQuery extends AuditQuery {
    private Long id;
    private String name;
    private String database;
    private String url;
    private String username;
    private String password;
}
