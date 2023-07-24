package com.dlalaweb.phones.details;

import java.util.Observable;
import java.util.Observer;

public class DetailsController extends Observable implements Observer {
	private DetailsView view;
	
	public DetailsController() {
		this.view =  new DetailsView();
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	public DetailsView getView() {
		return view;
	}


	
	

}
