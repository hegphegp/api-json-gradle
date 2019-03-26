package com.hegp.framework.web;

import static com.hegp.framework.apijson.JSONObject.KEY_ID;
import static com.hegp.framework.apijson.JSONObject.KEY_USER_ID;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

import com.hegp.framework.web.model.Privacy;
import com.hegp.framework.web.model.User;
import com.hegp.framework.apijson.RequestMethod;
import com.hegp.framework.apijson.server.AbstractSQLConfig;
import com.hegp.framework.apijson.server.Join;
import com.hegp.framework.apijson.server.SQLConfig;

/** SQL配置 */
public class DemoSQLConfig extends AbstractSQLConfig {

	public static final Callback SIMPLE_CALLBACK;

	static {
		//默认模式名
		DEFAULT_SCHEMA = "sys";
		
		//表名映射，隐藏真实表名，对安全要求很高的表可以这么做
		TABLE_KEY_MAP.put(User.class.getSimpleName(), "apijson_user");
		TABLE_KEY_MAP.put(Privacy.class.getSimpleName(), "apijson_privacy");

		//主键名映射
		SIMPLE_CALLBACK = new SimpleCallback() {

			@Override
			public DemoSQLConfig getSQLConfig(RequestMethod method, String table) {
				return new DemoSQLConfig(method, table);
			}

			@Override
			public String getUserIdKey(String schema, String table) {
				return Controller.USER_.equals(table) || Controller.PRIVACY_.equals(table) ? KEY_ID : KEY_USER_ID; // id / userId
			}

		};
	}

	// 重写AbstractSQLConfig父类的getDatabase()方法，指定数据库类型
	@Override
	public String getDatabase() {
		return "MYSQL";
//		return "POSTGRESQL";
	}

	@Override
	public String getDBUri() {
		return DATABASE_POSTGRESQL.equalsIgnoreCase(getDatabase()) ? "jdbc:postgresql://localhost:5432/postgres" : "jdbc:mysql://localhost:3306";
	}

	@Override
	public String getDBAccount() {
		return DATABASE_POSTGRESQL.equalsIgnoreCase(getDatabase()) ? "postgres" : "root"; //TODO 改成你自己的
	}

	@Override
	public String getDBPassword() {
		return DATABASE_POSTGRESQL.equalsIgnoreCase(getDatabase()) ? null : "root"; //TODO 改成你自己的，TiDB 默认密码为空字符串 ""
	}

	@Override
	public String getIdKey() {
		return SIMPLE_CALLBACK.getIdKey(getSchema(), getTable());
	}

	@Override
	public String getUserIdKey() {
		return SIMPLE_CALLBACK.getUserIdKey(getSchema(), getTable());
	}

	public DemoSQLConfig() {
		this(RequestMethod.GET);
	}
	public DemoSQLConfig(RequestMethod method) {
		super(method);
	}
	public DemoSQLConfig(RequestMethod method, String table) {
		super(method, table);
	}
	public DemoSQLConfig(RequestMethod method, int count, int page) {
		super(method, count, page);
	}

	/** 获取SQL配置 */
	public static SQLConfig newSQLConfig(RequestMethod method, String table, JSONObject request, List<Join> joinList) throws Exception {
		return newSQLConfig(method, table, request, joinList, SIMPLE_CALLBACK);
	}
}