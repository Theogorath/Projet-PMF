
package model;

import java.util.*;

public class Model extends Observable {
	private double dew ;
	private double temperature ;
	private double humidity ;
	private int order;

	public double getDew() {
		return dew;
	}
	public void setDew(double dew) {
		this.dew = dew;
		setChanged();
		notifyObservers(this.dew);
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
		setChanged();
		notifyObservers(this.temperature);
	}
	public double getHumidity() {
		return humidity;
	}
	public void setHumidity(double humidity) {
		this.humidity = humidity;
		setChanged();
		notifyObservers(this.humidity);
		//System.out.println(this.humidity);
		//System.out.println("a");
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}

	
	
	
	public Model(){
		this.humidity = humidity;
		this.temperature = temperature;
		this.dew = dew;
		this.order = order;
	
		
		
	}

	

	
}
