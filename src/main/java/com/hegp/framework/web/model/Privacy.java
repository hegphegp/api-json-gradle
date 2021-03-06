package com.hegp.framework.web.model;

import static com.hegp.framework.apijson.RequestRole.ADMIN;
import static com.hegp.framework.apijson.RequestRole.OWNER;
import static com.hegp.framework.apijson.RequestRole.UNKNOWN;

import com.hegp.framework.apijson.MethodAccess;

/**
 * TODO 漏洞：如果GETS允许CONTACT，则CONTACT能看到自己的余额，tag可以不是Privacy-circle。
 * 所以需要在Request表中增加role字段。或者干脆这里GETS只允许OWNER, ADMIN，需要用其它角色查时走独立接口。
 */

/**
 * 用户隐私信息
 *
 * @author Lemon
 */
@MethodAccess(
        GET = {},
        GETS = {OWNER, ADMIN},
        POST = {UNKNOWN, ADMIN},
        DELETE = {ADMIN}
)
public class Privacy extends BaseModel {
    private static final long serialVersionUID = 1L;

    public static final int PASSWORD_TYPE_LOGIN = 0;
    public static final int PASSWORD_TYPE_PAY = 1;

    private String phone; //手机
    private String password; //登录密码，隐藏字段
    private String payPassword; //支付密码，隐藏字段
    private Double balance;    //余额

    public Privacy() {
        super();
    }

    public Privacy(long id) {
        this();
        setId(id);
    }

    public Privacy(String phone, String password) {
        this();
        setPhone(phone);
        setPassword(password);
    }


    public String getPhone() {
        return phone;
    }

    public Privacy setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * get_password会转为password
     *
     * @return
     */
    public String get__password() {
        return password;
    }

    public Privacy setPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * get_PayPassword会转为PayPassword
     *
     * @return
     */
    public String get__payPassword() {
        return payPassword;
    }

    public Privacy setPayPassword(String payPassword) {
        this.payPassword = payPassword;
        return this;
    }

    public Double getBalance() {
        return balance;
    }

    public Privacy setBalance(Double balance) {
        this.balance = balance;
        return this;
    }

}
