package model;

import java.io.IOException;
import java.io.InputStream;

public class SerialReader implements Runnable {
	int c=0;
    InputStream in;
    Model model = new Model();
    public SerialReader ( InputStream in )
    {
        this.in = in;
    }
    public void run ()
    {
        byte[] buffer = new byte[1024];
        int len = -1;
        try
        {
            while ( ( len = this.in.read(buffer)) > -1 )
            {
                if (c==0) {
                	c=1;
                	this.model.setHumidity(new String(buffer,0,len));
                	//System.out.print(stockageHumidity);
                }
                else if (c==1) {
                	c=2;
                	this.model.setTemperature(new String(buffer,0,len));
                	//System.out.print(stockageTemperature);
                }
                else if (c==2) {
                	c=0;
                	this.model.setDew(new String(buffer,0,len));
                	//System.out.print(stockageMagnus);
                }
            }
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }            
    }
}