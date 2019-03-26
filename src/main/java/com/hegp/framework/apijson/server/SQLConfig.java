package com.hegp.framework.apijson.server;

import java.util.List;
import java.util.Map;

import com.hegp.framework.apijson.NotNull;
import com.hegp.framework.apijson.RequestMethod;
import com.hegp.framework.apijson.RequestRole;

/**
 * SQL配置
 *
 * @author Lemon
 */
public interface SQLConfig {

    String DATABASE_MYSQL = "MYSQL";
    String DATABASE_POSTGRESQL = "POSTGRESQL";

    String SCHEMA_INFORMATION = "information_schema";
    String TABLE_SCHEMA = "table_schema";
    String TABLE_NAME = "table_name";

    int TYPE_CHILD = 0;
    int TYPE_ITEM = 1;
    int TYPE_ITEM_CHILD_0 = 2;

    @NotNull
    String getIdKey();

    @NotNull
    String getUserIdKey();


    /**
     * 获取数据库地址
     *
     * @return
     */
    String getDBUri();

    /**
     * 获取数据库账号
     *
     * @return
     */
    String getDBAccount();

    /**
     * 获取数据库密码
     *
     * @return
     */
    String getDBPassword();

    /**
     * 获取SQL语句
     *
     * @return
     * @throws Exception
     */
    String getSQL(boolean prepared) throws Exception;


    boolean isTest();

    SQLConfig setTest(boolean test);

    boolean isCacheStatic();

    SQLConfig setCacheStatic(boolean cacheStatic);


    int getType();

    SQLConfig setType(int type);

    int getCount();

    SQLConfig setCount(int count);

    int getPage();

    SQLConfig setPage(int page);

    int getQuery();

    SQLConfig setQuery(int query);

    int getPosition();

    SQLConfig setPosition(int position);


    RequestMethod getMethod();

    SQLConfig setMethod(RequestMethod method);

    Object getId();

    SQLConfig setId(Object id);

    RequestRole getRole();

    SQLConfig setRole(RequestRole role);

    String getDatabase();

    SQLConfig setDatabase(String database);

    String getQuote();

    String getSchema();

    SQLConfig setSchema(String schema);

    /**
     * 请求传进来的Table名
     *
     * @return
     * @see {@link #getSQLTable()}
     */
    String getTable();

    /**
     * 数据库里的真实Table名
     * 通过 {@link #TABLE_KEY_MAP} 映射
     *
     * @return
     */
    String getSQLTable();

    String getTablePath();

    SQLConfig setTable(String table);

    String getGroup();

    SQLConfig setGroup(String group);

    String getHaving();

    SQLConfig setHaving(String having);

    String getOrder();

    SQLConfig setOrder(String order);

    Subquery getFrom();

    SQLConfig setFrom(Subquery from);

    List<String> getColumn();

    SQLConfig setColumn(List<String> column);

    List<List<Object>> getValues();

    SQLConfig setValues(List<List<Object>> values);

    Map<String, Object> getContent();

    SQLConfig setContent(Map<String, Object> content);


    Map<String, Object> getWhere();

    SQLConfig setWhere(Map<String, Object> where);

    Map<String, List<String>> getCombine();

    SQLConfig setCombine(Map<String, List<String>> combine);


    /**
     * exactMatch = false
     *
     * @param key
     * @return
     */
    Object getWhere(String key);

    /**
     * @param key
     * @param exactMatch
     * @return
     */
    Object getWhere(String key, boolean exactMatch);

    /**
     * @param key
     * @param value
     * @return
     */
    SQLConfig putWhere(String key, Object value, boolean prior);


    boolean isPrepared();

    SQLConfig setPrepared(boolean prepared);

    boolean isMain();

    SQLConfig setMain(boolean main);


    List<Object> getPreparedValueList();

    SQLConfig setPreparedValueList(List<Object> preparedValueList);


    String getAlias();

    SQLConfig setAlias(String alias);

    String getWhereString(boolean hasPrefix) throws Exception;

    boolean isKeyPrefix();

    SQLConfig setKeyPrefix(boolean keyPrefix);


    List<Join> getJoinList();

    SQLConfig setJoinList(List<Join> joinList);

    boolean hasJoin();


    String getSubqueryString(Subquery subquery) throws Exception;


}
