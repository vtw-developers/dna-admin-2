package com.vtw.dna.common.mybatis.typehandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vtw.dna.integration.manage.api.dto.RequestParameter;
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
public class RequestParametersJsonTypeHandler extends BaseTypeHandler<List<RequestParameter>> {


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<RequestParameter> list, JdbcType jdbcType) throws SQLException {
        ps.setString(i, toJson(list));
    }

    @Override
    public List<RequestParameter> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return fromJson(rs.getString(columnName));
    }

    @Override
    public List<RequestParameter> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return fromJson(rs.getString(columnIndex));
    }

    @Override
    public List<RequestParameter> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return fromJson(cs.getString(columnIndex));
    }

    private String toJson(List<RequestParameter> requestParameters) {
        if (requestParameters == null) {
            return null;
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(requestParameters);
            return json;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private List<RequestParameter> fromJson(String json) {
        if (StringUtils.isEmpty(json)) {
            return new ArrayList<>();
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<RequestParameter> list = objectMapper.readValue(json, new TypeReference<List<RequestParameter>>() {
            });
            return list;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
