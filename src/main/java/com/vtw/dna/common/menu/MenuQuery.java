package com.vtw.dna.common.menu;

import lombok.Data;

@Data
public class MenuQuery {
    private String menuId;
    private String name;
    private String type;
    private String icon;
    private String upperMenuId;
    private String pageInfoName;
    private String pageInfoPath;
    private Long pageInfoId;
    private Long index;
}
