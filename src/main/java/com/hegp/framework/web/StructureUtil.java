package com.hegp.framework.web;

import com.alibaba.fastjson.JSONObject;

import com.hegp.framework.apijson.JSON;
import com.hegp.framework.apijson.Log;
import com.hegp.framework.apijson.RequestMethod;
import com.hegp.framework.apijson.server.SQLConfig;
import com.hegp.framework.apijson.server.SQLCreator;
import com.hegp.framework.apijson.server.SQLExecutor;
import com.hegp.framework.apijson.server.Structure;


/**结构校验
 * @author Lemon
 */
public class StructureUtil {
	private static final String TAG = "Structure";


	static final String requestString = "{\"Comment\":{\"DISALLOW\": \"id\", \"NECESSARY\": \"userId,momentId,content\"}, \"ADD\":{\"Comment:to\":{}}}";
	static final String responseString = "{\"User\":{\"REMOVE\": \"phone\", \"REPLACE\":{\"sex\":2}, \"ADD\":{\"name\":\"api\"}}, \"PUT\":{\"Comment:to\":{}}}";
	/**测试
	 * @throws Exception
	 */
	public static void test() throws Exception {

		SQLCreator creator = new SQLCreator() {
			
			@Override
			public SQLConfig createSQLConfig() {
				return new DemoSQLConfig();
			}
			
			@Override
			public SQLExecutor createSQLExecutor() {
				return new DemoSQLExecutor();
			}
		};

		try {
			String result = new DemoParser(RequestMethod.GET).parse("{ \"[]\": { \"page\":0, \"count\":100, \"User\": { \"sex\":0 } } }");
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}





//		try {
//			request = JSON.parseObject("{\"Comment\":{\"userId\":82001}}");
//			Log.d(TAG, "test  parseRequest = " + Structure.parseRequest(RequestMethod.POST, "", JSON.parseObject(requestString), request, creator));
//			System.out.println("0000000000000000000");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

//		try {
//			request = JSON.parseObject("{\"Comment\":{\"userId\":0}}");
//			Log.d(TAG, "test  parseRequest = " + Structure.parseRequest(RequestMethod.POST, "", JSON.parseObject(requestString), request, creator));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		try {
//			request = JSON.parseObject("{\"Comment\":{\"userId\":0, \"momentId\":0, \"content\":\"apijson\"}}");
//			Log.d(TAG, "test  parseRequest = " + Structure.parseRequest(RequestMethod.POST, "", JSON.parseObject(requestString), request, creator));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		try {
//			request = JSON.parseObject("{\"Comment\":{\"id\":0, \"userId\":0, \"momentId\":0, \"content\":\"apijson\"}}");
//			Log.d(TAG, "test  parseRequest = " + Structure.parseRequest(RequestMethod.POST, "", JSON.parseObject(requestString), request, creator));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//
//		JSONObject response;
//		try {
//			response = JSON.parseObject("{\"User\":{\"userId\":0}}");
//			Log.d(TAG, "test  parseResponse = " + Structure.parseResponse(RequestMethod.GET, "", JSON.parseObject(responseString), response, creator, null));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		try {
//			response = JSON.parseObject("{\"User\":{\"userId\":0, \"phone\":\"12345678\"}}");
//			Log.d(TAG, "test  parseResponse = " + Structure.parseResponse(RequestMethod.GET, "", JSON.parseObject(responseString), response, creator, null));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		try {
//			response = JSON.parseObject("{\"User\":{\"userId\":0, \"phone\":\"12345678\", \"sex\":1}}");
//			Log.d(TAG, "test  parseResponse = " + Structure.parseResponse(RequestMethod.GET, "", JSON.parseObject(responseString), response, creator, null));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		try {
//			response = JSON.parseObject("{\"User\":{\"id\":0, \"name\":\"tommy\", \"phone\":\"12345678\", \"sex\":1}}");
//			Log.d(TAG, "test  parseResponse = " + Structure.parseResponse(RequestMethod.GET, "", JSON.parseObject(responseString), response,creator, null));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}


	
	
}
