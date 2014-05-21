package com.hsa.bean;

public class Formation{

	private String card;
	private String deck;
	private Integer occurrence;
	
	public Formation(){}
	
	public Formation(String card, String deck, Integer occurrence){
		this.card = card;
		this.deck = deck;
		this.occurrence = occurrence;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getDeck() {
		return deck;
	}

	public void setDeck(String deck) {
		this.deck = deck;
	}

	public Integer getOccurrence() {
		return occurrence;
	}

	public void setOccurrence(Integer occurrence) {
		this.occurrence = occurrence;
	}
	
	@Override
	public boolean equals(Object o) {

        if (o instanceof Formation)
        {
        	Formation f = (Formation) o;
        	if(card.equals(f.getCard()) && deck.equals(f.getDeck()) && occurrence==f.getOccurrence()) {
        		return true;
        	}
        }

        return false;
	}
	
	@Override
	public int hashCode() {
		return card.hashCode() + deck.hashCode() + occurrence.hashCode();
	}

}
