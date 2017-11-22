package test;


import gnu.io.*;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;

public class Communicator {
	
	SerialPort serialPort;
	/** The port we’re normally going to use. */
	private static final String PORT_NAMES[] = {" /dev/tty.usbserial-A9007UX1 ", // Mac OS X
			" /dev/ttyUSB0 ", // Linux
			"COM4", // Windows
	};
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
			output = serialPort.getOutputStream();

			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}
	public void write(){
		char flag = 0;
		String valeur = "10";
		try
        {     	
        	if (valeur.length()==1) {
        		valeur = "000"+ valeur;
        	} else if (valeur.length()==2) {
        		valeur = "00"+ valeur;
        	} else if (valeur.length()==3) {
        		valeur = "0"+ valeur;
        	}
        	
        	char[] valeurChar = valeur.toCharArray();
        	
        	//On envoi le message
        	char a = '~';
        	int c = a;
            this.output.write(c);
            output.flush();
            a = flag;
            c = a;
            this.output.write(c);
            output.flush();
            a = valeurChar[0];
            c = a;
            this.output.write(c);
            output.flush();
            a = valeurChar[1];
            c = a;
            this.output.write(c);
            output.flush();
            a = valeurChar[2];
            c = a;
            this.output.write(c);
            output.flush();
            a = valeurChar[3];
            c = a;
            this.output.write(c);
            output.flush();
            a = '$';
            c = a;
            this.output.write(c);
            output.flush();
        }
        catch (Exception e)
        {
            System.out.println("Impossible d'écrire la donnée.");
            e.printStackTrace();
        }
    }
public void main(){
	Communicator communicator=new Communicator();
	communicator.initialize();
	communicator.write();
}
}