package com.vtw.dna.monitor.log;

public enum ServiceResult {
    SUCCESS("S"), ERROR("F");

    private String code;

    private ServiceResult(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
