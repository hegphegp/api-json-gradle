package com.hegp.framework.apijson.server;

import java.util.List;

/**
 * 来访者
 *
 * @author Lemon
 */
public interface Visitor<T> {

    T getId();

    List<T> getContactIdList();

}
