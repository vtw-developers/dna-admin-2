package com.vtw.dna.apiinfo;

import lombok.Data;

@Data
public class ApiInfoFilter {
    private String name;
    private HttpMethod httpMethod;
    private String author;
}
