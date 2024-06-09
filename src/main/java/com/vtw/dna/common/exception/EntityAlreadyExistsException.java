package com.vtw.dna.common.exception;

public class EntityAlreadyExistsException extends RuntimeException {

    private String entity;
    private String field;
    private String value;

    public EntityAlreadyExistsException(String entity, String field, String value) {
        this.entity = entity;
        this.field = field;
        this.value = value;
    }

    public String getEntity() {
        return entity;
    }

    public String getField() {
        return field;
    }

    public String getValue() {
        return value;
    }
}
