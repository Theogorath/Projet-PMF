
package model;

import java.util.*;

public class Model extends Observable {
	private String dew ;
	private String temperature ;
	private String humidity ;
	private String order ;

	public String getDew() {
		return dew;
	}
	public void setDew(String dew) {
		this.dew = dew;
		setChanged();
		notifyObservers(this.dew);
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
		setChanged();
		notifyObservers(this.temperature);
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
		setChanged();
		notifyObservers(this.humidity);
		System.out.println(this.humidity);
		System.out.println("a");
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}

	
	
	
	public Model(){
		this.humidity = humidity;
		this.temperature = temperature;
		this.dew = dew;
		this.order = order;
	
		
		
	}

	

	
}
