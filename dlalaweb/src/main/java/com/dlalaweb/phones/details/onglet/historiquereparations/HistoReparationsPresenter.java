package com.dlalaweb.phones.details.onglet.historiquereparations;

import java.util.List;
import java.util.Observable;

import com.dlalacore.dlala.entities.Fiche;
import com.vaadin.ui.Grid.SelectionMode;

public class HistoReparationsPresenter extends Observable{
	private HistoreparationsView view;
	private HistoreparationsModel model;
	public HistoReparationsPresenter(List<Fiche> historeparations) {
		view =  new HistoreparationsView();
		model =  new HistoreparationsModel();
		model.setFiche(historeparations);
		
		
		setComponents(historeparations);
	}
	private void setComponents(List<Fiche> historeparations) {
		if(!historeparations.isEmpty()) {
			
				view.getgFiche().removeAllColumns();
				view.getgFiche().setSelectionMode(SelectionMode.SINGLE);
				view.getgFiche().setItems(historeparations);
				if (historeparations.size() <= 10) {
					view.getgFiche().setHeightByRows(historeparations.size());
				} else
					view.getgFiche().setHeightByRows(10);
				view.getgFiche().addColumn(Fiche::getTitre).setCaption("Titre");
				view.getgFiche().addColumn(Fiche::getDate).setCaption("Date");
				view.getgFiche().addColumn(Fiche::getCout).setCaption("Cout");
				view.getgFiche().addColumn(Fiche::getDetails).setCaption("Détail");
				
		}
		
	}
	public HistoreparationsView getView() {
		return view;
	}
	


	

}
