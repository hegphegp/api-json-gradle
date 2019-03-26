package com.hegp.framework.apijson.server;

import com.hegp.framework.apijson.NotNull;

/**
 * SQL相关创建器
 *
 * @author Lemon
 */
public interface SQLCreator {

    @NotNull
    SQLConfig createSQLConfig();

    @NotNull
    SQLExecutor createSQLExecutor();
}
