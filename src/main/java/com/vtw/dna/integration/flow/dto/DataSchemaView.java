package com.vtw.dna.integration.flow.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import lombok.Data;

import java.util.*;

@Data
public class DataSchemaView {
    private String id;
    private String name;
    private String type;
    private String arrayType;
    private String description;
    private int depth;
    private List<DataSchemaView> children = new ArrayList<>();

    public DataSchema convert() {
        DataSchema dataSchema = new DataSchema();
        dataSchema.setType(type.toLowerCase());
        dataSchema.setDescription(description);

        if (type.equals("Object")) {
            Map<String, DataSchema> properties = new LinkedHashMap<>();
            dataSchema.setProperties(properties);

            for (DataSchemaView child : children) {
                DataSchema schema = child.convert();
                properties.put(child.getName(), schema);
            }
        }

        if (type.equals("Array")) {
            DataSchema dataSchema2 = new DataSchema();
            dataSchema2.setType(arrayType.toLowerCase());
            dataSchema.setItems(dataSchema2);
            if (arrayType.equals("Object")) {
                Map<String, DataSchema> properties2 = new LinkedHashMap<>();
                dataSchema2.setProperties(properties2);

                for (DataSchemaView child : children) {
                    DataSchema schema = child.convert();
                    properties2.put(child.getName(), schema);
                }
            }
        }

        return dataSchema;
    }

    public static void main(String[] args) throws JsonProcessingException {
        DataSchemaView dataSchemaView = new DataSchemaView();
        dataSchemaView.setId("1");
        dataSchemaView.setName("name");
        dataSchemaView.setType("Object");
        dataSchemaView.setDescription("description");
        List<DataSchemaView> children = new ArrayList<>();
        dataSchemaView.setChildren(children);

        DataSchemaView dataSchemaView2 = new DataSchemaView();
        dataSchemaView2.setId("2");
        dataSchemaView2.setName("gogogo");
        dataSchemaView2.setType("String");
        dataSchemaView2.setDescription("description");
        children.add(dataSchemaView2);

        DataSchema convert = dataSchemaView.convert();
        System.out.println(convert.toString());

        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory().enable(YAMLGenerator.Feature.MINIMIZE_QUOTES));
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String s = objectMapper.writeValueAsString(convert);
        System.out.println(s);
    }
}
