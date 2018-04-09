package com.enterprise.testdomain;

import com.enterprise.data.type.DBEnum;

/**
 * @author tommy
 */
public enum UserTypeEnum implements DBEnum {
    ADMIN(1, "管理员"),

    CUSTOME(2, "客服"),

    USER(3, "用户");

    private int type;
    private String name;

    UserTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public int getTypeValue() {
        return 0;
    }
}
