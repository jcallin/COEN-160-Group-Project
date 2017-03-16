package simulation;

import java.util.Observable;

//Observable class for the Observer/Observable MVC functionality

class ObservableDelegate extends Observable {
	
	public void clearChanged() { 
		super.clearChanged();
	}
	
	public void setChanged() { 
		super.setChanged();
	}
		
}
