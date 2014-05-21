package com.hsa.aggregation;

import java.util.List;

import com.hsa.bean.Card;
import com.hsa.bean.Formation;

public class PartialTextualAggregation {
	
	private String name;
	private Integer cost;
	private String rarity;
	private Integer probability;
	private Integer occurrences;
	
	public PartialTextualAggregation(){}
	
	public PartialTextualAggregation(String name, Integer cost, String rarity, Integer probability, Integer occurrences){
		this.name = name;
		this.cost = cost;
		this.rarity = rarity;
		this.probability = probability;
		this.occurrences = occurrences;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}
	public String getRarity() {
		return rarity;
	}
	public void setRarity(String rarity) {
		this.rarity = rarity;
	}
	public Integer getProbability() {
		return probability;
	}
	public void setProbability(Integer probability2) {
		this.probability = probability2;
	}
	public Integer getOccurrences() {
		return occurrences;
	}
	public void setOccurrences(Integer occurrences) {
		this.occurrences = occurrences;
	}

}
