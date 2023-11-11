package com.dlalaweb.phones.details.onglet.reparations.details;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Observable;

import com.dlalacore.dlala.entities.Fiche;
import com.dlalacore.dlala.entities.Phone;
import com.dlalaweb.phones.details.onglet.reparations.details.DetailsReparationModel.ListenerModelDetReparaton;
import com.dlalaweb.service.impl.FicheService;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

public class DetailsReparationPresenter extends Observable implements ListenerModelDetReparaton {
	private DetailsReparationView		view;
	private DetailsReparationModel	model;
	private FicheService						service;
	private Binder<Fiche>						binder;

	public DetailsReparationPresenter(Fiche fiche, Phone phone) {
		view = new DetailsReparationView();
		model = new DetailsReparationModel();
		service = new FicheService();
		bindComponents();
		binder.setBean(model.getSelectedFiche());
		model.setListener(this);
		model.setSelectedFiche(fiche);
		model.setSelectedPhone(phone);
		binder.readBean(model.getSelectedFiche());
		setListeners();



	}

	public DetailsReparationPresenter(Phone phone) {
		view = new DetailsReparationView();
		model = new DetailsReparationModel();

		service = new FicheService();
		bindComponents();

		model.setListener(this);
		model.setSelectedPhone(phone);
		binder.setBean(model.getSelectedFiche());
		view.getTxtDate().setValue(LocalDate.now().format(DateTimeFormatter.ISO_DATE));

		setListeners();
		model.getSelectedFiche().setPhone(phone);
		binder.readBean(model.getSelectedFiche());
	}

	public void bindComponents() {

		binder = new Binder<>();
		binder.forField(view.getTxtCout()).withValidator(cout -> cout.matches("^[0-9]+"), "Saisie incorrecte")
		    .bind(Fiche::getCout, Fiche::setCout);
		binder.forField(view.getTxtDetail()).bind(Fiche::getDetails, Fiche::setDetails);
		binder.forField(view.getTxtDate()).bind(Fiche::getDate, Fiche::setDate);

	}

	private void setListeners() {
		view.getBtnEnregistrer().addClickListener(e -> onBtnSaveClicked());
		this.view.getWinContent().addCloseListener(e -> onWindoewClosed());

	}

	private void onBtnSaveClicked() {
		boolean isNew = false;
		try {
			binder.writeBean(model.getSelectedFiche());
			if (model.getSelectedFiche().getId() == null) {
				model.getSelectedFiche().setId(0);
				isNew = true;
			}

			service.save(model.getSelectedFiche());
			if (isNew)
				Notification.show("Fiche Ajoutée", ";) ", Type.WARNING_MESSAGE);
			else
				Notification.show("Modifié !", ":) ", Type.HUMANIZED_MESSAGE);

		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.getFieldValidationErrors();

		}

	}

	@Override
	public void onFicheSelected() {
		Fiche selectedFiche = model.getSelectedFiche();

		view.getWinContent().setCaption("<h2><b>" + selectedFiche.getTitre() + "</b></h2>");
		view.getTxtDate().setValue(selectedFiche.getDate());
		view.getTxtCout().setValue(selectedFiche.getCout());
		view.getTxtDetail().setValue(selectedFiche.getDetails());

	}

	public DetailsReparationView getView() {
		return view;
	}

	@Override
	public void onPhoneSelected() {

		Phone p = model.getSelectedPhone();
		if (p.getMarque() != null)
			view.getLblMarque().setValue(p.getMarque());
		if (p.getModel() != null)
			view.getLblModel().setValue(p.getModel());
		if (p.getNoModelPhone() != null)
			view.getLblNoModel().setValue(p.getNoModelPhone());
		model.getSelectedFiche().setTitre(view.getTxtTitre().getValue());
		// model.getSelectedFiche().setPhone(model.getSelectedPhone());

	}
	public void onWindoewClosed() {
		setChanged();
		notifyObservers("close window");
	}

}
