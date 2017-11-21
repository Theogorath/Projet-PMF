package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.util.Enumeration;

public class ConnectionArduino implements SerialPortEventListener{
	int i = 0 ;
	SerialPort serialPort;
	/** The port we’re normally going to use. */
	private static final String PORT_NAMES[] = {" /dev/tty.usbserial-A9007UX1 ", // Mac OS X
			" /dev/ttyUSB0 ", // Linux
			"COM4", // Windows
	};

	
	
	Model md=new Model();
	
	
	
	private BufferedReader input;
	private OutputStream output;
	private static final int TIME_OUT = 2000;
	private static final int DATA_RATE = 9600;

	public void initialize() {
		CommPortIdentifier portId = null;
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

		//First, Find an instance of serial port as set in PORT_NAMES.
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			for (String portName : PORT_NAMES) {
				if (currPortId.getName().equals(portName)) {
					portId = currPortId;
					break;
				}
			}
		}
		if (portId == null) {
			System.out.println(" Could not find COM port. ");
			return;
		}

		try {
			serialPort = (SerialPort) portId.open(this.getClass().getName(),
					TIME_OUT);
			serialPort.setSerialPortParams(DATA_RATE,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			// open the streams
			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			output = serialPort.getOutputStream();

			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}
	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	public synchronized void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				String inputLine=null;
				if (input.ready()) {
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


}
