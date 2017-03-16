package simulation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PieChart extends JPanel {
	private RCM rcm;
	private Map<String, Double> itemTotals;
	private Map<String, Color> colorMap;
	private Map<String, String> percentages;
	private File fileName;
	private JLabel title;	
	
	public PieChart(RCM _rcm){
		rcm = _rcm;
		fileName = new File("RCM" + this.rcm.getId()+ ".txt"); 
		itemTotals = new HashMap<String, Double>();
		colorMap = new HashMap<String, Color>();
		percentages = new HashMap<String, String>();
		
		title = new JLabel("RCM: " + this.rcm.getId());
		title.setFont(new Font("New Courier", Font.ITALIC, 22));
		title.setPreferredSize(new Dimension(340, 30));
		this.add(title);
	}
	
	public void loadFileIntoMap(){
		itemTotals.clear();
		//colorMap.clear();
		percentages.clear();
		Scanner freader;
		try {
			freader = new Scanner(fileName);
			String line = "";
			double tmpWeight = 0;
			double lineWeight = 0;
			
			while (freader.hasNextLine()) {
				line = freader.nextLine();
				String []tokenArray = line.split("\\s");
				lineWeight = Double.parseDouble(tokenArray[3]);
				
				if(this.itemTotals.containsKey(tokenArray[2])){ //Map already contains the item, edit current Key->Value pair
					tmpWeight = itemTotals.get(tokenArray[2]);
					itemTotals.put(tokenArray[2], lineWeight + tmpWeight);
				}
				else{ //Map does not contain the item, create new Key->Value pair
					itemTotals.put(tokenArray[2], lineWeight);
				}
				
			}

		    freader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//toString method to test loadFile method -- working as of 3:36pm on Tuesday
	public void toStrings(){
		for (String name: this.itemTotals.keySet()){
            String key = name;
            String value = itemTotals.get(name).toString();  
            System.out.println(key + " " + value); 
		}
	}
	
	public void paintComponent(Graphics g){
		loadFileIntoMap();
		super.paintComponent(g);
		drawPieChart(g);
	    drawLegend(g);
	}
	
	private void drawPieChart(Graphics g){
		double totalWeight = getTotalWeight();
		double percentage = 0.0;
		int startAngle = 0;
		int arcAngle = 0;
		
		for (String name: this.itemTotals.keySet()){
			
			//Percentage of total weight
	         percentage = itemTotals.get(name)/totalWeight;
	         
	         //Load percentages into percentages map
	         percentages.put(name, new DecimalFormat("##.##").format(percentage*100));

	         //Arc Angle
	         arcAngle = (int)Math.round(percentage*360);

	         //Set Color and load color into colorMap hashmap to be used by Draw Legend
	         if(!(colorMap.containsKey(name))){
	        	 Color tmp = getRandomColor();
	        	 colorMap.put(name, tmp);
	        	 g.setColor(colorMap.get(name));
	         }
	         else{
	        	 g.setColor(colorMap.get(name));
	         }

	         // draw Account pie wedge
	         g.fillArc(5, 30, 280, 280, startAngle, arcAngle);
	         
	         startAngle += arcAngle;
		}
		
	}
	
	private void drawLegend( Graphics g ) {
		// create Font for Account name
		Font font = new Font( "Times New Roman", Font.BOLD, 12 );
		g.setFont(font);

		// get FontMetrics for calculating offsets and positioning descriptions
		FontMetrics metrics = getFontMetrics(font);
		int ascent = metrics.getMaxAscent();
		int offsetY = ascent + 5;
		int verticalPos = 17;
		int horizontalPos = 235;
		// draw description for each Account	
		for (String name: this.itemTotals.keySet()){
			verticalPos++;
			// draw Account color swatch at next offset
			g.setColor(colorMap.get(name));
			g.fillRect(horizontalPos, offsetY*verticalPos, ascent, ascent );

			// draw Account name next to color swatch
			g.setColor(Color.black);
			g.drawString(name + "-" + percentages.get(name) + "%", horizontalPos+15, offsetY * verticalPos + ascent );
	      }
	   }
	
	public double getTotalWeight(){
		double total = 0;
		for(String name: this.itemTotals.keySet()){
            Double value = itemTotals.get(name); 
            total += value;
		}
		return total;
	}
	
	private Color getRandomColor() {
		// calculate random red, green and blue values
		int red = (int)(Math.random()*256);
		int green = (int)(Math.random()*256);
		int blue = (int)(Math.random()*256);

	    return new Color(red, green, blue);
	}
}
