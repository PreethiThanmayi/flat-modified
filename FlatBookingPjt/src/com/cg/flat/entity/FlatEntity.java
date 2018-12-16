package com.cg.flat.entity;

public class FlatEntity {
	public String cusFlatId;
	public String flatArea;
	public String flatType;
	public String flatSqft;
	public String flatPrice;
	public int flatAbty; 	
	
	
	public int getFlatAbty() {
		return flatAbty;
	}
	public void setFlatAbty(int flatAbty) {
		this.flatAbty = flatAbty;
	}
	public String getCusFlatId() {
		return cusFlatId;
	}
	public void setCusFlatId(String cusFlatId) {
		this.cusFlatId = cusFlatId;
	}
	public String getFlatArea() {
		return flatArea;
	}
	public void setFlatArea(String flatArea) {
		this.flatArea = flatArea;
	}
	public String getFlatType() {
		return flatType;
	}
	public void setFlatType(String flatType) {
		this.flatType = flatType;
	}
	public String getFlatSqft() {
		return flatSqft;
	}
	public void setFlatSqft(String flatSqft) {
		this.flatSqft = flatSqft;
	}
	public String getFlatPrice() {
		return flatPrice;
	}
	public void setFlatPrice(String flatPrice) {
		this.flatPrice = flatPrice;
	}
	
	
	
	public String toString() {
		return "FlatEntity [cusFlatId=" + cusFlatId + ", flatArea=" + flatArea + ", flatType=" + flatType
				+ ", flatSqft=" + flatSqft + ", flatPrice=" + flatPrice + ", flatAbty=" + flatAbty + "]";
	}
	

}
