package simulation;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observer;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PieChart extends JPanel /*implements Observer*/ {
	private RCM rcm;
	
	public PieChart(RCM _rcm){
		rcm = _rcm;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		drawPieChart(g);
	    drawLegend(g);
	}
	
	private void drawPieChart(Graphics g){
		//double totalWeight = getTotalWeight();
		double percentage = 0.0;
		double startAngle = 0.0;
		double arcAngle = 0.0;
		
		//for(int i = 0; i < this.rcm.dict.size(); i++){
		//	
		//}
		
	}
	
	private void drawLegend(Graphics g){
		
	}
}
