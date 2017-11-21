package controller;
import model.Model;
import model.ConnectionArduino;
import view.View;
public class Controller {
	private final View  view;
	private final Model model;


	public Controller(final View view, final Model model){
		this.view = view;
		this.model = model;
	}

	public void launch(){	
		this.view.setVisible(true);
		
		ConnectionArduino connectionArduino = new ConnectionArduino();
		connectionArduino.initialize();
		Thread t=new Thread() {
			public void run() {
				try {
					Thread.sleep(1000000);
					}catch (InterruptedException ie) {}
			}
		};
		t.start();
		System.out.println(" Started ");
		
	}
}