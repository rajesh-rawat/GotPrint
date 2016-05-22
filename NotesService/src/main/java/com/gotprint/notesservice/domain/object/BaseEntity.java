package com.gotprint.notesservice.domain.object;

import java.sql.Timestamp;

public class BaseEntity implements Persistable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1144972159264444971L;

	private Timestamp createTime;

	private Timestamp lastUpdateTime;
	
	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
}
