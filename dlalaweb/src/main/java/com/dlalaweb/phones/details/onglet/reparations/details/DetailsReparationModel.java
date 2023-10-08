package com.dlalaweb.phones.details.onglet.reparations.details;

import com.dlalacore.dlala.entities.Fiche;
import com.dlalacore.dlala.entities.Phone;

public class DetailsReparationModel {

	private ListenerModelDetReparaton	listener;
	private Fiche											selectedFiche;
	private Phone selectedPhone ; 
	
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
	
	



	public Phone getSelectedPhone() {
		return selectedPhone;
	}



	public void setSelectedPhone(Phone selectedPhone) {
		if(selectedPhone != null) {
		this.selectedPhone = selectedPhone;
		if(listener != null )
			listener.onPhoneSelected();
		
		}
	}





	public interface ListenerModelDetReparaton {
		void onFicheSelected();
		void onPhoneSelected();
	}

}
