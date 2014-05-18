package com.hsa.aggregation;

import com.hsa.bean.Deck;

import android.os.Parcel;
import android.os.Parcelable;

public class DeckDataAggregation implements Parcelable{

	/**
	 * 
	 */
	private String name;
	private String className;
	private int cardNumber;
	private String date;
	
	public DeckDataAggregation() { }
	
	public DeckDataAggregation(Deck deck) {
		this.name = deck.getName();
		this.className = deck.getClassName();
		this.cardNumber = 0;
		this.date = deck.getDate();
	}
	
	public DeckDataAggregation(Parcel parcel) {
		this.name = parcel.readString();
		this.className = parcel.readString();
		this.cardNumber = parcel.readInt();
		this.date = parcel.readString();
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
	public int getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel parcel, int flags) {
		parcel.writeString(name);
		parcel.writeString(className);
		parcel.writeInt(cardNumber);
		parcel.writeString(date);	
	}
	
	public static final Creator<DeckDataAggregation> CREATOR = new Creator<DeckDataAggregation>() {
        public DeckDataAggregation createFromParcel(Parcel in) {
            return new DeckDataAggregation(in); 
        }

        public DeckDataAggregation[] newArray(int size) {
            return new DeckDataAggregation[size];
        }
    };
	
}
