package com.fuzamei.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class Server {
	
	private int serverId;
	
	private String serverName;
	
	private String company;
	
	private String outIp;
	
	private String inIp;
	
	private Integer serverPurposeId;
	
	private String createTime;
	
	private String updateTime;
	
	private int isDelete;
	
	private int state;
	
	private String description;

	private String info;
	
	private List<Project> projects;
	
	private int rows;
	
	private int page;

	private int start;
	
	private int end;
	
}
