package com.dlalaweb.phones;

import java.util.List;
import java.util.Observable;

import com.dlalacore.dlala.entities.Phone;
import com.dlalacore.dlala.entities.Utilisateur;
import com.dlalaweb.service.impl.PhonesService;
import com.vaadin.ui.Grid.ItemClick;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.components.grid.ItemClickListener;
import com.vaadin.ui.UI;

public class PhonesPresenter extends Observable implements PhoneListener {
	private PhonesView		view;
	private PhonesModel		model;
	private PhonesService	service;

	public PhonesPresenter() {
		view = new PhonesView();
		model = new PhonesModel();
		SetListenersComponents();
		this.model.setListener(this);
		service = new PhonesService();
		startService();

	}

	private void SetListenersComponents() {
		this.view.getgPhones().addItemClickListener(e -> onItemGridClicked(e));
		this.view.getBtnAjouter().addClickListener(e -> onBtnAjouterClicked());

	}

	private void onBtnAjouterClicked() {
		setChanged();
		notifyObservers("Ajouter téléphone");

	}

	/**
	 * méthode listener item on grid
	 * 
	 * @param e
	 */

	private void onItemGridClicked(ItemClick<Phone> e) {
		Phone phone = e.getItem();
		if (phone != null) {
			setChanged();
			notifyObservers(phone);
		}

	}

	private void startService() {
		List<Phone> phones = service.findAll();
		model.setPhoneList(phones);

		Utilisateur user = (Utilisateur) UI.getCurrent().getSession().getAttribute("userSession");
		if (user != null)
			view.getLblCaption().setValue("Bien venue : " + user.getNom_utilisateur());
	}

	@Override
	public void onPhoneListChange() {
		buildGridItems();

	}

	private void buildGridItems() {
		view.getgPhones().removeAllColumns();
		view.getgPhones().setSelectionMode(SelectionMode.SINGLE);
		view.getgPhones().setItems(model.getPhoneList());
		if (model.getPhoneList().size() <= 10) {
			view.getgPhones().setHeightByRows(model.getPhoneList().size());
		} else
			view.getgPhones().setHeightByRows(10);
		view.getgPhones().addColumn(Phone::getMarque).setCaption("Marque");
		view.getgPhones().addColumn(Phone::getModel).setCaption("Model");
		view.getgPhones().addColumn(Phone::getEtat).setCaption("État");
		view.getgPhones().addColumn(Phone::getPrixAchat).setCaption("Prix Achat");
		view.getgPhones().addColumn(Phone::getCotePhone).setCaption("Cote");

	}

	public PhonesView getView() {
		return view;
	}

	public PhonesModel getModel() {
		return model;
	}

}
