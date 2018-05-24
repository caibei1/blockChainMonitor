package com.fuzamei.entity;

public class ExceptionLog {
	private int id;
	private int projectId;
	private int serverId;
	private int applicationId;
	private int state;
	private String context;
	private String createTime;
	private String updateTime;
	private Integer port;
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getServerId() {
		return serverId;
	}
	public void setServerId(int serverId) {
		this.serverId = serverId;
	}
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "ExceptionLog [id=" + id + ", projectId=" + projectId + ", serverId=" + serverId + ", applicationId="
				+ applicationId + ", state=" + state + ", context=" + context + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}

	
}
