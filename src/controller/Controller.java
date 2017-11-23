package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.ConnectionArduino;
import model.Model;
import view.View;
public class Controller {
	//private String order = "0";
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
		ConnectionArduino connectionArduino = new ConnectionArduino(this.model);
		
		connectionArduino.initialize(/*this.order*/);
	}
	
	class AddListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				System.out.println("TEEEEEEEEEEEST");
				//order = view.getOrderTextField().getText();
				//System.out.println(order);
				model.setOrder(Integer.valueOf(view.getOrderTextField().getText()));
				System.out.println("Order (model) = " + model.getOrder());
				view.getOrderTempLabel().setText("Température consigne = " + view.getOrderTextField().getText() + "°C");
			} catch (Exception e2) {
				// TODO: handle exception
				
			}
		}
	}

	/*public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}*/

	
}