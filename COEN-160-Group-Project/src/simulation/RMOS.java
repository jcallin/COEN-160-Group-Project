package simulation;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RMOS extends JPanel implements ActionListener{
	RMOSLeft leftPanel;
	RMOSRight rightPanel;
	public RMOS(RCM r1, RCM r2){
		// Create a flow layout for the panel
		super(new FlowLayout(FlowLayout.LEFT));
		// Size of the RMOS should be full width and 1/2 the height of the window
		setPreferredSize(new Dimension(1200,450));
		//setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		leftPanel = new RMOSLeft(r1, r2);
		rightPanel = new RMOSRight();
		// Left panel is for RCM/RMOS administration
		this.add(leftPanel);
		// Right panel is for visual analytics
		this.add(rightPanel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}