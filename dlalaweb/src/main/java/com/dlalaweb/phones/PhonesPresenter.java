package com.dlalaweb.phones;

import java.util.List;
import java.util.Observable;

import com.dlalacore.dlala.entities.Phones;
import com.dlalacore.dlala.entities.Utilisateur;
import com.dlalaweb.service.impl.PhonesService;
import com.vaadin.ui.UI;

public class PhonesPresenter extends Observable implements PhoneListener {
	private PhonesView		view;
	private PhonesModel		model;
	private PhonesService	service;

	public PhonesPresenter() {
		view = new PhonesView();
		model = new PhonesModel();
		this.model.setListener(this);
		service = new PhonesService();
		startService();

	}

	private void startService() {
		List<Phones> phones = service.findAll();
		model.setPhoneList(phones);

		Utilisateur user = (Utilisateur) UI.getCurrent().getSession().getAttribute("userSession");
		if(user != null)
		view.getLblCaption().setValue("Bien venue : " + user.getNom_utilisateur());
	}

	@Override
	public void onPhoneListChange() {
		buildGridItems();

	}

	private void buildGridItems() {
		view.getgPhones().removeAllColumns();
		view.getgPhones().setItems(model.getPhoneList());
		if (model.getPhoneList().size() <= 10) {
			view.getgPhones().setHeightByRows(model.getPhoneList().size());
		} else
			view.getgPhones().setHeightByRows(10);
		view.getgPhones().addColumn(Phones::getMarque).setCaption("Marque");
		view.getgPhones().addColumn(Phones::getModel).setCaption("Model");
		view.getgPhones().addColumn(Phones::getEtat).setCaption("État");
		view.getgPhones().addColumn(Phones::getPrixAchat).setCaption("Prix Achat");
		view.getgPhones().addColumn(Phones::getCotePhone).setCaption("Cote");

	}

	public PhonesView getView() {
		return view;
	}

	public PhonesModel getModel() {
		return model;
	}

}
