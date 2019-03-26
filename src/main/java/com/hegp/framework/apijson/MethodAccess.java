package com.hegp.framework.apijson;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static com.hegp.framework.apijson.RequestRole.ADMIN;
import static com.hegp.framework.apijson.RequestRole.CIRCLE;
import static com.hegp.framework.apijson.RequestRole.CONTACT;
import static com.hegp.framework.apijson.RequestRole.LOGIN;
import static com.hegp.framework.apijson.RequestRole.OWNER;
import static com.hegp.framework.apijson.RequestRole.UNKNOWN;

/**
 * 请求方法权限，只允许某些角色通过对应方法访问
 *
 * @author Lemon
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Inherited
public @interface MethodAccess {

    /**
     * @return 该请求方法允许的角色 default {UNKNOWN, LOGIN, CONTACT, CIRCLE, OWNER, ADMIN};
     * @see {@link RequestMethod#GET}
     */
    RequestRole[] GET() default {UNKNOWN, LOGIN, CONTACT, CIRCLE, OWNER, ADMIN};

    /**
     * @return 该请求方法允许的角色 default {UNKNOWN, LOGIN, CONTACT, CIRCLE, OWNER, ADMIN};
     * @see {@link RequestMethod#HEAD}
     */
    RequestRole[] HEAD() default {UNKNOWN, LOGIN, CONTACT, CIRCLE, OWNER, ADMIN};

    /**
     * @return 该请求方法允许的角色 default {LOGIN, CONTACT, CIRCLE, OWNER, ADMIN};
     * @see {@link RequestMethod#GETS}
     */
    RequestRole[] GETS() default {LOGIN, CONTACT, CIRCLE, OWNER, ADMIN};

    /**
     * @return 该请求方法允许的角色 default {LOGIN, CONTACT, CIRCLE, OWNER, ADMIN};
     * @see {@link RequestMethod#HEADS}
     */
    RequestRole[] HEADS() default {LOGIN, CONTACT, CIRCLE, OWNER, ADMIN};

    /**
     * @return 该请求方法允许的角色  default {LOGIN, ADMIN};
     * @see {@link RequestMethod#POST}
     */
    RequestRole[] POST() default {OWNER, ADMIN};

    /**
     * @return 该请求方法允许的角色 default {OWNER, ADMIN};
     * @see {@link RequestMethod#PUT}
     */
    RequestRole[] PUT() default {OWNER, ADMIN};

    /**
     * @return 该请求方法允许的角色 default {OWNER, ADMIN};
     * @see {@link RequestMethod#DELETE}
     */
    RequestRole[] DELETE() default {OWNER, ADMIN};

}
