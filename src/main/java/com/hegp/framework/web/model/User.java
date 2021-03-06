package com.hegp.framework.web.model;

import static com.hegp.framework.apijson.RequestRole.ADMIN;
import static com.hegp.framework.apijson.RequestRole.UNKNOWN;

import java.util.List;

import com.hegp.framework.apijson.MethodAccess;
import com.hegp.framework.apijson.server.Visitor;

/**
 * 用户开放信息
 *
 * @author Lemon
 */
@MethodAccess(
        POST = {UNKNOWN, ADMIN},
        DELETE = {ADMIN}
)
public class User extends BaseModel implements Visitor<Long> {
    private static final long serialVersionUID = 1L;

    public static final int SEX_MAIL = 0;
    public static final int SEX_FEMALE = 1;
    public static final int SEX_UNKNOWN = 2;

    private Integer sex; //性别
    private String head; //头像url
    private String name; //姓名
    private String tag; //标签
    private List<String> pictureList; //照片列表
    private List<Long> contactIdList; //朋友列表

    /**
     * 默认构造方法，JSON等解析时必须要有
     */
    public User() {
        super();
    }

    public User(long id) {
        this();
        setId(id);
    }

    public Integer getSex() {
        return sex;
    }

    public User setSex(Integer sex) {
        this.sex = sex;
        return this;
    }

    public String getHead() {
        return head;
    }

    public User setHead(String head) {
        this.head = head;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public List<String> getPictureList() {
        return pictureList;
    }

    public User setPictureList(List<String> pictureList) {
        this.pictureList = pictureList;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public User setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public List<Long> getContactIdList() {
        return contactIdList;
    }

    public User setContactIdList(List<Long> contactIdList) {
        this.contactIdList = contactIdList;
        return this;
    }


}
