package com.vtw.dna.integration.flow.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
public class ParameterValue {
    private String name;
    private Object value;

    public static Map<String, Object> convert(List<ParameterValue> values) {
        final Map<String, Object> map = new LinkedHashMap<>();
        for (ParameterValue value : values) {
            map.put(value.getName(), value.getValue());
        }
        return map;
    }
}
