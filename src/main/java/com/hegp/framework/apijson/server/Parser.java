package com.hegp.framework.apijson.server;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.hegp.framework.apijson.NotNull;
import com.hegp.framework.apijson.RequestMethod;

/**解析器
 * @author Lemon
 */
public interface Parser<T> {

	int DEFAULT_QUERY_COUNT = 10;
	int MAX_QUERY_COUNT = 100;
	int MAX_QUERY_PAGE = 100;
	int MAX_UPDATE_COUNT = 10;
	
	
	@NotNull
	Visitor<T> getVisitor();
	Parser<T> setVisitor(@NotNull Visitor<T> visitor);

	@NotNull
	RequestMethod getMethod();
	Parser<T> setMethod(@NotNull RequestMethod method);

	JSONObject getRequest();
	Parser<T> setRequest(JSONObject request);

	boolean isNoVerify();
	Parser<T> setNoVerify(boolean noVerify);

	boolean isNoVerifyLogin();
	Parser<T> setNoVerifyLogin(boolean noVerifyLogin);

	boolean isNoVerifyRole();
	Parser<T> setNoVerifyRole(boolean noVerifyRole);

	boolean isNoVerifyContent();
	Parser<T> setNoVerifyContent(boolean noVerifyContent);

	
	@NotNull
	Verifier<T> createVerifier();

	@NotNull
	SQLConfig createSQLConfig();

	@NotNull
	SQLExecutor createSQLExecutor();


	String parse(String request);
	String parse(JSONObject request);

	JSONObject parseResponse(String request);
	JSONObject parseResponse(JSONObject request);




	JSONObject parseCorrectRequest() throws Exception;
	
	JSONObject parseCorrectRequest(JSONObject target) throws Exception;

	JSONObject parseCorrectResponse(String table, JSONObject response) throws Exception;

	JSONObject getStructure(String table, String key, String value, int version) throws Exception;



	JSONObject onObjectParse(JSONObject request, String parentPath, String name, SQLConfig arrayConfig, boolean isSubquery) throws Exception;

	JSONArray onArrayParse(JSONObject request, String parentPath, String name, boolean isSubquery) throws Exception;

	/**解析远程函数
	 * @param object
	 * @param function
	 * @return
	 * @throws Exception
	 */
	Object onFunctionParse(JSONObject object, String function) throws Exception;
	
	ObjectParser createObjectParser(JSONObject request, String parentPath, String name, SQLConfig arrayConfig, boolean isSubquery) throws Exception;

	int getDefaultQueryCount();
	int getMaxQueryCount();
	int getMaxQueryPage();
	int getMaxUpdateCount();
	
	void putQueryResult(String path, Object result);


	Object getValueByPath(String valuePath);


	void onVerifyLogin() throws Exception;
	void onVerifyContent() throws Exception;
	void onVerifyRole(SQLConfig config) throws Exception;
	
	JSONObject executeSQL(SQLConfig config, boolean isSubquery) throws Exception;

}
