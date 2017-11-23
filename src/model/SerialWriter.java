package model;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

public class SerialWriter implements Runnable{
	OutputStream out;
    
    public SerialWriter ( OutputStream out )
    {
        this.out = out;
    }
    
    public void run ()
    {
        try
        {                
            int c = 0;
            
            Scanner sc = new Scanner(System.in);
           // c=sc.nextInt();
            
            while ( ( c=sc.nextInt()) > -1 )
            {
            	
            	//c=sc.nextInt();
            	
            	System.out.println(c);
                this.out.write(c);
            }                
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }            
    }
}
