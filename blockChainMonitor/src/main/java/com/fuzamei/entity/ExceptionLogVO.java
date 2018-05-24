package com.fuzamei.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExceptionLogVO {

	
	private int id;
	private int projectId;
	private int applicationId;
	private int serverId;
	private int state;
	private Integer page;			  //页数
	private Integer rowNum;			  //每页显示得条数
	private Integer startPage;		  //起始页
}
