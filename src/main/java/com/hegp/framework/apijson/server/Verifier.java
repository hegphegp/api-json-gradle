package com.hegp.framework.apijson.server;

import com.hegp.framework.apijson.JSONObject;
import com.hegp.framework.apijson.NotNull;
import com.hegp.framework.apijson.RequestMethod;
import com.hegp.framework.apijson.RequestRole;

/**权限验证器
 * @author Lemon
 */
public interface Verifier<T> {

	/**验证权限是否通过
	 * @param config
	 * @return
	 * @throws Exception
	 */
	boolean verify(SQLConfig config) throws Exception;

	/**允许请求，角色不好判断，让访问者发过来角色名，OWNER,CONTACT,ADMIN等
	 * @param table
	 * @param method
	 * @param role
	 * @return
	 * @throws Exception 
	 * @see {@link JSONObject#KEY_ROLE}
	 */
	void verifyRole(String table, RequestMethod method, RequestRole role) throws Exception;

	/**登录校验
	 * @throws Exception
	 */
	void verifyLogin() throws Exception;
	/**管理员角色校验
	 * @throws Exception
	 */
	void verifyAdmin() throws Exception;



	/**验证是否重复
	 * @param table
	 * @param key
	 * @param value
	 * @throws Exception
	 */
	void verifyRepeat(String table, String key, Object value) throws Exception;
	
	/**验证是否重复
	 * @param table
	 * @param key
	 * @param value
	 * @param exceptId 不包含id
	 * @throws Exception
	 */
	void verifyRepeat(String table, String key, Object value, long exceptId) throws Exception;
	

	@NotNull
	Parser<T> createParser();
	

	@NotNull
	Visitor<T> getVisitor();
	Verifier<T> setVisitor(@NotNull Visitor<T> visitor);

	
	String getVisitorIdKey(SQLConfig config);

}
