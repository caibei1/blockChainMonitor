package com.fuzamei.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ExceptionInfo {
	private int id;
	private int port;
	private int projectId;
	private int serverId;
	private String createTime;
	private String updateTime;
	private int state;
	private String projectName;
	private String description;
}
