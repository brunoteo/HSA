package com.hsa.bean;

public class Deck {

	private String name;
	private String className;
	private String note;
	private String date;
	
	public Deck(){}
	
	public Deck(String name, String className, String note, String date){
		this.name = name;
		this.className = className;
		this.note = note;
		this.date = date;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
