package com.fuzamei.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProjectServer {
	private int id;
	private int serverId;
	private int projectId;
	private int port;
	private int isMonitor;
	private Integer status;
}
