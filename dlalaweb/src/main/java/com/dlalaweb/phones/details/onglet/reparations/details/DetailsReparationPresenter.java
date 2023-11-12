package com.dlalaweb.phones.details.onglet.reparations.details;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Observable;

import com.dlalacore.dlala.entities.Fiche;
import com.dlalacore.dlala.entities.Phone;
import com.dlalaweb.phones.details.onglet.reparations.details.DetailsReparationModel.ListenerModelDetReparaton;
import com.dlalaweb.service.impl.FicheService;
import com.dlalaweb.utils.dialogconfirmation.DialogConfirmation;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Notification.Type;

public class DetailsReparationPresenter extends Observable implements ListenerModelDetReparaton {
	private DetailsReparationView		view;
	private DetailsReparationModel	model;
	private FicheService						service;
	private Binder<Fiche>						binder;
	private boolean									isvalid	= false, hasChange = false;

	public DetailsReparationPresenter(Fiche fiche, Phone phone) {
		view = new DetailsReparationView();
		model = new DetailsReparationModel();
		service = new FicheService();
		bindComponents();
		setListeners();
		binder.setBean(model.getSelectedFiche());
		model.setListener(this);
		model.setSelectedFiche(fiche);
		model.setSelectedPhone(phone);

		// binder.setBean(model.getSelectedFiche());

		// binder.readBean(model.getSelectedFiche());

		view.getTxtTitre().setReadOnly(true);
		view.getTxtDate().setReadOnly(true);
		view.getBtnEnregistrer().setEnabled(false);

	}

	public DetailsReparationPresenter(Phone phone) {
		view = new DetailsReparationView();
		model = new DetailsReparationModel();

		service = new FicheService();
		bindComponents();

		model.setListener(this);
		model.setSelectedPhone(phone);
		binder.setBean(model.getSelectedFiche());
		model.setListener(this);
		view.getTxtDate().setValue(LocalDate.now().format(DateTimeFormatter.ISO_DATE));

		model.getSelectedFiche().setPhone(phone);
		binder.readBean(model.getSelectedFiche());
		setListeners();
		view.getBtnEnregistrer().setEnabled(isvalid && hasChange);
		view.getBtnEffacer().setEnabled(false);
	}

	public void bindComponents() {

		binder = new Binder<>();
		binder.forField(view.getTxtCout()).withValidator(cout -> cout.matches("^[0-9]+"), "Saisie incorrecte")
		    .bind(Fiche::getCout, Fiche::setCout);
		binder.forField(view.getTxtDetail()).bind(Fiche::getDetails, Fiche::setDetails);
		binder.forField(view.getTxtDate()).bind(Fiche::getDate, Fiche::setDate);
		binder.forField(view.getTxtTitre()).withValidator(titre -> titre.matches("[a-zA-Z ]+"), "Saisie incorrect ")
		    .asRequired("Champ Obligatoire").bind(Fiche::getTitre, Fiche::setTitre);
	}

	private void setListeners() {
		view.getBtnEnregistrer().addClickListener(e -> onBtnSaveClicked());
		this.view.getWinContent().addCloseListener(e -> onWindoewClosed());
		this.view.getBtnEffacer().addClickListener(e -> onBtnEffacerClicked());
		// binder.addStatusChangeListener(e -> onStatusBinderChanged());
		binder.addValueChangeListener(e -> onStatusBinderChanged());

	}

	private void onBtnEffacerClicked() {
		DialogConfirmation dialog = new DialogConfirmation();
		dialog.getBtnCancel().addClickListener(e -> {
			dialog.getWinContent().close();
			Notification.show("Annuler !", Type.HUMANIZED_MESSAGE);
			dialog.getWinContent().close();
		});
		dialog.getBtnOk().addClickListener(e -> {
			service.deleteFiche(model.getSelectedFiche().getId());
			Notification.show("Supprimer !", Type.HUMANIZED_MESSAGE);
			dialog.getWinContent().close();
			setChanged();
			notifyObservers("fiche supprimée");
		});

		UI.getCurrent().addWindow(dialog.getWinContent());

	}

	private void onStatusBinderChanged() {
		hasChange = true;
		isvalid = binder.isValid();
		view.getBtnEnregistrer().setEnabled(isvalid && hasChange);

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

			if (!view.getBtnEffacer().isEnabled()) {
				view.getBtnEffacer().setEnabled(true);
			}
			setChanged();
			notifyObservers("fiche ajoutée");

		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.getFieldValidationErrors();

		}

	}

	@Override
	public void onFicheSelected() {
		Fiche selectedFiche = model.getSelectedFiche();

		view.getTxtTitre().setValue(selectedFiche.getTitre());
		view.getTxtDate().setValue(selectedFiche.getDate());
		view.getTxtCout().setValue(selectedFiche.getCout());
		view.getTxtDetail().setValue(selectedFiche.getDetails());
		binder.readBean(model.getSelectedFiche());

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
