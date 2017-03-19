package simulation;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class RMOSRight extends JPanel implements ActionListener, Observer{
	private JLabel title;
	private JButton updateButton;
	private PieChart chart1, chart2;
	private RMOSLeft RMOSleft;
	
	RMOSRight(RCM r1, RCM r2, RMOSLeft left){
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setPreferredSize(new Dimension(728,430));
		//this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
		
		RMOSleft = left;
		
		RMOSleft.addObserver(this);
		
		
		title = new JLabel("RMOS Admin Panel", SwingConstants.CENTER);
		title.setFont(new Font("Sans Serif", Font.PLAIN, 40));
		title.setPreferredSize(new Dimension(450, 50));
		
		updateButton = new JButton("Update Analytics");
		updateButton.setPreferredSize(new Dimension(150, 30));
		updateButton.addActionListener(this);		
		
		//RCM 1's PieChart/Panel Container
		chart1 = new PieChart(r1);
		chart1.setPreferredSize(new Dimension(350, 350));
		//chart1.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
		this.add(chart1);
		
		chart2 = new PieChart(r2);
		chart2.setPreferredSize(new Dimension(350, 360));
		//chart2.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
		this.add(chart2);
		
		this.add(title);
		this.add(updateButton);
		this.add(chart1);
		this.add(chart2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.updateButton){
			chart1.repaint();
			chart2.repaint();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		this.chart1.repaint();
		this.chart2.repaint();		
	}
}