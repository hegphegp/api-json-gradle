package com.hegp.framework.web.model;

import com.hegp.framework.apijson.MethodAccess;

import static com.hegp.framework.apijson.RequestRole.ADMIN;
import static com.hegp.framework.apijson.RequestRole.UNKNOWN;

/**
 * 评论
 *
 * @author Lemon
 */
@MethodAccess(
    DELETE = {UNKNOWN, ADMIN}
)
public class Comment {
}