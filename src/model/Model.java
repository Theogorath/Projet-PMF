
package model;

import java.util.*;

public class Model extends Observable {
	private double dew ;
	private double temperature ;
	private double humidity ;
	private double order ;

	private double temperaturelist[] = new double[6];
	
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
		setChanged();
		notifyObservers(this.humidity);
	}
	public double getOrder() {
		return order;
	}
	public void setOrder(double order) {
		this.order = order;
	}
	public double[] getTemperaturelist() {
		return temperaturelist;
	}
	public void setTemperaturelist(double[] temperaturelist) {
		this.temperaturelist = temperaturelist;
	}
	
	
	
	public Model(){
		this.humidity = humidity;
		this.temperature = temperature;
		this.dew = dew;
		this.order = order;
		this.temperaturelist = temperaturelist;
		
		
	}

	

	
}
