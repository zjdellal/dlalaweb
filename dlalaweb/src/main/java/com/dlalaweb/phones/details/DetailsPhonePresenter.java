package com.dlalaweb.phones.details;

import java.util.Observable;

import com.dlalacore.dlala.entities.Phone;
import com.dlalaweb.utils.DateFormatterUtil;

public class DetailsPhonePresenter extends Observable implements DetPhoneModelListener {

	private DetailsPhoneView	view;
	private DetailsPhoneModel	model;

	public DetailsPhonePresenter() {
		this.view = new DetailsPhoneView();
		this.model = new DetailsPhoneModel();
		this.model.setListener(this);
	}

	public DetailsPhonePresenter(Phone phone) {
		this.view = new DetailsPhoneView();
		this.model = new DetailsPhoneModel();
		this.model.setListener(this);
		this.model.setSelectedPhone(phone);

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
		view.getTxtPrixVentePhone().setValue(phone.getPrixVente()==null ? "": phone.getPrixVente());
		view.getTxtCoutRepPhone().setValue(phone.getCoutReparation()==null ? "":phone.getCoutReparation());
		view.getComboCotePhone().setValue(phone.getCotePhone()==null ? "":phone.getCotePhone());
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
