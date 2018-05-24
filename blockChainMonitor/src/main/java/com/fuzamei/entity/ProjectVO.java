package com.fuzamei.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProjectVO {
	
	private String name;

	private String describe;

	private	String	company;

	private	String	version;

	private	String	adminIds;

	private String	machineIds;

	private	String	nodePort;

	private String	webMachine;

	private String	webPorts;

	private String	webCountPort;
	
//	private List<String> adminIdsList;
//	
//	private List<String> machineIdsList;
//	
//	private List<String> webPortsList;

}
