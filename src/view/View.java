package view;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import model.Model;

public class View extends JFrame implements Observer{
	
	private static final long serialVersionUID = -3825360027604802902L;
	private JLabel tempLabel;
	private JLabel humiLabel;
	private JLabel condenLabel;
	private JLabel orderTempLabel;
	private JLabel orderLabel;
	private JTextField orderTextField;
	private JButton validateButton;
	private JLabel stateConden;
	private JFreeChart xylineChart;
	private ChartPanel chartPanel;
	private XYSeriesCollection dataset = new XYSeriesCollection();
	private XYSeries tempGraph = new XYSeries("");
	private XYPlot plot;
	private XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
	
	private double humidity;
	private double temperature;
	private int timer = 0;
	
	private Icon okImage = new ImageIcon("image\\index.png");
	private Icon warningImage = new ImageIcon("image\\index2.png");
	
	public View(){
		this.setTitle("Programme");
		this.setSize(800, 450);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.getContentPane().setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tempLabel = new JLabel("Température = " + Double.toString(temperature) + "°C");
		tempLabel.setBounds(295, 35, 210, 20);
		tempLabel.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		this.getContentPane().add(tempLabel);
		
		humiLabel = new JLabel("Humidité = " + Double.toString(humidity) + "%");
		humiLabel.setBounds(20, 35, 150, 20);
		humiLabel.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		this.getContentPane().add(humiLabel);
		
		condenLabel = new JLabel("Risque de condensation :");
		condenLabel.setBounds(400, 150, 250, 20);
		condenLabel.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		this.getContentPane().add(condenLabel);
		
		orderTempLabel = new JLabel("Température consigne = ");
		orderTempLabel.setBounds(255, 60, 290, 20);
		orderTempLabel.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		this.getContentPane().add(orderTempLabel);
		
		orderLabel = new JLabel("Consigne :");
		orderLabel.setBounds(570, 35, 100, 20);
		orderLabel.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		this.getContentPane().add(orderLabel);
		
		orderTextField = new JTextField();
		orderTextField.setBounds(670, 35, 100, 20);
		this.getContentPane().add(orderTextField);
		
		validateButton = new JButton("Valider");
		validateButton.setBounds(620, 60, 100, 25);
		validateButton.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		this.getContentPane().add(validateButton);
		
		stateConden = new JLabel();
		stateConden.setBounds(450, 180, 225, 225);
		stateConden.setIcon(okImage);
		this.getContentPane().add(stateConden);
		
		dataset.addSeries(tempGraph);
		xylineChart = ChartFactory.createXYLineChart("", "Temps", "Température", dataset, PlotOrientation.VERTICAL, false, false, false);
		chartPanel = new ChartPanel(xylineChart);
		chartPanel.setBorder(UIManager.getBorder("TextField.border"));
		chartPanel.setSize(350, 260);
		chartPanel.setLocation(10, 150);
		plot = xylineChart.getXYPlot();
		renderer.setSeriesStroke(0, new BasicStroke(2.0f));
		plot.setRenderer(renderer);
		this.getContentPane().add(chartPanel);
		
	}
	
	public void fillDataset(){
		if(tempGraph.getItemCount() > 20){
			if(timer == 21){
				timer = 0;
			}
			//tempGraph.add(timer, temperature);
			tempGraph.remove(timer);
		} /*else {
			tempGraph.add(timer, temperature);
		}*/
		tempGraph.add(timer, temperature);
		timer++;
	}
	
	public double getHumidity() {
		return humidity;
	}

	public void setHumidity(double humidity) {
		this.humidity = humidity;
		humiLabel.setText("Humidité = " + String.valueOf(this.humidity) + "%");
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
		tempLabel.setText("Température = " + String.valueOf(this.temperature) + "°C");
	}
	
	public JLabel getOrderTempLabel() {
		return orderTempLabel;
	}

	public JTextField getOrderTextField() {
		return orderTextField;
	}

	public JButton getValidateButton() {
		return validateButton;
	}
	
	public void addListener(ActionListener listenForAction){
		this.validateButton.addActionListener(listenForAction);
		this.orderTextField.addActionListener(listenForAction);
	}

	@Override
	public void update(Observable obs, Object obj) {
		// TODO Auto-generated method stub
		if(obs instanceof Model){
			setTemperature(((Model) obs).getTemperature());
			fillDataset();
			setHumidity(((Model) obs).getHumidity());
			if(((Model) obs).getDew() >= temperature){
				stateConden.setIcon(warningImage);
			}else{
				stateConden.setIcon(okImage);
			}
			System.out.println("Température = " + ((Model) obs).getTemperature());
			System.out.println("Humidité = " + ((Model) obs).getHumidity());
		}
	}
	
}
