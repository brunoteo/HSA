package com.hsa.bean;

import java.util.ArrayList;
import java.util.Map;

public class SearchCriterion {

	private String name;
	private Map<String,ArrayList<String>> filters;
	
	public SearchCriterion(){}
	
	public SearchCriterion(String name, Map<String,ArrayList<String>> filters){
		this.name=name;
		this.filters=filters;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, ArrayList<String>> getFilters() {
		return filters;
	}
	public void setFilters(Map<String, ArrayList<String>> filters) {
		this.filters = filters;
	}
}
