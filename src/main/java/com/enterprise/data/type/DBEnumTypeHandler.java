package com.enterprise.data.type;


import com.enterprise.data.exception.DBEnumConvertRuntimeException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author tommy
 */
public class DBEnumTypeHandler extends BaseTypeHandler<DBEnum> {
    private Class<DBEnum> type;

    public DBEnumTypeHandler() {
    }

    public DBEnumTypeHandler(Class<DBEnum> type) {
        if (type == null) throw new IllegalArgumentException("Type argument cannot be null");
        this.type = type;
    }

    @Override
    public DBEnum getNullableResult(ResultSet rs, String name) throws SQLException {
        return convert(rs.getInt(name));
    }

    @Override
    public DBEnum getNullableResult(ResultSet rs, int i) throws SQLException {
        return convert(rs.getInt(i));
    }

    @Override
    public DBEnum getNullableResult(CallableStatement cs, int i)
            throws SQLException {
        return convert(cs.getInt(i));
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, DBEnum enumObj,
                                    JdbcType type) throws SQLException {
        ps.setInt(i, enumObj.getTypeValue());
    }

    private DBEnum convert(int status) {
        DBEnum[] objs = type.getEnumConstants();
        for (DBEnum em : objs) {
            if (em.getTypeValue() == status) {
                return em;
            }
        }
        if (status == 0) {
            return null;
        }
        throw new DBEnumConvertRuntimeException(new StringBuffer(type.getSimpleName()).append(": The value ").append(status).append(" is not mapper ENUM").toString());
    }
}
