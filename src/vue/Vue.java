package vue;

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Vue extends JFrame{
	
	private static final long serialVersionUID = -3825360027604802902L;
	
	public Vue(){
		this.setTitle("Programme");
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.getContentPane().setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JFreeChart lineChart = ChartFactory.createLineChart("", "Temps", "Température", 
				createDataset(), PlotOrientation.VERTICAL, false, false, false);
		ChartPanel chartPanel = new ChartPanel(lineChart);
		chartPanel.setBorder(UIManager.getBorder("TextField.border"));
		chartPanel.setSize(350, 250);
		chartPanel.setLocation(10, 300);
		this.getContentPane().add(chartPanel);
	}
	
	public DefaultCategoryDataset createDataset(){
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(0, "Température", "30");
		dataset.addValue(10, "Température", "60");
		dataset.addValue(-10, "Température", "90");
		dataset.addValue(-5, "Température", "120");
		dataset.addValue(20, "Température", "150");
		return dataset;
	}
	
	
	public static void main(String[] args){
		Vue fenetre = new Vue();
		fenetre.setVisible(true);
		
	}
	
}
