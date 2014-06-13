package com.hsa.aggregation;


public class GraphicalAggregation {
	
	private String name;
	private int occurrence;
	private int image;
	
	public GraphicalAggregation(){};
	
	public GraphicalAggregation(String name, int occurrence, int image){
		this.name = name;
		this.occurrence = occurrence;
		this.image = image;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOccurrence() {
		return occurrence;
	}
	public void setOccurrence(int occurence) {
		this.occurrence = occurence;
	}
	public int getImage() {
		return image;
	}
	public void setImage(int image) {
		this.image = image;
	}
	

}
