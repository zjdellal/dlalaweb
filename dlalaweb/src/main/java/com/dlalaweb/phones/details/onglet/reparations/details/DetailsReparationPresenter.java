package com.dlalaweb.phones.details.onglet.reparations.details;

import java.util.Observable;

import com.dlalacore.dlala.entities.Fiche;
import com.dlalaweb.phones.details.onglet.reparations.details.DetailsReparationModel.ListenerModelDetReparaton;

public class DetailsReparationPresenter extends Observable implements ListenerModelDetReparaton {
	private DetailsReparationView		view;
	private DetailsReparationModel	model;

	public DetailsReparationPresenter(Fiche fiche) {
		view = new DetailsReparationView();
		model = new DetailsReparationModel();
		model.setListener(this);
		model.setSelectedFiche(fiche);

	}

	@Override
	public void onFicheSelected() {
		Fiche selectedFiche = model.getSelectedFiche();
		view.getTxtTitre().setValue(selectedFiche.getTitre());
		view.getTxtDate().setValue(selectedFiche.getDate());
		view.getTxtCout().setValue(selectedFiche.getCout());
		view.getTxtDetail().setValue(selectedFiche.getDetails());

	}

	public DetailsReparationView getView() {
		return view;
	}
	
	
	

}
