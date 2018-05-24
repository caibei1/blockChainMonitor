package com.fuzamei.entity;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Project {
	private int projectId;
	private String projectName;
	private String description;
	private String company;
	private String version;
	private int state;
	private int isMonitor;
	private int isOnline;
	private String createTime;
	private String updateTime;
	private int maxHeight;
	private int minHeight;
}
