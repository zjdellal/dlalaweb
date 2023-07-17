package com.dlalaweb.phones.details;

import java.time.LocalDate;
import java.util.Observable;

import com.dlalacore.dlala.entities.Phone;
import com.dlalaweb.service.impl.PhonesService;
import com.dlalaweb.utils.DateFormatterUtil;

public class DetailsPhonePresenter extends Observable implements DetPhoneModelListener {

	private DetailsPhoneView	view;
	private DetailsPhoneModel	model;
	private PhonesService			service;

	public DetailsPhonePresenter() {
		this.view = new DetailsPhoneView();
		this.model = new DetailsPhoneModel();
		this.model.setListener(this);
		setListenersComponents();
	}

	public DetailsPhonePresenter(Phone phone) {
		this.view = new DetailsPhoneView();
		this.model = new DetailsPhoneModel();
		this.model.setListener(this);
		this.model.setSelectedPhone(phone);
		setListenersComponents();

	}

	private void setListenersComponents() {
		view.getBtnSave().addClickListener(e -> onBtnSaveClicked());

	}

	private void onBtnSaveClicked() {
		
		Phone phone = new Phone();
		phone.setMarque(view.getTxtMarquePhone().getValue());
		phone.setModel(view.getTxtModelPhone().getValue());
		phone.setEtatBatterie("50%");
		phone.setAccessoires("Boite");
		phone.setCotePhone("Affaire");
		phone.setCoutReparation("10$");
		phone.setPrixAchat("100$");
		phone.setPrixVente("0");
		phone.setDateAchat(LocalDate.now().toString());
		phone.setDateMaj(LocalDate.now().toString());
		phone.setDateVente(LocalDate.now().toString());
		phone.setEtat("neuf");
		phone.setStatutPhone("disponible");
		service = new PhonesService();
		service.save(phone);

	}

	@Override
	public void onPhoneSelected() {
		Phone phone = model.getSelectedPhone();
		view.getTxtMarquePhone().setValue(phone.getMarque());
		view.getTxtModelPhone().setValue(phone.getModel());
		view.getComboEtatPhone().setValue(phone.getEtat());
		view.getTxtBatteriePhone().setValue(phone.getEtatBatterie());
		view.getTxtAccesPhone().setValue(phone.getAccessoires());
		view.getTxtPrixAchatPhone().setValue(phone.getPrixAchat());
		view.getTxtPrixVentePhone().setValue(phone.getPrixVente() == null ? "" : phone.getPrixVente());
		view.getTxtCoutRepPhone().setValue(phone.getCoutReparation() == null ? "" : phone.getCoutReparation());
		view.getComboCotePhone().setValue(phone.getCotePhone() == null ? "" : phone.getCotePhone());
		if (phone.getDateAchat() != null)
			view.getDateAchatPhone().setValue(DateFormatterUtil.ConvertStringToLocalDate(phone.getDateAchat()));
		if (phone.getDateVente() != null)
			view.getDateVentePhone().setValue(DateFormatterUtil.ConvertStringToLocalDate(phone.getDateVente()));
		if (phone.getDateMaj() != null)
			view.getDateMajPhone().setValue(DateFormatterUtil.ConvertStringToLocalDate(phone.getDateMaj()));

	}

	public DetailsPhoneView getView() {
		return view;
	}

}
