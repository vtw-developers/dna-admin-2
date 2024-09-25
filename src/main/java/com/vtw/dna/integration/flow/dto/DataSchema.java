package com.vtw.dna.integration.flow.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@JsonPropertyOrder({"type", "description", "example", "properties", "items"})
@Data
public class DataSchema {
    private String type;
    private Map<String, DataSchema> properties;
    private DataSchema items;
    private String description;
    private String example;

    public DataSchemaView convert() {
        return convert(null, 1);
    }

    public DataSchemaView convert(String key, int depth) {
        DataSchemaView schemaView = new DataSchemaView();
        schemaView.setId(UUID.randomUUID().toString());
        schemaView.setType(StringUtils.capitalize(type));
        schemaView.setDescription(description);
        schemaView.setName(key);
        if (key == null) {
            schemaView.setName("root");
        }
        schemaView.setDepth(depth);
        depth++;

        if (type.equals("object")) {
            List children = new ArrayList();
            schemaView.setChildren(children);

            for (String key2 : properties.keySet()) {
                DataSchema v = properties.get(key2);
                DataSchemaView child = v.convert(key2, depth);
                children.add(child);
            }
        }

        if (type.equals("array")) {
            List children = new ArrayList();
            schemaView.setChildren(children);
            schemaView.setArrayType(StringUtils.capitalize(items.getType()));

            if (items.getType().equals("object")) {
                for (String key2 : items.getProperties().keySet()) {
                    DataSchema v = items.getProperties().get(key2);
                    DataSchemaView child = v.convert(key2, depth);
                    children.add(child);
                }
            }
        }

        return schemaView;
    }

    public static void main(String[] args) throws JsonProcessingException {
        String schemaJson = """
                type: object
                properties:
                  boxOfficeResult:
                    type: object
                    properties:
                      boxofficeType:
                        type: string
                        description: 박스오피스 종류
                        example: 일별 박스오피스
                      showRange:
                        type: string
                      dailyBoxOfficeList:
                        type: array
                        items:
                          type: object
                          properties:
                            rnum:
                              type: string
                              description: 순번
                              example: 1
                            rank:
                              type: string
                              description: 해당일자의 박스오피스 순위
                              example: 1
                            rankInten:
                              type: string
                            rankOldAndNew:
                              type: string
                            movieCd:
                              type: string
                            movieNm:
                              type: string
                            openDt:
                              type: string
                            salesAmt:
                              type: string
                            salesShare:
                              type: string
                            salesInten:
                              type: string
                            salesChange:
                              type: string
                            salesAcc:
                              type: string
                            audiCnt:
                              type: string
                            audiInten:
                              type: string
                            audiChange:
                              type: string
                            audiAcc:
                              type: string
                            scrnCnt:
                              type: string
                            showCnt:
                              type: string
                """;

        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        DataSchema dataSchema = objectMapper.readValue(schemaJson, DataSchema.class);

        DataSchemaView view = dataSchema.convert();
        System.out.println(view);
    }
}
