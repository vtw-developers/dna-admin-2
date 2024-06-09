package com.vtw.dna.common.exception;

import java.util.NoSuchElementException;

public class NoSuchEntityException extends NoSuchElementException {

    private final String entityName;
    private final Long entityId;

    public NoSuchEntityException(String entityName, Long entityId) {
        super();
        this.entityName = entityName;
        this.entityId = entityId;
    }

    public String getEntityName() {
        return entityName;
    }

    public Long getEntityId() {
        return entityId;
    }
}
