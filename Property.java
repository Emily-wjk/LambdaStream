package com.rit.demo.pojo;


public class Property {
	Integer id;
	String  name;
    // 距离，单位:米
    Integer distance;
    // 销量，月售
    Integer sales;
    // 价格，这里简单起见就写一个级别代表价格段
    Integer priceLevel;
    public Integer getId() {
		return id;
	}
    public Integer getDistance() {
		return distance;
	}
    public String getName() {
		return name;
	}
    public Integer getPriceLevel() {
		return priceLevel;
	}
    public Integer getSales() {
		return sales;
	}
    public void setId(Integer id) {
		this.id = id;
	}
    public void setDistance(Integer distance) {
		this.distance = distance;
	}
    public void setName(String name) {
		this.name = name;
	}
    public void setPriceLevel(Integer priceLevel) {
		this.priceLevel = priceLevel;
	}
    public void setSales(Integer sales) {
		this.sales = sales;
	}
}
