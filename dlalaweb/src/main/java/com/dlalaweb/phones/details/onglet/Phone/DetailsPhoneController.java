package com.dlalaweb.phones.details.onglet.Phone;

import java.util.Observable;
import java.util.Observer;

public class DetailsPhoneController extends Observable implements Observer{
	private DetailsPhonePresenter detailsPhonePresenter;
	
	public DetailsPhoneController() {
		detailsPhonePresenter =  new DetailsPhonePresenter();
		detailsPhonePresenter.addObserver(this);
}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
