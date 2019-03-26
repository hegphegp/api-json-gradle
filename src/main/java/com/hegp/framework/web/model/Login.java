package com.hegp.framework.web.model;

import static com.hegp.framework.apijson.RequestRole.ADMIN;
import static com.hegp.framework.apijson.RequestRole.CONTACT;
import static com.hegp.framework.apijson.RequestRole.LOGIN;
import static com.hegp.framework.apijson.RequestRole.OWNER;
import static com.hegp.framework.apijson.RequestRole.UNKNOWN;

import com.hegp.framework.apijson.MethodAccess;

/**登录日志
 * @author Lemon
 */
@SuppressWarnings("serial")
@MethodAccess(
		GET = {},
		HEAD = {},
		GETS = {UNKNOWN, LOGIN, CONTACT, OWNER, ADMIN},
		HEADS = {UNKNOWN, LOGIN, CONTACT, OWNER, ADMIN},
		POST = {ADMIN},
		PUT = {ADMIN},
		DELETE = {ADMIN}
		)
public class Login extends BaseModel {

	public static final int TYPE_PASSWORD = 0;//密码登录
	public static final int TYPE_VERIFY = 1;//验证码登录
	
	private Integer type;

	public Login() {
		super();
	}
	public Login(long userId) {
		this();
		setUserId(userId);
	}

	
	public Integer getType() {
		return type;
	}
	public Login setType(Integer type) {
		this.type = type;
		return this;
	}

}
