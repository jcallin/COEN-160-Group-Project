package simulation;

class ObservableDelegate extends java.util.Observable {
	
	/* Open up the access permissions of these methods
	 * Defined as protected methods in super class */
	
	public void clearChanged() { super.clearChanged();}
	
	public void setChanged() { super.setChanged();}
		
}
