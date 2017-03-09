//Github Remote setup Test

package simulation;

public class Item {
	private String name;
	private double value;
	// Upon constructor, the RMOS admin sets this from 1-10 to denote heavier items for weight generation
	private double averageWeight;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getAverageWeight() {
		return averageWeight;
	}

	public void setAverageWeight(double averageWeight) {
		this.averageWeight = averageWeight;
	}

	public Item(String _name, double _value, double _averageWeight){
		name = _name;
		value = _value;
		averageWeight = _averageWeight;
	}
	
}
