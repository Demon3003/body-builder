package com.zhurawell.base.data.model.dialect;

import org.hibernate.dialect.PostgreSQLDialect;

import java.sql.Types;

public class JsonPostgreSQLDialect extends PostgreSQLDialect {

    public JsonPostgreSQLDialect() {

        super();

        this.registerColumnType(Types.JAVA_OBJECT, "json");
    }
}