package com.vtw.dna.common.auth.dto;

import lombok.Data;

@Data
public class AuthUser {
	private String id;
	private String password;
	private String name;
	private boolean approval;
}
