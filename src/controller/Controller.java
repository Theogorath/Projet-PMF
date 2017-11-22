package controller;
import model.Model;

import java.io.OutputStream;
import java.util.Enumeration;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import model.ConnectionArduino;
import view.View;
public class Controller {
	
	private final View  view;
	private final Model model;

	private OutputStream output;
	private static final int TIME_OUT = 2000;
	private static final int DATA_RATE = 9600;
	

	SerialPort serialPort;
	private static final String PORT_NAMES[] = {" /dev/tty.usbserial-A9007UX1 ", " /dev/ttyUSB0 ", 	"COM4",};

	
	
	public Controller(final View view, final Model model){
		this.view = view;
		this.model = model;
		this.model.addObserver(this.view);
	}

	public void launch(){	
		this.view.setVisible(true);
		ConnectionArduino connectionArduino = new ConnectionArduino(this.model);
		connectionArduino.initialize();
	}
	
	public void init() {
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
		String valeur = "12";
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
}