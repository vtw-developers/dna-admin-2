package com.vtw.dna.common.mybatis.typehandlers;

import com.vtw.dna.monitor.log.ServiceResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//@MappedTypes(Boolean.class)
//@MappedJdbcTypes(JdbcType.CHAR)
public class ServiceResultToCodeTypeHandler extends BaseTypeHandler<ServiceResult> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, ServiceResult result, JdbcType jdbcType) throws SQLException {
        ps.setString(i, result.getCode());
    }

    @Override
    public ServiceResult getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return getServiceResult(rs.getString(columnName));
    }

    @Override
    public ServiceResult getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return getServiceResult(rs.getString(columnIndex));
    }

    @Override
    public ServiceResult getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getServiceResult(cs.getString(columnIndex));
    }

    private ServiceResult getServiceResult(String code) {
        if (StringUtils.isEmpty(code)) {return null;}
        return ServiceResult.getByCode(code);
    }
}
