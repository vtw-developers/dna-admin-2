package com.vtw.dna.common.menu.pageinfo;

import lombok.Data;

@Data
public class PageInfo {
    private Long id;
    private String name;
    private String path;
    private Long roleId;
    private String roleName;
}
