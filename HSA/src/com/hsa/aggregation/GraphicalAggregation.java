package com.hsa.aggregation;


public class GraphicalAggregation {
	
	private String name;
	private int occurence;
	private int image;
	
	public GraphicalAggregation(){};
	
	public GraphicalAggregation(String name, int occurrence, int image){
		this.name = name;
		this.occurence = occurrence;
		this.image = image;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOccurence() {
		return occurence;
	}
	public void setOccurence(int occurence) {
		this.occurence = occurence;
	}
	public int getImage() {
		return image;
	}
	public void setImage(int image) {
		this.image = image;
	}
	

}
