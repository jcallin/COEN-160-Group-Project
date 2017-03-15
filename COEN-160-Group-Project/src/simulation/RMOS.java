package simulation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

@SuppressWarnings("serial")
public class RMOS extends JPanel implements ActionListener{
	RMOSLeft leftPanel; //Admin Control
	RMOSRight rightPanel; //Data Visualization of RCMs
	public RMOS(RCM r1, RCM r2){
		// Create a flow layout for the panel
		super(new FlowLayout(FlowLayout.LEFT));
		// Size of the RMOS should be full width and 1/2 the height of the window
		
		setPreferredSize(new Dimension(1200,450));
		
		//setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		leftPanel = new RMOSLeft(r1, r2);
		rightPanel = new RMOSRight(r1, r2, leftPanel);
				
		// Left panel is for RCM/RMOS administration
		this.add(leftPanel);
		// Right panel is for visual analytics
		this.add(rightPanel);
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}