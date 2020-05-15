package com.checkroomremain;

public class DifferentNumberRoomRemain {//不同"人數"的房型的剩餘數量
	Integer one= 0;
	Integer two= 0;
	Integer three= 0;
	Integer four= 0;
	Integer five= 0;
	Integer six= 0;
	
	
	public DifferentNumberRoomRemain() {
		super();
	}

	public DifferentNumberRoomRemain(Integer one, Integer two, Integer three, Integer four, Integer five, Integer six) {
		super();
		this.one = one;
		this.two = two;
		this.three = three;
		this.four = four;
		this.five = five;
		this.six = six;
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

	@Override
	public String toString() {
		return "DifferentNumberRoomRemain [one=" + one + ", two=" + two + ", three=" + three + ", four=" + four
				+ ", five=" + five + ", six=" + six + "]";
	}
	
	
}
