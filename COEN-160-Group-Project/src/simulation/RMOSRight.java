package simulation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class RMOSRight extends JPanel{
	private JLabel title;
	private JPanel chart1, chart2; //Will end up being private PieChart chart1, chart2;

	
	RMOSRight(){
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setPreferredSize(new Dimension(728,430));
		//this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
		
		title = new JLabel("RMOS Admin Panel", SwingConstants.CENTER);
		title.setFont(new Font("Sans Serif", Font.PLAIN, 30));
		title.setPreferredSize(new Dimension(400, 50));
		
		chart1 = new JPanel();
		chart1.setPreferredSize(new Dimension(350, 360));
		chart1.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
		
		chart2 = new JPanel();
		chart2.setPreferredSize(new Dimension(350, 360));
		chart2.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
		
		this.add(title);
		this.add(chart1);
		this.add(chart2);
		// Generate and curate analytics (loop)?
	}
}