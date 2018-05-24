package com.fuzamei.entity;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Application {
	private int applicationId;
	private int projectId;
	private String url;
	private String port;
	private String description;
	private String ip;
	private int serverId;
	private int isMonitor;
	private String createTime;
	private String updateTime;
	private Integer status;
}
