package com.hegp.framework.web;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSONObject;

import com.hegp.framework.apijson.RequestMethod;
import com.hegp.framework.apijson.StringUtil;
import com.hegp.framework.apijson.server.AbstractObjectParser;
import com.hegp.framework.apijson.server.Parser;
import com.hegp.framework.apijson.server.SQLConfig;

/**
 * 简化Parser，getObject和getArray(getArrayConfig)都能用
 */
public abstract class DemoObjectParser extends AbstractObjectParser {

    static {
        COMPILE_MAP.put("phone", StringUtil.PATTERN_PHONE);
        COMPILE_MAP.put("email", StringUtil.PATTERN_EMAIL);
        COMPILE_MAP.put("idCard", StringUtil.PATTERN_ID_CARD);
    }

    /**
     * for single object
     */
    public DemoObjectParser(HttpSession session, @NotNull JSONObject request, String parentPath, String name, SQLConfig arrayConfig, boolean isSubquery) throws Exception {
        super(request, parentPath, name, arrayConfig, isSubquery);
    }

    @Override
    public DemoObjectParser setMethod(RequestMethod method) {
        super.setMethod(method);
        return this;
    }

    @Override
    public DemoObjectParser setParser(Parser<?> parser) {
        super.setParser(parser);
        return this;
    }

    @Override
    public SQLConfig newSQLConfig() throws Exception {
        return DemoSQLConfig.newSQLConfig(method, table, sqlRequest, joinList);
    }
}