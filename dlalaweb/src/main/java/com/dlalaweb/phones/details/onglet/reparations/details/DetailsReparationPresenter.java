package com.dlalaweb.phones.details.onglet.reparations.details;

import java.util.Observable;

import com.dlalacore.dlala.entities.Fiche;
import com.dlalacore.dlala.entities.Phone;
import com.dlalaweb.phones.details.onglet.reparations.details.DetailsReparationModel.ListenerModelDetReparaton;

public class DetailsReparationPresenter extends Observable implements ListenerModelDetReparaton {
	private DetailsReparationView		view;
	private DetailsReparationModel	model;

	public DetailsReparationPresenter(Fiche fiche, Phone phone) {
		view = new DetailsReparationView();
		model = new DetailsReparationModel();
		model.setListener(this);
		model.setSelectedFiche(fiche);
		model.setSelectedPhone(phone);

	}
	
	public DetailsReparationPresenter(Phone phone) {
		view = new DetailsReparationView();
		model = new DetailsReparationModel();
		
		model.setListener(this);
		model.setSelectedPhone(phone);

		setListeners();
		}

	private void setListeners() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFicheSelected() {
		Fiche selectedFiche = model.getSelectedFiche();
//		view.getTxtTitre().setValue(selectedFiche.getTitre());
		view.getWinContent().setCaption("<h2><b>"+selectedFiche.getTitre()+ "</b></h2>");
		view.getTxtDate().setValue(selectedFiche.getDate());
		view.getTxtCout().setValue(selectedFiche.getCout());
		view.getTxtDetail().setValue(selectedFiche.getDetails());

	}

	public DetailsReparationView getView() {
		return view;
	}

	@Override
	public void onPhoneSelected() {
		
		Phone p  =  model.getSelectedPhone();
		if(p.getMarque() != null)
			view.getLblMarque().setValue(p.getMarque());
		if(p.getModel()!= null)
			view.getLblModel().setValue(p.getModel());
		if(p.getNoModelPhone()!= null)
			view.getLblNoModel().setValue(p.getNoModelPhone());
//		if(p.get)
		
	}
	
	
	

}
