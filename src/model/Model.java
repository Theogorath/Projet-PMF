
package model;

import java.util.*;

public class Model {
	private double dew ;
	private double temperature ;
	private double humidity ;
	private double order ;
	ArrayList listeTemp = new ArrayList();
	public double getDew() {
		return dew;
	}
	public void setDew(double dew) {
		this.dew = dew;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public double getHumidity() {
		return humidity;
	}
	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}
	public double getOrder() {
		return order;
	}
	public void setOrder(double order) {
		this.order = order;
	}
	public ArrayList getListeTemp() {
		return listeTemp;
	}
	public void setListeTemp(ArrayList listeTemp) {
		this.listeTemp = listeTemp;
	}
	
}
