package com.fuzamei.entity;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HistoryHeight {
	private int id;
	private int serverId;
	private int height;
	private int projectId;
	private String updateTime;
}
