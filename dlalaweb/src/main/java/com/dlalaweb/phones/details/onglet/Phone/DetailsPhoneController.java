package com.dlalaweb.phones.details.onglet.Phone;

import java.util.Observable;
import java.util.Observer;

import com.dlalaweb.phones.details.onglet.historiquereparations.HistoriqueReparationsController;

public class DetailsPhoneController extends Observable implements Observer {
	private DetailsPhonePresenter						detailsPhonePresenter;
	private HistoriqueReparationsController	histReparation;

	public DetailsPhoneController() {
		detailsPhonePresenter = new DetailsPhonePresenter();
		detailsPhonePresenter.addObserver(this);
		histReparation = new HistoriqueReparationsController();
		histReparation.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}
