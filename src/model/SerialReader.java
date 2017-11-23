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
                	String stockageHumidity = new String(buffer,0,len);
                	//this.model.setHumidity(Double.parseDouble(stockageHumidity));
                	c=1;
                }
                else if (c==1) {
                	c=2;
                	String stockageTemperature = new String(buffer,0,len);
                	//this.model.setHumidity(Double.parseDouble(stockageTemperature));
                }
                else if (c==2) {
                	String stockageMagnus = new String(buffer,0,len);
                	//this.model.setHumidity(Double.parseDouble(stockageMagnus));
                	c=0;
                }
            }
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }            
    }
}
