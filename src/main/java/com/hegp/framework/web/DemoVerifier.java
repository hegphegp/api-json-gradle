package com.hegp.framework.web;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

import com.hegp.framework.web.model.Comment;
import com.hegp.framework.web.model.Login;
import com.hegp.framework.web.model.Moment;
import com.hegp.framework.web.model.Privacy;
import com.hegp.framework.web.model.User;
import com.hegp.framework.web.model.Verify;
import com.hegp.framework.apijson.MethodAccess;
import com.hegp.framework.apijson.server.AbstractVerifier;
import com.hegp.framework.apijson.server.Visitor;

/**
 * 权限验证器
 */
public class DemoVerifier extends AbstractVerifier<Long> {
    private static final String TAG = "DemoVerifier";

    // <TableName, <METHOD, allowRoles>>
    // <User, <GET, [OWNER, ADMIN]>>
    static { //注册权限
        ACCESS_MAP.put(User.class.getSimpleName(), getAccessMap(User.class.getAnnotation(MethodAccess.class)));
        ACCESS_MAP.put(Privacy.class.getSimpleName(), getAccessMap(Privacy.class.getAnnotation(MethodAccess.class)));
        ACCESS_MAP.put(Moment.class.getSimpleName(), getAccessMap(Moment.class.getAnnotation(MethodAccess.class)));
        ACCESS_MAP.put(Comment.class.getSimpleName(), getAccessMap(Comment.class.getAnnotation(MethodAccess.class)));
        ACCESS_MAP.put(Verify.class.getSimpleName(), getAccessMap(Verify.class.getAnnotation(MethodAccess.class)));
        ACCESS_MAP.put(Login.class.getSimpleName(), getAccessMap(Login.class.getAnnotation(MethodAccess.class)));
    }

    @NotNull
    @Override
    public DemoParser createParser() {
        DemoParser parser = new DemoParser();
        parser.setVisitor(visitor);
        return parser;
    }

    /**
     * 登录校验
     */
    public static void verifyLogin(HttpSession session) throws Exception {
        new DemoVerifier().setVisitor(getVisitor(session)).verifyLogin();
    }

    /**
     * 获取来访用户的id
     */
    public static long getVisitorId(HttpSession session) {
        if (session == null) {
            return 0;
        }
        Long id = (Long) session.getAttribute(Controller.USER_ID);
        if (id == null) {
            Visitor<Long> v = getVisitor(session);
            id = v == null ? 0 : value(v.getId());
            session.setAttribute(Controller.USER_ID, id);
        }
        return value(id);
    }

    /**
     * 获取来访用户
     */
    @SuppressWarnings("unchecked")
    public static Visitor<Long> getVisitor(HttpSession session) {
        return session == null ? null : (Visitor<Long>) session.getAttribute(Controller.USER_);
    }

    public static long value(Long v) {
        return v == null ? 0 : v;
    }
}
