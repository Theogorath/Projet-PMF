package controller;

import controller.Controller;
import model.Model;
import view.View;

public class main {

	public static void main(String[] args) {
		
		View view = new View();
		Model model = new Model();
		//model.addObserver(view);
		Controller controller = new Controller(view, model);
		//model.addObserver(view);
		controller.launch();

		 
		
		}
}