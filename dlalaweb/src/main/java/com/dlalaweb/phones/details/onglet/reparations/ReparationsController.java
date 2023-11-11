package com.dlalaweb.phones.details.onglet.reparations;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.dlalacore.dlala.entities.Fiche;
import com.dlalacore.dlala.entities.Phone;
import com.dlalaweb.phones.details.onglet.reparations.details.DetailsReparationPresenter;
import com.vaadin.ui.UI;

public class ReparationsController extends Observable implements Observer {
	private ReparationsPresenter				reparationsPresenter;
	private DetailsReparationPresenter	detailsRepPresenter;
	private Phone												phoneSelected;
	private List<Fiche> histoReparations;

	public ReparationsController(List<Fiche> historeparations, Phone phone) {
		phoneSelected = phone;
		this.histoReparations = historeparations;
		reparationsPresenter = new ReparationsPresenter(historeparations);
		reparationsPresenter.addObserver(this);

	}
	
	public ReparationsController( Phone phone) {
		phoneSelected = phone;
	
		reparationsPresenter = new ReparationsPresenter(phoneSelected);
		reparationsPresenter.addObserver(this);

	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof ReparationsPresenter) {
			if (arg instanceof Fiche) {
				Fiche fiche = (Fiche) arg;
				// setter le model
				detailsRepPresenter = new DetailsReparationPresenter(fiche, phoneSelected);
				detailsRepPresenter.addObserver(this);
				UI.getCurrent().addWindow(detailsRepPresenter.getView().getWinContent());
			}
			if (arg.equals("ajouter fiche")) {
				detailsRepPresenter = new DetailsReparationPresenter(phoneSelected);
				UI.getCurrent().addWindow(detailsRepPresenter.getView().getWinContent());
			}
		}
		if(o instanceof DetailsReparationPresenter) {
			if(arg.equals("close window")) {
				
//				reparationsPresenter = new ReparationsPresenter(phoneSelected);
//				reparationsPresenter.addObserver(this);
				setChanged();
				notifyObservers("refresh");
			}
		}
		

	}

	public ReparationsPresenter getReparationsPresenter() {
		return reparationsPresenter;
	}

	public DetailsReparationPresenter getDetailsRepPresenter() {
		return detailsRepPresenter;
	}

}
