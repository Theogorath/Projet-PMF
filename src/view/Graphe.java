package view;

import javax.swing.UIManager;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

public class Graphe extends ChartPanel{
	
	private static final long serialVersionUID = -3472576582851665670L;
	private JFreeChart lineChart;
	private ChartPanel chartPanel;
	
	
	public Graphe(JFreeChart chart) {
		super(chart);
		lineChart = chart;
		chartPanel = new ChartPanel(lineChart);
		chartPanel.setBorder(UIManager.getBorder("TextField.border"));
		chartPanel.setSize(350, 250);
		chartPanel.setLocation(10, 300);
		
	}

	public JFreeChart getLineChart() {
		return lineChart;
	}

	public void setLineChart(JFreeChart lineChart) {
		this.lineChart = lineChart;
	}

	public ChartPanel getChartPanel() {
		return chartPanel;
	}

	public void setChartPanel(ChartPanel chartPanel) {
		this.chartPanel = chartPanel;
	}
	
	
	
	
	
}
