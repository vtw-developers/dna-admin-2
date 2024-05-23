package com.vtw.dna.apiinfo;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class HttpMethodTypeHandler implements TypeHandler<HttpMethod> {

   public void setParameter(PreparedStatement ps, int paramInt, HttpMethod paramType, JdbcType jdbctype)
         throws SQLException {
      ps.setString(paramInt, paramType.name());
   }

   @Override
   public HttpMethod getResult(ResultSet rs, String param) throws SQLException {
      return HttpMethod.valueOf(rs.getString(param));
   }

   @Override
   public HttpMethod getResult(ResultSet rs, int columnIndex) throws SQLException {
      return HttpMethod.valueOf(rs.getString(columnIndex));
   }

   @Override
   public HttpMethod getResult(CallableStatement cs, int columnIndex) throws SQLException {
      return HttpMethod.valueOf(cs.getString(columnIndex));
   }
}
