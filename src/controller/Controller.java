package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.ConnectionArduino;
import model.Model;
import view.View;
public class Controller {
	
	private final View  view;
	private final Model model;
	
	public Controller(final View view, final Model model){
		this.view = view;
		this.model = model;
		this.model.addObserver(this.view);
		this.view.addListener(new AddListener());
	}

	public void launch() throws Exception {	
		this.view.setVisible(true);
		ConnectionArduino connectionArduino = new ConnectionArduino();
		connectionArduino.connect("COM4");
		//connectionArduino.initialize();
	}
	
	class AddListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				String order = view.getOrderTextField().getText();
				view.getOrderTempLabel().setText("Température consigne = " + order + "°C");
			} catch (Exception e2) {
				// TODO: handle exception
				
			}
		}
	}
	
}