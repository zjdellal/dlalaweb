package com.dlalaweb.phones.details.onglet.reparations;

import java.util.List;

import com.dlalacore.dlala.entities.Fiche;

public class ReparationsModel {
	private List<Fiche>	fiches;
	private IFiche			listenerFiche;

	public List<Fiche> getFiches() {
		return fiches;
	}

	public void setFiches(List<Fiche> fiches) {
		this.fiches = fiches;
		if (listenerFiche != null)
			listenerFiche.onFicheSelected();
	}

	public IFiche getListenerFiche() {
		return listenerFiche;
	}

	public void setListenerFiche(IFiche listenerFiche) {
		this.listenerFiche = listenerFiche;
	}

	interface IFiche {
		void onFicheSelected();
	}

}
