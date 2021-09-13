package com.ssafy.house.model.dto;

public class HouseAmountPerAreaDto {
	private String dong;
	private String aptName;
	private double avgAmount;
	private double avgArea;
	private int amountPerArea;
	
	public HouseAmountPerAreaDto() {
		super();
	}

	public HouseAmountPerAreaDto(String dong, String aptName, double avgAmount, double avgArea, int amountPerArea) {
		super();
		this.dong = dong;
		this.aptName = aptName;
		this.avgAmount = avgAmount;
		this.avgArea = avgArea;
		this.amountPerArea = amountPerArea;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getAptName() {
		return aptName;
	}

	public void setAptName(String aptName) {
		this.aptName = aptName;
	}

	public double getAvgAmount() {
		return avgAmount;
	}

	public void setAvgAmount(double avgAmount) {
		this.avgAmount = avgAmount;
	}

	public double getAvgArea() {
		return avgArea;
	}

	public void setAvgArea(double avgArea) {
		this.avgArea = avgArea;
	}

	public int getAmountPerArea() {
		return amountPerArea;
	}

	public void setAmountPerArea(int amountPerArea) {
		this.amountPerArea = amountPerArea;
	}

	@Override
	public String toString() {
		return "HouseAmountPerAreaDto [dong=" + dong + ", aptName=" + aptName + ", avgAmount=" + avgAmount + ", avgArea="
				+ avgArea + ", amountPerArea=" + amountPerArea + "]";
	}
	
}
