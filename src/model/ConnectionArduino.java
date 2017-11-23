package model;

import java.util.Scanner;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
public class ConnectionArduino{
	public ConnectionArduino(){
		super();
	}
	public boolean connect(String portName) throws Exception{
		CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        if ( portIdentifier.isCurrentlyOwned() )
        {
            return false;
        }
        else
        {
            CommPort commPort = portIdentifier.open(this.getClass().getName(),2000);
            if ( commPort instanceof SerialPort )
            {
                SerialPort serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
                InputStream in = serialPort.getInputStream();
                OutputStream out = serialPort.getOutputStream();
                (new Thread(new SerialReader(in))).start();
                (new Thread(new SerialWriter(out))).start();
                return true;
            }
            else
            {
                System.out.println("Error: Only serial ports are handled by this example.");
                return false;
            }
        } 
	}

}