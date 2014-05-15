package com.hsa.aggregation;

import com.hsa.bean.Card;

public class CompleteTextualAggregation {

	private String name;
	private String type;
	private Integer cost;
	private String className;
	private String rarity;
	private String effect;
	private Integer attack;
	private Integer health;
	private Integer durability;
	private String race;
	
	public CompleteTextualAggregation() {}
	
	public CompleteTextualAggregation(Card card) {
		this.name = card.getName();
		this.type = card.getType();
		this.cost = card.getCost();
		this.className = card.getClassName();
		this.rarity = card.getRarity();
		this.effect = card.getEffect();
		this.attack = card.getAttack();
		this.health = card.getHealth();
		this.durability = card.getDurability();
		this.race = card.getRace();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getRarity() {
		return rarity;
	}
	public void setRarity(String rarity) {
		this.rarity = rarity;
	}
	public String getEffect() {
		return effect;
	}
	public void setEffect(String effect) {
		this.effect = effect;
	}
	public Integer getAttack() {
		return attack;
	}
	public void setAttack(Integer attack) {
		this.attack = attack;
	}
	public Integer getHealth() {
		return health;
	}
	public void setHealth(Integer health) {
		this.health = health;
	}
	public int getDurability() {
		return durability;
	}
	public void setDurability(Integer durability) {
		this.durability = durability;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
}
