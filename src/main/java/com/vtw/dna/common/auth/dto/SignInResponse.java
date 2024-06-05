package com.vtw.dna.common.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SignInResponse {
    @JsonProperty("token")
    private String token;

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;
}
