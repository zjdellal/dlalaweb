package com.dlalaweb.phones.details.onglet.reparations;

import java.util.List;
import java.util.Observable;

import com.dlalacore.dlala.entities.Fiche;
import com.dlalacore.dlala.entities.Phone;
import com.dlalaweb.service.impl.FicheService;
import com.vaadin.ui.Grid.ItemClick;
import com.vaadin.ui.Grid.SelectionMode;

public class ReparationsPresenter extends Observable {
	private ReparationsView		view;
	private ReparationsModel	model;
	private FicheService			service;

	public ReparationsPresenter(List<Fiche> historeparations) {
		view = new ReparationsView();
		model = new ReparationsModel();
		model.setFiches(historeparations);

		setComponents(historeparations);
		setlisteners();

	}

	public ReparationsPresenter(Phone phone) {
		view = new ReparationsView();
		model = new ReparationsModel();
		service = new FicheService();
		;
		model.setFiches(service.getFichesByIdPhone(phone.getId()));

		setComponents(model.getFiches());
		setlisteners();

	}

	private void setlisteners() {
		onClickItemGrid();
		view.getBtnAjouter().addClickListener(e -> onBtnAjouterClicked());
	}

	private void onBtnAjouterClicked() {
		setChanged();
		notifyObservers("ajouter fiche");

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
