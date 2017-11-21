package vue;

import java.awt.Font;

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
import org.jfree.data.category.DefaultCategoryDataset;

public class Vue extends JFrame{
	
	private static final long serialVersionUID = -3825360027604802902L;
	private JLabel tempLabel;
	private JLabel humiLabel;
	private JLabel condenLabel;
	private JLabel tempConsigneLabel;
	private JLabel consigneLabel;
	private JTextField consigneTextField;
	private JButton validateButton;
	private JLabel etatConden;
	private JFreeChart lineChart;
	private ChartPanel chartPanel;
	
	private double humidite;
	private double temperature;
	private double temperatureConsigne;
	
	private Icon okImage = new ImageIcon("image\\index.png");
	private Icon warningImage = new ImageIcon("image\\index2.png");
	
	public Vue(){
		this.setTitle("Programme");
		this.setSize(800, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.getContentPane().setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tempLabel = new JLabel("Température = " + Double.toString(temperature) + "°C");
		tempLabel.setBounds(295, 10, 210, 20);
		tempLabel.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		this.getContentPane().add(tempLabel);
		
		humiLabel = new JLabel("Humidité = " + Double.toString(humidite) + "%");
		humiLabel.setBounds(20, 35, 150, 20);
		humiLabel.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		this.getContentPane().add(humiLabel);
		
		condenLabel = new JLabel("Risque de condensation :");
		condenLabel.setBounds(400, 300, 250, 20);
		condenLabel.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		this.getContentPane().add(condenLabel);
		
		tempConsigneLabel = new JLabel("Température consigne = " + Double.toString(temperatureConsigne) + "°C");
		tempConsigneLabel.setBounds(255, 35, 290, 20);
		tempConsigneLabel.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		this.getContentPane().add(tempConsigneLabel);
		
		consigneLabel = new JLabel("Consigne :");
		consigneLabel.setBounds(315, 60, 100, 20);
		consigneLabel.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		this.getContentPane().add(consigneLabel);
		
		consigneTextField = new JTextField();
		consigneTextField.setBounds(415, 60, 100, 20);
		this.getContentPane().add(consigneTextField);
		
		validateButton = new JButton("Valider");
		validateButton.setBounds(350, 85, 100, 25);
		validateButton.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		this.getContentPane().add(validateButton);
		
		etatConden = new JLabel();
		etatConden.setBounds(450, 330, 225, 225);
		etatConden.setIcon(okImage);
		this.getContentPane().add(etatConden);
		
		lineChart = ChartFactory.createLineChart("", "Temps", "Température", createDataset(), PlotOrientation.VERTICAL, false, false, false);
		chartPanel = new ChartPanel(lineChart);
		chartPanel.setBorder(UIManager.getBorder("TextField.border"));
		chartPanel.setSize(350, 260);
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
