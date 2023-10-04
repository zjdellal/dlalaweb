package com.dlalaweb.phones.details;

import java.util.List;

import com.dlalacore.dlala.entities.Fiche;
import com.dlalacore.dlala.entities.Phone;

public class DetailsPhoneModel {
	private DetPhoneModelListener	listener;
	private Phone									selectedPhone;
	

	public DetailsPhoneModel() {

	}

	public DetPhoneModelListener getListener() {
		return listener;
	}

	public void setListener(DetPhoneModelListener listener) {
		this.listener = listener;
	}

	public Phone getSelectedPhone() {
		return selectedPhone;
	}

	public void setFiche(List<Fiche> fiche) {
		selectedPhone.setFiches(fiche);
		if(selectedPhone.getFiches()!= null && listener != null)
			listener.ifFicheExiste();
		
	}
	public void setSelectedPhone(Phone selectedPhone) {
		this.selectedPhone = selectedPhone;
		if (selectedPhone != null && listener != null) {
			listener.onPhoneSelected();

		}
	}

}
