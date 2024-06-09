package com.vtw.dna.common.menu;

import lombok.Data;

@Data
public class Menu {
    private Long id;
    private String menuId;
    private String name;
    private String type;
    private String icon;
    private String upperMenuId;
    private Long pageInfoId;
    private Long index;
}
