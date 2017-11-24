package view;

import java.awt.BasicStroke;
import java.awt.Color;
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
	private JLabel tempImageLabel;
	private JLabel humiImageLabel;
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
	private Icon tempImage = new ImageIcon("image\\temerature.png");
	private Icon humiImage = new ImageIcon("image\\humidity.png");
	
	public View(){
		this.setTitle("Programme");
		this.setSize(1200, 800);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.getContentPane().setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.WHITE);
		
		tempLabel = new JLabel("Température = " + Double.toString(temperature) + "°C");
		tempLabel.setBounds(134, 98, 270, 20);
		tempLabel.setFont(new Font("Lucida Console", Font.BOLD, 18));
		this.getContentPane().add(tempLabel);
		
		humiLabel = new JLabel(Double.toString(humidity) + "%");
		humiLabel.setBounds(576, 125, 72, 28);
		humiLabel.setForeground(Color.WHITE);
		humiLabel.setFont(new Font("Lucida Console", Font.BOLD, 18));
		this.getContentPane().add(humiLabel);
		
		humiImageLabel = new JLabel();
		humiImageLabel.setBounds(486, 11, 250, 245);
		humiImageLabel.setIcon(humiImage);
		this.getContentPane().add(humiImageLabel);
		
		condenLabel = new JLabel("Risque de condensation");
		condenLabel.setBounds(852, 228, 287, 35);
		condenLabel.setFont(new Font("Lucida Console", Font.BOLD, 18));
		this.getContentPane().add(condenLabel);
		
		orderTempLabel = new JLabel("Température consigne = ");
		orderTempLabel.setBounds(134, 129, 342, 21);
		orderTempLabel.setFont(new Font("Lucida Console", Font.BOLD, 18));
		this.getContentPane().add(orderTempLabel);
		
		orderLabel = new JLabel("Consigne :");
		orderLabel.setBounds(570, 35, 100, 20);
		orderLabel.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		this.getContentPane().add(orderLabel);
		
		orderTextField = new JTextField();
		orderTextField.setBounds(287, 163, 112, 20);
		this.getContentPane().add(orderTextField);
		
		validateButton = new JButton("Valider");
		validateButton.setBounds(169, 161, 100, 23);
		validateButton.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		this.getContentPane().add(validateButton);
		
		stateConden = new JLabel();
		stateConden.setBounds(872, 19, 225, 225);
		stateConden.setIcon(okImage);
		this.getContentPane().add(stateConden);
		
		tempImageLabel = new JLabel();
		tempImageLabel.setBounds(-24, 19, 158, 237);
		tempImageLabel.setIcon(tempImage);
		this.getContentPane().add(tempImageLabel);
		
		dataset.addSeries(tempGraph);
		xylineChart = ChartFactory.createXYLineChart("", "Temps", "Température", dataset, PlotOrientation.VERTICAL, false, false, false);
		chartPanel = new ChartPanel(xylineChart);
		chartPanel.setBounds(0, 307, 1190, 462);
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
			tempGraph.remove(timer);
		}
		tempGraph.add(timer, temperature);
		timer++;
	}
	
	public double getHumidity() {
		return humidity;
	}

	public void setHumidity(double humidity) {
		this.humidity = humidity;
		humiLabel.setText(Double.toString(humidity) + "%");
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
			//System.out.println("Température = " + ((Model) obs).getTemperature());
			//System.out.println("Humidité = " + ((Model) obs).getHumidity());
		}
	}
	
}
