package com.vtw.dna.common.menu.pageinfo;

import lombok.Data;

@Data
public class PageInfo {
    private Long id;
    private String name;
    private String path;
    private Long readRoleId;
    private String readRoleName;
    private Long writeRoleId;
    private String writeRoleName;
}
