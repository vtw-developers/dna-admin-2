package com.vtw.dna.common.mybatis.typehandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vtw.dna.apiinfo.dto.RequestParameter;
import com.vtw.dna.apiinfo.dto.ResponseElements;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//@MappedTypes(Boolean.class)
//@MappedJdbcTypes(JdbcType.CHAR)
public class ResponseElementsJsonTypeHandler extends BaseTypeHandler<List<ResponseElements>> {


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<ResponseElements> list, JdbcType jdbcType) throws SQLException {
        ps.setString(i, toJson(list));
    }

    @Override
    public List<ResponseElements> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return fromJson(rs.getString(columnName));
    }

    @Override
    public List<ResponseElements> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return fromJson(rs.getString(columnIndex));
    }

    @Override
    public List<ResponseElements> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return fromJson(cs.getString(columnIndex));
    }

    private String toJson(List<ResponseElements> requestParameters) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(requestParameters);
            return json;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private List<ResponseElements> fromJson(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<ResponseElements> list = objectMapper.readValue(json, new TypeReference<List<ResponseElements>>() {
            });
            return list;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
