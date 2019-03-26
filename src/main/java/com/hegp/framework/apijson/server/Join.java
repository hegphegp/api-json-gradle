package com.hegp.framework.apijson.server;

import com.alibaba.fastjson.JSONObject;

import com.hegp.framework.apijson.NotNull;

/**连表 配置
 * @author Lemon
 */
public class Join {
	
	private String path;
	
	private String originKey;
	private String originValue;

	private String joinType; // "@" - APP, "&" - INNER, "|" - FULL, "!" - OUTTER, "<" - LEFT, ">" - RIGHT, "^" - SIDE, "*" - CROSS
	private String relateType; // "" - 一对一, "{}" - 一对多, "<>" - 多对一
	private JSONObject table; // { "id@":"/Moment/userId" }
	private String name; //User
	private String key; //id
	private String targetName; // Moment
	private String targetKey; // userId

	private SQLConfig joinConfig;
	private SQLConfig cacheConfig;

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	public String getOriginKey() {
		return originKey;
	}
	public void setOriginKey(String originKey) {
		this.originKey = originKey;
	}
	public String getOriginValue() {
		return originValue;
	}
	public void setOriginValue(String originValue) {
		this.originValue = originValue;
	}
	
	public String getJoinType() {
		return joinType;
	}
	public void setJoinType(String joinType) {
		this.joinType = joinType;
	}
	public String getRelateType() {
		return relateType;
	}
	public void setRelateType(String relateType) {
		this.relateType = relateType;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public JSONObject getTable() {
		return table;
	}
	public void setTable(JSONObject table) {
		this.table = table;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getTargetName() {
		return targetName;
	}
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}
	public String getTargetKey() {
		return targetKey;
	}
	public void setTargetKey(String targetKey) {
		this.targetKey = targetKey;
	}


	public SQLConfig getJoinConfig() {
		return joinConfig;
	}
	public void setJoinConfig(SQLConfig joinConfig) {
		this.joinConfig = joinConfig;
	}
	public SQLConfig getCacheConfig() {
		return cacheConfig;
	}
	public void setCacheConfig(SQLConfig cacheConfig) {
		this.cacheConfig = cacheConfig;
	}

	
	public void setKeyAndType(@NotNull String originKey) throws Exception { //id, id@, id{}@, contactIdList<>@ ...
		if (originKey.endsWith("@")) {
			originKey = originKey.substring(0, originKey.length() - 1);
		}
		else { //TODO 暂时只允许 User.id = Moment.userId 字段关联，不允许 User.id = 82001 这种
			throw new IllegalArgumentException(joinType + "/.../" + name + "/" + originKey + " 不合法！join:'.../refKey'" + " 中 refKey 必须以 @ 结尾！");
		}
		
		if (originKey.endsWith("{}")) {
			setRelateType("{}");
			setKey(originKey.substring(0, originKey.length() - 2));
		}
		else if (originKey.endsWith("<>")) {
			setRelateType("<>");
			setKey(originKey.substring(0, originKey.length() - 2));
		}
		else {
			setRelateType("");
			setKey(originKey);
		}
	}
	
	
	
	public boolean isSQLJoin() {
		return ! isAppJoin();
	}
	
	public static boolean isSQLJoin(Join j) {
		return j != null && j.isSQLJoin();
	}
	
	public boolean isAppJoin() {
		return "@".equals(getJoinType());
	}
	
	public static boolean isAppJoin(Join j) {
		return j != null && j.isAppJoin();
	}


}
