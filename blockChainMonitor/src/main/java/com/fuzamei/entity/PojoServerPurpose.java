package com.fuzamei.entity;

public class PojoServerPurpose {
	private int serverPurposeId;
	private int newId;
	private  String context;
	public int getServerPurposeId() {
		return serverPurposeId;
	}
	public void setServerPurposeId(int serverPurposeId) {
		this.serverPurposeId = serverPurposeId;
	}
	public int getNewId() {
		return newId;
	}
	public void setNewId(int newId) {
		this.newId = newId;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	@Override
	public String toString() {
		return "ServerPurpose [serverPurposeId=" + serverPurposeId + ", newId=" + newId + ", context=" + context + "]";
	}
	
}
