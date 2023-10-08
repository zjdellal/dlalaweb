package com.dlalaweb.phones.details.onglet.reparations;

import java.util.List;
import java.util.Observable;

import com.dlalacore.dlala.entities.Fiche;
import com.dlalacore.dlala.entities.Phone;
import com.vaadin.ui.Grid.ItemClick;
import com.vaadin.ui.Grid.SelectionMode;

public class ReparationsPresenter extends Observable {
	private ReparationsView		view;
	private ReparationsModel	model;

	public ReparationsPresenter(List<Fiche> historeparations) {
		view = new ReparationsView();
		model = new ReparationsModel();
		model.setFiche(historeparations);

		setComponents(historeparations);
		onClickItemGrid();
	}

	private void onClickItemGrid() {
		this.getView().getgFiche().addItemClickListener(e -> onItemGridClicked(e));

	}

	private void onItemGridClicked(ItemClick<Fiche> e) {
		Fiche fiche = e.getItem();
		if (fiche != null) {
			setChanged();
			notifyObservers(fiche);
		}

	}

	private void setComponents(List<Fiche> historeparations) {
		if (!historeparations.isEmpty()) {

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

	public ReparationsView getView() {
		return view;
	}

}
