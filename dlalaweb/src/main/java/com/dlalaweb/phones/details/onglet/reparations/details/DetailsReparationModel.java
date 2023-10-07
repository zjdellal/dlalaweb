package com.dlalaweb.phones.details.onglet.reparations.details;

import com.dlalacore.dlala.entities.Fiche;

public class DetailsReparationModel {

	private ListenerModelDetReparaton	listener;
	private Fiche											selectedFiche;
	
	public DetailsReparationModel() {
		
	}
	
	

	public ListenerModelDetReparaton getListener() {
		return listener;
	}



	public void setListener(ListenerModelDetReparaton listener) {
		this.listener = listener;
	}



	public Fiche getSelectedFiche() {
		return selectedFiche;
	}



	public void setSelectedFiche(Fiche selectedFiche) {
		this.selectedFiche = selectedFiche;
		if(listener != null && selectedFiche != null)
			listener.onFicheSelected();
			
	}



	public interface ListenerModelDetReparaton {
		void onFicheSelected();
	}

}
