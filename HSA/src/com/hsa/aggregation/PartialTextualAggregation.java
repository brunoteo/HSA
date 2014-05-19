package com.hsa.aggregation;

import java.util.List;

import com.hsa.bean.Card;
import com.hsa.bean.Formation;

public class PartialTextualAggregation {
	
	private String name;
	private Integer cost;
	private String rarity;
	private Double probability;
	private Integer occurrences;
	
	public PartialTextualAggregation(){}
	
	public PartialTextualAggregation(Card card, List<Formation> trackFormations){
		this.name = card.getName();
		this.cost = card.getCost();
		this.rarity = card.getRarity();
		int total = 0;
		for(Formation formation : trackFormations){
			if(formation.getCard() == this.name) this.occurrences = formation.getOccurrence();
			total = total + this.occurrences;
		}
		this.probability = (double) (this.occurrences / total * 100.00);
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
	public Double getProbability() {
		return probability;
	}
	public void setProbability(Double probability) {
		this.probability = probability;
	}
	public Integer getOccurrences() {
		return occurrences;
	}
	public void setOccurrences(Integer occurrences) {
		this.occurrences = occurrences;
	}

}
