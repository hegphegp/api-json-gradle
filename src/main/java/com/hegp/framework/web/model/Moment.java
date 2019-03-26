package com.hegp.framework.web.model;

import static com.hegp.framework.apijson.RequestRole.ADMIN;
import static com.hegp.framework.apijson.RequestRole.CIRCLE;
import static com.hegp.framework.apijson.RequestRole.CONTACT;
import static com.hegp.framework.apijson.RequestRole.LOGIN;
import static com.hegp.framework.apijson.RequestRole.OWNER;

import com.hegp.framework.apijson.MethodAccess;

/**
 * 动态
 *
 * @author Lemon
 */
@MethodAccess(
    PUT = {LOGIN, CONTACT, CIRCLE, OWNER, ADMIN}//TODO 还要细分，LOGIN,CONTACT只允许修改praiseUserIdList。数据库加role没用，应该将praiseUserIdList移到Praise表
)
public class Moment {
}