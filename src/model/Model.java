
package model;

import java.util.*;

public class Model {
	private double dew ;
	private double temperature ;
	private double humidity ;
	private double order ;
	private ArrayList<Double> temperaturelist = new ArrayList();
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
	public ArrayList getTemperaturelist() {
		return temperaturelist;
	}
	public void setTemperaturelist(double inputLine) {
		this.temperaturelist.add(inputLine);

	}
	
	public Model(){
		this.humidity = humidity;
		this.temperature = temperature;
		this.dew = dew;
		this.order = order;
		this.temperaturelist = temperaturelist;
		
		
	}
	
/*	public void set() {
	//	ConnectionArduino connectionArduino = new ConnectionArduino();
		
		int i = 0 ;
		if (i==0) {
			humidity=Double.parseDouble(connectionArduino.getInputLine());
			System.out.println(humidity);
			i=1;
		}
		
		else if (i==1) {
			temperature=Double.parseDouble(connectionArduino.getInputLine());
			System.out.println(temperature);
			i=2;
		}
		
		else {
			dew=Double.parseDouble(connectionArduino.getInputLine());
			System.out.println(dew);
			i=2;
		}
	}
	*/
	
}
