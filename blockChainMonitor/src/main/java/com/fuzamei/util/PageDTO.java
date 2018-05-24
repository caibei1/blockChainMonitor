package com.fuzamei.util;


import java.util.List;

/**
 * This is a pagination DTO
 * 
 * @author sinba
 */
public class PageDTO {

	private static final int DEFAULT_ROW_NUM = 20;
	
	public PageDTO() {}
	
	public static final <T> PageDTO getPagination(int total, List<T> rows){
		PageDTO pagination = new PageDTO();
		pagination.setRows(rows);
		pagination.setTotal(total);
		return pagination;
	};

	@SuppressWarnings("rawtypes")
	private List rows; // 需要显示的数据

	private int total; // 总的页数

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}