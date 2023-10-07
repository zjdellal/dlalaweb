package com.dlalaweb.phones.details.onglet.Phone;

import java.util.Observable;
import java.util.Observer;

import com.dlalaweb.phones.details.onglet.reparations.ReparationsController;

public class DetailsPhoneController extends Observable implements Observer {
	private DetailsPhonePresenter						detailsPhonePresenter;
	private ReparationsController	histReparation;

	public DetailsPhoneController() {
		detailsPhonePresenter = new DetailsPhonePresenter();
		detailsPhonePresenter.addObserver(this);
//		histReparation = new ReparationsController();
//		histReparation.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}
