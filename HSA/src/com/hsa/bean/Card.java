package com.hsa.bean;

public class Card {
	private String name;
	private String type;
	private Integer cost;
	private String rarity;
	private String effect;
	private String className;
	private Integer attack;
	private Integer health;
	private Integer durability;
	private String race;
	private String path;
	
	public Card(){}
	
	public Card(String name, String type, Integer cost, String rarity,
			String effect, String className, Integer attack, Integer health,
			Integer durability, String race, String path){;
		this.name = name;
		this.type = type;
		this.cost = cost;
		this.rarity = rarity;
		this.effect = effect;
		this.className = className;
		this.attack = attack;
		this.health = health;
		this.durability = durability;
		this.race = race;
		this.path = path;
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

	public String getEffect() {
		return effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
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

	public Integer getDurability() {
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	

}
