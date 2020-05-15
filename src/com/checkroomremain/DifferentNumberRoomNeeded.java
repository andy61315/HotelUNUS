package com.checkroomremain;

public class DifferentNumberRoomNeeded {
	Integer howManyPeople;
	Integer need;
	public DifferentNumberRoomNeeded() {
		super();
	}
	public DifferentNumberRoomNeeded(Integer howManyPeople, Integer need) {//howManyPeople=幾人房XD
		super();
		this.howManyPeople = howManyPeople;
		this.need = need;
	}
	public Integer getHowManyPeople() {
		return howManyPeople;
	}
	public void setHowManyPeople(Integer howManyPeople) {
		this.howManyPeople = howManyPeople;
	}
	public Integer getNeed() {
		return need;
	}
	public void setNeed(Integer need) {
		this.need = need;
	}
	@Override
	public String toString() {
		return "DifferentNumberRoomNeeded [howManyPeople=" + howManyPeople + ", need=" + need + "]";
	}
	
	
}
