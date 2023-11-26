package com.dlalaweb.phones.details.onglet.Phone;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.List;
import java.util.Observable;
import java.util.Set;

import com.dlalacore.dlala.entities.Fiche;
import com.dlalacore.dlala.entities.Phone;
import com.dlalaweb.phones.details.DetPhoneModelListener;
import com.dlalaweb.phones.details.DetailsPhoneModel;
import com.dlalaweb.service.impl.FicheService;
import com.dlalaweb.service.impl.PhonesService;
import com.dlalaweb.utils.ConverterLocalDateToString;
import com.dlalaweb.utils.ConverterStatutEnumToString;
import com.dlalaweb.utils.ConverterEtatEnumToString;
import com.dlalaweb.utils.EtatEnum;
import com.dlalaweb.utils.StatutEnum;
import com.dlalaweb.utils.dialogconfirmation.DialogConfirmation;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;

public class DetailsPhonePresenter extends Observable implements DetPhoneModelListener {

	private DetailsPhoneView	view;
	private DetailsPhoneModel	model;
	private PhonesService			service;
	private FicheService			ficheService;

	private Binder<Phone>			binder;
	private List<Fiche>				historeparations;
	private boolean						isNew	= false;

	public DetailsPhonePresenter() {
		isNew = true;
		this.view = new DetailsPhoneView();
		this.model = new DetailsPhoneModel();
		this.model.setListener(this);
		service = new PhonesService();

		setComponents();
		binder = new Binder<>(Phone.class);
		binder();
		binder.setBean(model.getSelectedPhone());
		view.getBtnAjouterReparation().setEnabled(false);
		view.getBtnDeletPhone().setEnabled(false);
		setListenersComponents();
		binder.readBean(model.getSelectedPhone());

	}

	private void setComponents() {
		Set<EtatEnum> setEtat = EnumSet.allOf(EtatEnum.class);
		view.getComboEtatPhone().setItems(setEtat);
		Set<StatutEnum> set = EnumSet.allOf(StatutEnum.class);
		view.getComboBoxStatutPhone().setItems(set);
	}

	public DetailsPhonePresenter(Phone phone) {
		this.view = new DetailsPhoneView();
		this.model = new DetailsPhoneModel();
		this.model.setListener(this);
		this.model.setSelectedPhone(phone);
		service = new PhonesService();

		setComponents();

		binder = new Binder<>(Phone.class);
		binder.setBean(phone);
		binder.readBean(model.getSelectedPhone());
		binder();
		model.setFiche(historeparations);
		setListenersComponents();

	}

	private void setListenersComponents() {
		view.getBtnSave().addClickListener(e -> onBtnSaveClicked());
		view.getWinContent().addCloseListener(e -> onWindowsClosed());
		view.getBtnAjouterReparation().addClickListener(e -> onbtnAdReparationClicked(e));
		view.getBtnDeletPhone().addClickListener(e -> onBtnDeleteClicked());
		// value change listener pour marque
		view.getTxtMarquePhone().addValueChangeListener(e -> onMarqueValueChange());
	}

	private void onMarqueValueChange() {

		if (view.getTxtMarquePhone().getValue().equalsIgnoreCase("apple")) {
			binder.forField(view.getTxtBatteriePhone()).asRequired("% Batterie obligatoire pour iphone")
			    .bind(Phone::getEtatBatterie, Phone::setEtatBatterie);

		} else {
			binder.forField(view.getTxtBatteriePhone()).bind(Phone::getEtatBatterie, Phone::setEtatBatterie);
			view.getTxtBatteriePhone().setRequiredIndicatorVisible(false);
		}
	}

	private void onBtnDeleteClicked() {
		DialogConfirmation dialog = new DialogConfirmation();
		dialog.getBtnCancel().addClickListener(e -> {
			dialog.getWinContent().close();
			Notification.show("Annuler !", Type.HUMANIZED_MESSAGE);
			dialog.getWinContent().close();
		});
		dialog.getBtnOk().addClickListener(e -> {
			if (model.getSelectedPhone().getId() == 0) {
				List<Phone> liste = service.findAll();
				for (Phone p : liste) {
					if (p.getImeiPhone().equals(view.getTxtImei().getValue())) {
						model.getSelectedPhone().setId(p.getId());
					}
				}
			}
			/**
			 * continuer ici il faudrait supprimer toutes les fiches connecter au phone a
			 * supprimer
			 */
			// ficheService = new FicheService();

			boolean isOK = service.deletePhone(model.getSelectedPhone().getId());
			if (isOK) {
				Notification.show("Supprimer !", Type.HUMANIZED_MESSAGE);
				dialog.getWinContent().close();
				setChanged();
				notifyObservers("téléphone supprimé");
			}
		});

		UI.getCurrent().addWindow(dialog.getWinContent());

	}

	private void onbtnAdReparationClicked(ClickEvent e) {
		setChanged();
		notifyObservers(e.getButton().getCaption());
	}

	private void onWindowsClosed() {
		this.model.setSelectedPhone(null);
		this.setChanged();
		this.notifyObservers("close window");
	}

