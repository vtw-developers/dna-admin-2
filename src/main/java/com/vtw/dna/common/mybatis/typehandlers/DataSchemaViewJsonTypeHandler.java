package com.vtw.dna.common.mybatis.typehandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vtw.dna.integration.flow.dto.DataSchemaView;
import com.vtw.dna.integration.flow.dto.TemplateParameter;
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
public class DataSchemaViewJsonTypeHandler extends BaseTypeHandler<DataSchemaView> {


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, DataSchemaView object, JdbcType jdbcType) throws SQLException {
        ps.setString(i, toJson(object));
    }

    @Override
    public DataSchemaView getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return fromJson(rs.getString(columnName));
    }

    @Override
    public DataSchemaView getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return fromJson(rs.getString(columnIndex));
    }

    @Override
    public DataSchemaView getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return fromJson(cs.getString(columnIndex));
    }

    private String toJson(DataSchemaView object) {
        if (object == null) {
            return null;
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(object);
            return json;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private DataSchemaView fromJson(String json) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            DataSchemaView object = objectMapper.readValue(json, DataSchemaView.class);
            return object;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
