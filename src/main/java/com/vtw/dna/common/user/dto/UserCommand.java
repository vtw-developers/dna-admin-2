package com.vtw.dna.common.user.dto;

import lombok.Data;

@Data
public class UserCommand {
    private String id;
    private String name;
    private Long roleId;
}
