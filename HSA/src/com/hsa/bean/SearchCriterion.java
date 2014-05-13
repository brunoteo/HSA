package com.hsa.bean;

import java.util.Map;

public class SearchCriterion {

	private String name;
	private Map<String, String> filters;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, String> getFilters() {
		return filters;
	}
	public void setFilters(Map<String, String> filters) {
		this.filters = filters;
	}
}