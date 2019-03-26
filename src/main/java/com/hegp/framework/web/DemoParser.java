package com.hegp.framework.web;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;

import com.hegp.framework.apijson.RequestMethod;
import com.hegp.framework.apijson.server.AbstractParser;
import com.hegp.framework.apijson.server.JSONRequest;
import com.hegp.framework.apijson.server.SQLConfig;

/**
 * 请求解析器
 */
public class DemoParser extends AbstractParser<Long> {

    public DemoParser() {
        super();
    }

    public DemoParser(RequestMethod method) {
        super(method);
    }

    public DemoParser(RequestMethod method, boolean noVerify) {
        super(method, noVerify);
    }

    protected HttpSession session;

    public HttpSession getSession() {
        return session;
    }

    public DemoParser setSession(HttpSession session) {
        this.session = session;
        return this;
    }

    @Override
    public DemoSQLConfig createSQLConfig() {
        return new DemoSQLConfig();
    }

    @Override
    public DemoSQLExecutor createSQLExecutor() {
        return new DemoSQLExecutor();
    }

    @Override
    public JSONObject parseResponse(JSONObject request) {
        //补充format
        if (session != null && request != null) {
            if (request.get(JSONRequest.KEY_FORMAT) == null) {
                request.put(JSONRequest.KEY_FORMAT, session.getAttribute(JSONRequest.KEY_FORMAT));
            }
            if (request.get(Controller.DEFAULTS) == null) {
                JSONObject defaults = (JSONObject) session.getAttribute(Controller.DEFAULTS);
                Set<Map.Entry<String, Object>> set = defaults == null ? null : defaults.entrySet();

                if (set != null) {
                    for (Map.Entry<String, Object> e : set) {
                        if (e != null && request.get(e.getKey()) == null) {
                            request.put(e.getKey(), e.getValue());
                        }
                    }
                }
            }
        }
        return super.parseResponse(request);
    }

    private DemoFunction function;

    @Override
    public Object onFunctionParse(JSONObject json, String fun) throws Exception {
        if (function == null) {
            function = new DemoFunction(requestMethod, session);
        }
        return function.invoke(fun, json);
    }

    @Override
    public DemoObjectParser createObjectParser(JSONObject request, String parentPath, String name, SQLConfig arrayConfig, boolean isSubquery) throws Exception {

        return new DemoObjectParser(session, request, parentPath, name, arrayConfig, isSubquery) {

            //TODO 删除，onPUTArrayParse改用MySQL函数JSON_ADD, JSON_REMOVE等
            @Override
            public JSONObject parseResponse(JSONRequest request) throws Exception {
                DemoParser demoParser = new DemoParser(RequestMethod.GET);
                demoParser.setSession(session);
                //						parser.setNoVerifyRequest(noVerifyRequest)
                demoParser.setNoVerifyLogin(noVerifyLogin);
                demoParser.setNoVerifyRole(noVerifyRole);
                return demoParser.parseResponse(request);
            }


            //			@Override
            //			protected DemoSQLConfig newQueryConfig() {
            //				if (itemConfig != null) {
            //					return itemConfig;
            //				}
            //				return super.newQueryConfig();
            //			}

            //导致最多评论的(Strong 30个)的那个动态详情界面Android(82001)无姓名和头像，即User=null
            //			@Override
            //			protected void onComplete() {
            //				if (response != null) {
            //					putQueryResult(path, response);//解决获取关联数据时requestObject里不存在需要的关联数据
            //				}
            //			}

        }.setMethod(requestMethod).setParser(this);
    }

    @Override
    public void onVerifyContent() throws Exception {
        //补充全局缺省版本号  //可能在默认为1的前提下这个请求version就需要为0  requestObject.getIntValue(JSONRequest.KEY_VERSION) <= 0) {
        if (session != null && requestObject.get(JSONRequest.KEY_VERSION) == null) {
            requestObject.put(JSONRequest.KEY_VERSION, session.getAttribute(JSONRequest.KEY_VERSION));
        }
        super.onVerifyContent();
    }

//    // 可重写来设置最大查询数量
//    @Override
//    public int getMaxQueryCount() {
//        return 50;
//    }

}
