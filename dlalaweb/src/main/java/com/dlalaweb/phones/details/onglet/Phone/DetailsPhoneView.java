package com.dlalaweb.phones.details.onglet.Phone;

import com.dlalaweb.utils.StatutEnum;
import com.dlalaweb.utils.EtatEnum;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class DetailsPhoneView extends VerticalLayout {

	private TextField							txtMarquePhone, txtModelPhone, txtBatteriePhone, txtPrixAchatPhone, txtPrixVentePhone;
	// accessoires téléphones
	private TextField							txtAccesPhone, txtImei, txtNoModel, txtBenefice;
	// couts réparation vente;
	private TextField							txtCoutRepPhone;

	private ComboBox<String>			 comboCotePhone;
	private ComboBox<EtatEnum>			comboEtatPhone;
	private DateField							dateAchatPhone, dateVentePhone, dateMajPhone;
	private Button								btnSave, btnAjouterReparation, btnDeletPhone;

	private Window								winContent;
	private Label									lblMonnaieCoutRep;
	private Label									lblMonnaiePrixAchat;
	private Label									lblMonnaiePrixVente;
	private Label									lblMonnaieBenefice;
	private ComboBox<StatutEnum>	comboBoxStatutPhone;

	public DetailsPhoneView() {
		this.setSizeFull();

		this.setSpacing(false);
		winContent = new Window("<h3><b>Détails du téléphone</b><h3>", this);
		winContent.setCaptionAsHtml(true);
		winContent.setSizeFull();
		winContent.center();
		winContent.setModal(true);
		winContent.setDraggable(false);

		winContent.setWidth("80%");
		buildScreen();
	}

	private void buildScreen() {
		this.buidLigneHl1();
		this.buidLigneHl2();
		this.buidLigneHl3();
		this.buidLigneHl4();
		this.buildButtonSide();
	}

	private void buidLigneHl1() {
		// ligen 1 de l'écran
		HorizontalLayout hl1 = new HorizontalLayout();
		hl1.setMargin(false);
		hl1.setWidth("100%");

		this.txtMarquePhone = new TextField("Marque du téléphone");
		this.txtModelPhone = new TextField("Modèle du téléphone");
		this.comboEtatPhone = new ComboBox<>("État du téléphone");
		this.comboEtatPhone.setEmptySelectionAllowed(true);
		this.txtBatteriePhone = new TextField("État de la batterie");
		hl1.addComponents(txtMarquePhone, txtModelPhone, comboEtatPhone, txtBatteriePhone);
		this.addComponent(hl1);
	}

	private void buidLigneHl2() {
		HorizontalLayout hl2 = new HorizontalLayout();
		hl2.setMargin(false);
		hl2.setWidth("100%");

		this.txtAccesPhone = new TextField("Accessoires avec le téléphone");
		this.txtImei = new TextField("IMEI");
		this.txtNoModel = new TextField("No model");
		comboBoxStatutPhone = new ComboBox<>("Statut");
		hl2.addComponents(txtAccesPhone, txtImei, txtNoModel, comboBoxStatutPhone);
		this.addComponent(hl2);

	}

	private void buidLigneHl3() {
		HorizontalLayout hl3 = new HorizontalLayout();
		hl3.setMargin(false);
		hl3.setWidth("100%");
		this.comboCotePhone = new ComboBox<>("Côte téléphone");
		this.dateAchatPhone = new DateField("Date d'achat");
		this.dateVentePhone = new DateField("Date de vente");
		this.dateMajPhone = new DateField("Date modification");
		hl3.addComponents(comboCotePhone, dateAchatPhone, dateVentePhone, dateMajPhone);
		this.addComponent(hl3);

	}

	private void buidLigneHl4() {
		HorizontalLayout hl4 = new HorizontalLayout();
		hl4.setMargin(false);
		hl4.setWidth("100%");

		this.txtPrixAchatPhone = new TextField("Prix d'achat");
		lblMonnaiePrixAchat = new Label("$ Cad");
		lblMonnaiePrixAchat.setStyleName(ValoTheme.LABEL_BOLD);
		lblMonnaiePrixAchat.setStyleName(ValoTheme.LABEL_H4);
		HorizontalLayout hlPrixAchat = new HorizontalLayout(txtPrixAchatPhone, lblMonnaiePrixAchat);
		hlPrixAchat.setMargin(false);
		hlPrixAchat.setSpacing(true);
		hlPrixAchat.setComponentAlignment(lblMonnaiePrixAchat, Alignment.BOTTOM_CENTER);

		this.txtPrixVentePhone = new TextField("Prix de vente");
		lblMonnaiePrixVente = new Label("$ Cad");
		lblMonnaiePrixVente.setStyleName(ValoTheme.LABEL_BOLD);
		lblMonnaiePrixVente.setStyleName(ValoTheme.LABEL_H4);
		HorizontalLayout hlPrixVente = new HorizontalLayout(txtPrixVentePhone, lblMonnaiePrixVente);
		hlPrixVente.setMargin(false);
		hlPrixVente.setSpacing(true);
		hlPrixVente.setComponentAlignment(lblMonnaiePrixVente, Alignment.BOTTOM_CENTER);
		this.txtCoutRepPhone = new TextField("Coûts de réparation");
		lblMonnaieCoutRep = new Label("$ Cad");
		lblMonnaieCoutRep.setStyleName(ValoTheme.LABEL_BOLD);
		lblMonnaieCoutRep.setStyleName(ValoTheme.LABEL_H4);
		HorizontalLayout hlCout = new HorizontalLayout(txtCoutRepPhone, lblMonnaieCoutRep);
		hlCout.setMargin(false);
		hlCout.setSpacing(true);
		hlCout.setComponentAlignment(lblMonnaieCoutRep, Alignment.BOTTOM_CENTER);
		this.txtBenefice = new TextField("Bénéfice");
		lblMonnaieBenefice = new Label("$ Cad");
		lblMonnaieBenefice.setStyleName(ValoTheme.LABEL_BOLD);
		lblMonnaieBenefice.setStyleName(ValoTheme.LABEL_H4);
		HorizontalLayout hlBenefice = new HorizontalLayout(txtBenefice, lblMonnaieBenefice);
		hlBenefice.setMargin(false);
		hlBenefice.setSpacing(true);
		hlBenefice.setComponentAlignment(lblMonnaieBenefice, Alignment.BOTTOM_CENTER);
		hl4.addComponents(hlPrixVente, hlPrixAchat, hlCout, hlBenefice);
		this.addComponent(hl4);

	}
	// xtPrixAchatPhone, txtPrixVentePhone

	private void buildButtonSide() {
		btnSave = new Button("Enregistrer");
		btnAjouterReparation = new Button("Ajouter réparation");
		btnDeletPhone = new Button("Effacer téléphone");

		HorizontalLayout hButtons = new HorizontalLayout(btnSave, btnAjouterReparation, btnDeletPhone);
		this.addComponent(hButtons);
		this.setComponentAlignment(hButtons, Alignment.BOTTOM_CENTER);
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

	public ComboBox<EtatEnum> getComboEtatPhone() {
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

	public Button getBtnSave() {
		return btnSave;
	}

	public TextField getTxtImei() {
		return txtImei;
	}

	public TextField getTxtNoModel() {
		return txtNoModel;
	}

	public TextField getTxtBenefice() {
		return txtBenefice;
	}

	public Label getLblMonnaieCoutRep() {
		return lblMonnaieCoutRep;
	}

	public Label getLblMonnaiePrixAchat() {
		return lblMonnaiePrixAchat;
	}

	public Label getLblMonnaiePrixVente() {
		return lblMonnaiePrixVente;
	}

	public Label getLblMonnaieBenefice() {
		return lblMonnaieBenefice;
	}

	public ComboBox<StatutEnum> getComboBoxStatutPhone() {
		return comboBoxStatutPhone;
	}

	public Button getBtnAjouterReparation() {
		return btnAjouterReparation;
	}

	public Button getBtnDeletPhone() {
		return btnDeletPhone;
	}
	

}
