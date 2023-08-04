package com.dlalaweb.phones.details.onglet.Phone;

import java.util.List;
import java.util.Observable;

import com.dlalacore.dlala.entities.Fiche;
import com.dlalacore.dlala.entities.Phone;
import com.dlalaweb.phones.details.DetPhoneModelListener;
import com.dlalaweb.phones.details.DetailsPhoneModel;
import com.dlalaweb.service.impl.FicheService;
import com.dlalaweb.service.impl.PhonesService;
import com.dlalaweb.utils.ConverterLocalDateToString;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;

public class DetailsPhonePresenter extends Observable implements DetPhoneModelListener {

	private DetailsPhoneView	view;
	private DetailsPhoneModel	model;
	private PhonesService			service;
	private Binder<Phone>			binder;

	public DetailsPhonePresenter() {
		this.view = new DetailsPhoneView();
		this.model = new DetailsPhoneModel();
		this.model.setListener(this);
		setListenersComponents();
		binder = new Binder<>(Phone.class);
		binder();
		binder.setBean(model.getSelectedPhone());

	}

	public DetailsPhonePresenter(Phone phone) {
		this.view = new DetailsPhoneView();
		this.model = new DetailsPhoneModel();
		this.model.setListener(this);
		this.model.setSelectedPhone(phone);
		setListenersComponents();
		binder = new Binder<>(Phone.class);
		binder.setBean(phone);
		binder.readBean(model.getSelectedPhone());
		binder();

	}

	private void setListenersComponents() {
		view.getBtnSave().addClickListener(e -> onBtnSaveClicked());
		view.getWinContent().addCloseListener(e -> onWindowsClosed());
	}

	private void onWindowsClosed() {
		this.model.setSelectedPhone(null);
		this.setChanged();
		this.notifyObservers("close window");
	}

