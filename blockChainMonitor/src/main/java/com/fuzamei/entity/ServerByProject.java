package com.fuzamei.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ServerByProject {
	private int projectId;
	private int serverId;
	private int maxHeight;
	private int minHeight;
	private int port;
	private int isMonitor;
	private String outIp;
	private String url;
	private int status;
}
