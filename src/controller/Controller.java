package controller;
import model.Model;
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
	}
}