	private void onBtnSaveClicked() {
		Phone phone = new Phone();
		if (model.getSelectedPhone() != null) {
			phone.setId(model.getSelectedPhone().getId());
		}

		// phone.setMarque(view.getTxtMarquePhone().getValue());
		// phone.setModel(view.getTxtModelPhone().getValue());
		// phone.setEtatBatterie("50%");
		// phone.setAccessoires("Boite");
		// phone.setCotePhone("Affaire");
		// phone.setCoutReparation("10$");
		// phone.setPrixAchat("100$");
		// phone.setPrixVente("0");
		// phone.setDateAchat(LocalDate.now().toString());
		// phone.setDateMaj(LocalDate.now().toString());
		// phone.setDateVente(LocalDate.now().toString());
		// phone.setEtat("neuf");
		// phone.setStatutPhone("disponible");

		try {
			binder.writeBean(model.getSelectedPhone());
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service = new PhonesService();
		service.save(model.getSelectedPhone());

	}

	@Override
	public void onPhoneSelected() {
		Phone phone = model.getSelectedPhone();

		if (phone.getPrixVente() != null && phone.getPrixAchat() != null) {
			String subVente = phone.getPrixVente().substring(0, phone.getPrixVente().length() - 5);
			String subachat = phone.getPrixAchat().substring(0, phone.getPrixAchat().length() - 5);
			Double ben = Double.parseDouble(subVente) - Double.parseDouble(subachat);
			view.getTxtBenefice().setValue(String.valueOf(ben));
		}

		FicheService fiche = new FicheService();
		List<Fiche> ff = fiche.getFichesByIdPhone(phone.getId());
		if (!ff.isEmpty())
			view.getTxtCoutRepPhone().setValue(getTotalCoutReparation(ff));
		// view.getTxtMarquePhone().setValue(phone.getMarque() == null ? "" :
		// phone.getMarque());
		// view.getTxtModelPhone().setValue(phone.getModel() == null ? "" :
		// phone.getModel());
		// view.getComboEtatPhone().setValue(phone.getEtat() == null ? "" :
		// phone.getEtat());
		// view.getTxtBatteriePhone().setValue(phone.getEtatBatterie() == null ? "" :
		// phone.getEtatBatterie());
		// view.getTxtAccesPhone().setValue(phone.getAccessoires() == null ? "" :
		// phone.getAccessoires());
		// view.getTxtPrixAchatPhone().setValue(phone.getPrixAchat() == null ? "" :
		// phone.getPrixAchat());
		// view.getTxtPrixVentePhone().setValue(phone.getPrixVente() == null ? "" :
		// phone.getPrixVente());
		// view.getTxtCoutRepPhone().setValue(phone.getCoutReparation() == null ? "" :
		// phone.getCoutReparation());
		// view.getComboCotePhone().setValue(phone.getCotePhone() == null ? "" :
		// phone.getCotePhone());
		// if (phone.getDateAchat() != null)
		// view.getDateAchatPhone().setValue(DateFormatterUtil.ConvertStringToLocalDate(phone.getDateAchat()));
		// if (phone.getDateVente() != null)
		// view.getDateVentePhone().setValue(DateFormatterUtil.ConvertStringToLocalDate(phone.getDateVente()));
		// if (phone.getDateMaj() != null)
		// view.getDateMajPhone().setValue(DateFormatterUtil.ConvertStringToLocalDate(phone.getDateMaj()));

	}

	private String getTotalCoutReparation(List<Fiche> fiches) {
		double total = (double) 0;
		for (Fiche f : fiches) {
			if (f.getCout() != null) {
				String subCout = f.getCout().substring(0, f.getCout().length() - 2);
				if(Double.parseDouble(subCout) > 0 )
				total += Double.parseDouble(subCout);
			}

		}
		return String.valueOf(total);

	}

	private void binder() {

		// marque
		binder.forField(view.getTxtMarquePhone())
		    .withValidator(marque -> marque.length() >= 2 && marque.matches("^[a-zA-Z]*$"), "Saisie incorrecte")
		    .bind(Phone::getMarque, Phone::setMarque);
		// model
		binder.forField(view.getTxtModelPhone()).withValidator(model -> model.length() >= 2, "Champs obligatoire")
		    .bind(Phone::getModel, Phone::setModel);
		// imei
		binder.forField(view.getTxtImei())
		    .withValidator(imei -> imei.length() > 0 && imei.matches("\\d+"), "Champs numéric obligatoire")
		    .bind(Phone::getImeiPhone, Phone::setImeiPhone);

		// no model
		binder.forField(view.getTxtNoModel()).withValidator(noModel -> noModel.length() > 0, "Champs obligatoire")
		    .bind(Phone::getNoModelPhone, Phone::setNoModelPhone);

		binder.forField(view.getTxtNoModel()).asRequired("obligatoire !!!!").bind(Phone::getNoModelPhone,
		    Phone::setNoModelPhone);

		// état teléphone
		binder.forField(view.getComboEtatPhone()).bind(Phone::getEtat, Phone::setEtat);

		// accessoires
		binder.forField(view.getTxtAccesPhone()).bind(Phone::getAccessoires, Phone::setAccessoires);

		// cote téléphone
		binder.forField(view.getComboCotePhone()).bind(Phone::getCotePhone, Phone::setCotePhone);

		// état batterie
		if (view.getTxtMarquePhone().getValue().equalsIgnoreCase("apple"))
			binder.forField(view.getTxtBatteriePhone()).asRequired("champs obligatoire pour l'iphone")
			    .bind(Phone::getEtatBatterie, Phone::setEtatBatterie);
		else
			binder.forField(view.getTxtBatteriePhone()).bind(Phone::getEtatBatterie, Phone::setEtatBatterie);

		// date d'achat
		binder.forField(view.getDateAchatPhone()).withConverter(new ConverterLocalDateToString()).bind(Phone::getDateAchat,
		    Phone::setDateAchat);
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

	}

	public DetailsPhoneView getView() {
		return view;
	}

}
