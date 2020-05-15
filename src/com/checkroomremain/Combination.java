package com.checkroomremain;

public class Combination {//要得出符合目前剩餘房間的訂房組合
	Integer one= 0;
	Integer two= 0;
	Integer three= 0;
	Integer four= 0;
	Integer five= 0;
	Integer six= 0;
	Integer howManyRooms = 0;
	Integer howManyPeople = 0;//X個人住X間房
	
	
	public Combination() {
		super();
	}


	public Combination(Integer one, Integer two, Integer three, Integer four, Integer five, Integer six,
			Integer howManyRooms, Integer howManyPeople) {
		super();
		this.one = one;
		this.two = two;
		this.three = three;
		this.four = four;
		this.five = five;
		this.six = six;
		this.howManyRooms = howManyRooms;
		this.howManyPeople = howManyPeople;
	}


	public Integer getOne() {
		return one;
	}


	public void setOne(Integer one) {
		this.one = one;
	}


	public Integer getTwo() {
		return two;
	}


	public void setTwo(Integer two) {
		this.two = two;
	}


	public Integer getThree() {
		return three;
	}


	public void setThree(Integer three) {
		this.three = three;
	}


	public Integer getFour() {
		return four;
	}


	public void setFour(Integer four) {
		this.four = four;
	}


	public Integer getFive() {
		return five;
	}


	public void setFive(Integer five) {
		this.five = five;
	}


	public Integer getSix() {
		return six;
	}


	public void setSix(Integer six) {
		this.six = six;
	}


	public Integer getHowManyRooms() {
		return howManyRooms;
	}


	public void setHowManyRooms(Integer howManyRooms) {
		this.howManyRooms = howManyRooms;
	}


	public Integer getHowManyPeople() {
		return howManyPeople;
	}


	public void setHowManyPeople(Integer howManyPeople) {
		this.howManyPeople = howManyPeople;
	}


	@Override
	public String toString() {
		return "Combination [one=" + one + ", two=" + two + ", three=" + three + ", four=" + four + ", five=" + five
				+ ", six=" + six + ", howManyRooms=" + howManyRooms + ", howManyPeople=" + howManyPeople + "]";
	}
	
	
}
