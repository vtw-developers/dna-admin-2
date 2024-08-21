package com.vtw.dna.common.menu.pageinfo;

import lombok.Data;

@Data
public class PageInfoFilter {
    private String name;
    private String path;
    private Long readRoleId;
    private Long writeRoleId;
}