	private void onBtnSaveClicked() {
		Phone phone = new Phone();
		if (isNew) {

			phone.setId(0);

			if (model.getSelectedPhone() != null) {
				phone.setId(model.getSelectedPhone().getId());
			} else
				model.setSelectedPhone(phone);
		}

		try {
			binder.writeBean(model.getSelectedPhone());
			LocalDate now = LocalDate.now();
			model.getSelectedPhone().setDateMaj(String.valueOf(now));

			Phone result = service.save(model.getSelectedPhone());
			if (result != null) {
				view.getBtnAjouterReparation().setEnabled(true);
				view.getBtnDeletPhone().setEnabled(true);
				new DetailsPhonePresenter(result);
			}

		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	@Override
	public void onPhoneSelected() {
		Phone phone = model.getSelectedPhone();
		setPrixFields(phone);
		if (phone.getDateMaj() == null)
			view.getDateMajPhone().setValue(LocalDate.now());
		// binder.readBean(phone);
	}

	private void setPrixFields(Phone phone) {

		FicheService fiche = new FicheService();
		// lr = liste de réparations

		historeparations = fiche.getFichesByIdPhone(phone.getId());
		if (!historeparations.isEmpty())
			view.getTxtCoutRepPhone().setValue(getTotalCoutReparation(historeparations));
		else
			view.getTxtCoutRepPhone().setValue("0");
		if (phone.getPrixAchat() == null) {
			view.getTxtPrixAchatPhone().setValue("0");
		}

		if (phone.getPrixVente() != null && phone.getPrixAchat() != null) {

			Double ben = (double) 0;
			double subVente = Double.parseDouble(phone.getPrixVente());
			double subAchat = Double.parseDouble(phone.getPrixAchat());
			;
			if (Double.parseDouble(view.getTxtCoutRepPhone().getValue()) > 0) {
				subAchat += Double.parseDouble(view.getTxtCoutRepPhone().getValue());

			}

			ben = subVente - subAchat;

			view.getTxtBenefice().setValue(String.valueOf(ben));
		}
	}

	private String getTotalCoutReparation(List<Fiche> fiches) {
		double total = (double) 0;
		for (Fiche f : fiches) {
			if (f.getCout() != null) {
				String subCout = f.getCout();
				if (Double.parseDouble(subCout) > 0)
					total += Double.parseDouble(subCout);
			}

		}
		return String.valueOf(total);

	}

	private void binder() {

		// marque
		binder.forField(view.getTxtMarquePhone()).asRequired().bind(Phone::getMarque, Phone::setMarque);
		// model
		binder.forField(view.getTxtModelPhone()).withValidator(model -> model.length() >= 2, "Champs obligatoire")
		    .bind(Phone::getModel, Phone::setModel);
		// imei
		binder.forField(view.getTxtImei()).asRequired("Imei obligatoire").bind(Phone::getImeiPhone, Phone::setImeiPhone);

		// no model
		binder.forField(view.getTxtNoModel()).withValidator(noModel -> noModel.length() > 0, "Champs obligatoire")
		    .bind(Phone::getNoModelPhone, Phone::setNoModelPhone);

		binder.forField(view.getTxtNoModel()).asRequired("obligatoire !!!!").bind(Phone::getNoModelPhone,
		    Phone::setNoModelPhone);

		// état teléphone
		binder.forField(view.getComboEtatPhone()).withConverter(new ConverterEtatEnumToString()).bind(Phone::getEtat,
		    Phone::setEtat);

		// accessoires
		binder.forField(view.getTxtAccesPhone()).bind(Phone::getAccessoires, Phone::setAccessoires);

		// cote téléphone
		binder.forField(view.getComboCotePhone()).bind(Phone::getCotePhone, Phone::setCotePhone);

		//etet batterie
		binder.forField(view.getTxtBatteriePhone()).bind(Phone::getEtatBatterie, Phone::setEtatBatterie);

		// date d'achat
		binder.forField(view.getDateAchatPhone()).asRequired("Date obligatoire")
		    .withConverter(new ConverterLocalDateToString()).bind(Phone::getDateAchat, Phone::setDateAchat);
		// date de vente
		binder.forField(view.getDateVentePhone()).withConverter(new ConverterLocalDateToString()).bind(Phone::getDateVente,
		    Phone::setDateVente);

		// date mise a jour
		binder.forField(view.getDateMajPhone()).withConverter(new ConverterLocalDateToString()).bind(Phone::getDateMaj,
		    Phone::setDateMaj);

		// prix d'achat
		binder.forField(view.getTxtPrixAchatPhone()).withValidator(prix -> prix.matches("\\d+"), "Champs numéric seulement")
		    .bind(Phone::getPrixAchat, Phone::setPrixAchat);

		// prix de vente
		binder.forField(view.getTxtPrixVentePhone())
		    .withValidator(vente -> vente.matches("\\d+"), "Champs numéric seulement")
		    .bind(Phone::getPrixVente, Phone::setPrixVente);
		// comboStatut
		binder.forField(view.getComboBoxStatutPhone()).withConverter(new ConverterStatutEnumToString())
		    .bind(Phone::getStatutPhone, Phone::setStatutPhone);

	}

	public DetailsPhoneView getView() {
		return view;
	}

	@Override
	public void ifFicheExiste() {
		setChanged();
		notifyObservers("Historique éxistante");

	}

	public List<Fiche> getHistoreparations() {
		return model.getSelectedPhone().getFiches();
	}

	public DetailsPhoneModel getModel() {
		return model;
	}

}
