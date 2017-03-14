package simulation;

import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Class TextFieldDemo, The main window for the application, holding the 1 RCM and 2 RMOS
 * @author Julian Callin
 */
@SuppressWarnings("serial")
class Scene extends JFrame {
	// Declarations
	private RMOS RMOS;
	private RCM RCM1, RCM2;
	
    public Scene(String name) {
        super(name);
		setSize(1250, 950);
        
		Container container = getContentPane();
		container.setLayout(new FlowLayout());
         
		// Default items accepted by the RCM
		ArrayList<Item> items = new ArrayList<Item>();
		items.add(new Item("Aluminum", 0.75, 6));
		items.add(new Item("Copper", 1.10, 8));
		
        // Create the RMOS and RCM components
		RCM1 = new RCM("1", "Swig", 100.00, 100.00, items);
        RCM2 = new RCM("2", "Bannan", 100.00, 100.00, items);
		RMOS = new RMOS(RCM1, RCM2);
         
        //Add buttons to the experiment layout
        container.add(RMOS);
        container.add(RCM1);
        container.add(RCM2);
        
        //Left to right component orientation is selected by default
        container.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        
        setVisible(true);
    }
    
	public static void main(String[] args) {
		/**
		 * Run the GUI codes on Event-Dispatching thread for thread safety
		 */
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Scene scene = new Scene("RMOS/RCM Manager");
				scene.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
	}
}
