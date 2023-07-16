package com.dlalaweb.phones.details;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class DetailsPhoneView extends VerticalLayout {
	private TextField					txtMarquePhone, txtModelPhone, txtBatteriePhone, txtPrixAchatPhone, txtPrixVentePhone;
	// accessoires téléphones
	private TextField					txtAccesPhone;
	// couts réparation vente;
	private TextField					txtCoutRepPhone;

	private ComboBox<String>	comboEtatPhone, comboCotePhone;
	private DateField					dateAchatPhone, dateVentePhone, dateMajPhone;

	private Window						winContent;

	public DetailsPhoneView() {
		this.setSizeFull();

		this.setSpacing(false);
		winContent = new Window("<h2><b>Détails du téléphone</b><h2>", this);
		winContent.setCaptionAsHtml(true);
		winContent.setSizeFull();
		winContent.center();
		winContent.setModal(true);
		winContent.setDraggable(false);
		winContent.setClosable(false);
		winContent.setWidth("80%");
		buildScreen();
	}

	private void buildScreen() {
		this.buidLigneHl1();
		this.buidLigneHl2();
		this.buidLigneHl3();
	}

	private void buidLigneHl1() {
		// ligen 1 de l'écran
		HorizontalLayout hl1 = new HorizontalLayout();
		hl1.setMargin(false);
		hl1.setWidth("100%");

		this.txtMarquePhone = new TextField("Marque du téléphone");
		this.txtModelPhone = new TextField("Modèle du téléphone");
		this.comboEtatPhone = new ComboBox<>("État du téléphone");
		this.txtBatteriePhone = new TextField("État de la batterie");
		hl1.addComponents(txtMarquePhone, txtModelPhone, comboEtatPhone, txtBatteriePhone);
		this.addComponent(hl1);
	}

	private void buidLigneHl2() {
		HorizontalLayout hl2 = new HorizontalLayout();
		hl2.setMargin(false);
		hl2.setWidth("100%");

		this.txtAccesPhone = new TextField("Accessoires avec le téléphone");
		this.txtPrixAchatPhone = new TextField("Prix d'achat");
		this.txtPrixVentePhone = new TextField("Prix de vente");
		this.txtCoutRepPhone = new TextField("Coût des réparations");
		hl2.addComponents(txtAccesPhone, txtPrixAchatPhone, txtPrixVentePhone, txtCoutRepPhone);
		this.addComponent(hl2);

	}

	private void buidLigneHl3() {
		HorizontalLayout hl3 = new HorizontalLayout();
		hl3.setMargin(false);
		hl3.setWidth("100%");
		this.comboCotePhone = new ComboBox<>("Côte téléphone");
		this.dateAchatPhone = new DateField("Date d'achat");
		this.dateVentePhone = new DateField("Date de vente");
		this.dateMajPhone = new DateField("Date modificattion");
		hl3.addComponents(comboCotePhone, dateAchatPhone, dateVentePhone, dateMajPhone);
		this.addComponent(hl3);

	}

	public TextField getTxtMarquePhone() {
		return txtMarquePhone;
	}

	public TextField getTxtModelPhone() {
		return txtModelPhone;
	}

	public TextField getTxtBatteriePhone() {
		return txtBatteriePhone;
	}

	public TextField getTxtPrixAchatPhone() {
		return txtPrixAchatPhone;
	}

	public TextField getTxtPrixVentePhone() {
		return txtPrixVentePhone;
	}

	public TextField getTxtAccesPhone() {
		return txtAccesPhone;
	}

	public TextField getTxtCoutRepPhone() {
		return txtCoutRepPhone;
	}

	public ComboBox<String> getComboEtatPhone() {
		return comboEtatPhone;
	}

	public ComboBox<String> getComboCotePhone() {
		return comboCotePhone;
	}

	public DateField getDateAchatPhone() {
		return dateAchatPhone;
	}

	public DateField getDateVentePhone() {
		return dateVentePhone;
	}

	public DateField getDateMajPhone() {
		return dateMajPhone;
	}

	public Window getWinContent() {
		return winContent;
	}

}
