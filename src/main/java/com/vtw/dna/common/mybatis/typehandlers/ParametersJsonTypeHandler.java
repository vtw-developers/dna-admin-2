package com.vtw.dna.common.mybatis.typehandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vtw.dna.integration.flow.dto.Parameter;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//@MappedTypes(Boolean.class)
//@MappedJdbcTypes(JdbcType.CHAR)
public class ParametersJsonTypeHandler extends BaseTypeHandler<List<Parameter>> {


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<Parameter> list, JdbcType jdbcType) throws SQLException {
        ps.setString(i, toJson(list));
    }

    @Override
    public List<Parameter> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return fromJson(rs.getString(columnName));
    }

    @Override
    public List<Parameter> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return fromJson(rs.getString(columnIndex));
    }

    @Override
    public List<Parameter> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return fromJson(cs.getString(columnIndex));
    }

    private String toJson(List<Parameter> Parameters) {
        if (Parameters == null) {
            return null;
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(Parameters);
            return json;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Parameter> fromJson(String json) {
        if (StringUtils.isEmpty(json)) {
            return new ArrayList<>();
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Parameter> list = objectMapper.readValue(json, new TypeReference<List<Parameter>>() {
            });
            return list;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
