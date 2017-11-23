package model;

import java.io.IOException;
import java.io.InputStream;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class SerialReader implements Runnable, SerialPortEventListener{
	int c=0;
	
    InputStream in;
    Model model = new Model();
    public SerialReader ( InputStream in )
    {
        this.in = in;
    }
    
    	public synchronized void run(SerialPortEvent oEvent) {
    		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
    			try {
    				String inputLine=null;
    				if (ConnectionArduino.getInput().ready()) {
    					inputLine = input.readLine();

    					String [] chunks = inputLine.split(" , ");
    					
    					if (i==0) {
    						this.md.setHumidity(Double.parseDouble(inputLine))  ;
    						//System.out.println(this.md.getHumidity());
    						i=1;
    					}
    					else if (i==1) {
    						this.md.setTemperature(Double.parseDouble(inputLine))  ;
    						//this.md.setTemperaturelist(inputLine);
    						//System.out.println(this.md.getTemperature());
    						i=2;
    					}
    					else {
    						this.md.setDew(Double.parseDouble(inputLine))  ;
    						//System.out.println(Model.getDew());
    						i=0;
    					}
    				//	System.out.println(inputLine);

    					System.out.println(chunks[0] + " \t " + chunks[1] + " \t " + chunks[2] +  "\t »");
    				}

    			} catch (Exception e) {
    			//	System.err.println(e.toString());
    			}
    		}
    		// Ignore all the other eventTypes, but you should consider the other ones.
    	} 

	@Override
	public void serialEvent(SerialPortEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}