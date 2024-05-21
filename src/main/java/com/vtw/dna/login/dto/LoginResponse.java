package com.vtw.dna.login.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginResponse {
    @JsonProperty("jToken")
    private String jToken;

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;
}
