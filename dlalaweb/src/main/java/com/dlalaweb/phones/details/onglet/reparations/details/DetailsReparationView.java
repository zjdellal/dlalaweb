package com.dlalaweb.phones.details.onglet.reparations.details;

import com.dlalacore.dlala.entities.Phone;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class DetailsReparationView {

	private VerticalLayout	vContent;
	private TextField				txtTitre, txtCout, txtDate;
	private TextArea				txtDetail;
	private Window					winContent;

	private Label						lblNomTel, lblModel, lblNoModel, lblMarque;
	private FormLayout			fLayout;
	private Label						lblMonnaieCout;
	private Button btnEnregistrer, btnEffacer;

	public DetailsReparationView() {
		vContent = new VerticalLayout();
		vContent.setWidth("100%");
		vContent.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

		winContent = new Window("Détail de la fiche", vContent);
		winContent.setCaptionAsHtml(true);
		winContent.setSizeUndefined();
		winContent.center();
		winContent.setModal(false);
		winContent.setResizable(false);
		winContent.setDraggable(false);
		buildScreen();
		// txtDetail = new TextArea("Details:");
		// txtDetail.setWidth("100%");
		// vContent.addComponent(txtDetail);

	}


	private void buildScreen() {
		buildFlayoutTel();
		this.txtTitre = new TextField("Titre");
		txtTitre.setSizeFull();
		this.vContent.addComponent(txtTitre);
		this.txtDate = new TextField("Date de réparation");
		this.txtCout = new TextField("Cout");
		txtCout.setWidth("95%");
		lblMonnaieCout = new Label("$ Cad");
		lblMonnaieCout.addStyleName(ValoTheme.LABEL_BOLD);
		HorizontalLayout hCout = new HorizontalLayout(txtCout, lblMonnaieCout);
		hCout.setSpacing(false);
		hCout.setMargin(false);
		// hCout.setExpandRatio(txtCout, 0.75f);
		// hCout.setExpandRatio(lblMonnaieCout, 0.25f);
		hCout.setSizeFull();
		hCout.setComponentAlignment(lblMonnaieCout, Alignment.BOTTOM_CENTER);
		HorizontalLayout h = new HorizontalLayout(txtDate, hCout);
		h.setWidth("100%");
		this.txtDetail = new TextArea("Détails:");

		txtDetail.setWidth("100%");

		vContent.addComponents(h, txtDetail);
		vContent.setComponentAlignment(txtDetail, Alignment.BOTTOM_LEFT);
		this.btnEnregistrer =  new Button("Enregistrer");
		this.btnEffacer =  new Button("Supprimer");
		HorizontalLayout hButtons =  new HorizontalLayout(btnEnregistrer, btnEffacer);
		this.vContent.addComponent(hButtons);
		this.vContent.setComponentAlignment(hButtons, Alignment.MIDDLE_CENTER);

	}

	private void buildFlayoutTel() {
		// Phone p = new Phone();
		// p.
		lblMarque = new Label();
		lblMarque.setCaption("Marque");

		lblNomTel = new Label();
		lblNomTel.setCaption("Nom du téléphone : ");

		lblModel = new Label();
		lblModel.setCaption("Modèle du téléphone : ");

		lblNoModel = new Label();
		lblNoModel.setCaption("No de modèle : ");

		fLayout = new FormLayout(lblMarque, lblModel, lblNoModel);
		fLayout.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
		vContent.addComponent(fLayout);
	}

	public TextField getTxtTitre() {
		return txtTitre;
	}

	public TextField getTxtCout() {
		return txtCout;
	}

	public TextField getTxtDate() {
		return txtDate;
	}

	public TextArea getTxtDetail() {
		return txtDetail;
	}

	public Window getWinContent() {
		return winContent;
	}

	public Label getLblNomTel() {
		return lblNomTel;
	}

	public Label getLblModel() {
		return lblModel;
	}

	public Label getLblNoModel() {
		return lblNoModel;
	}

	public FormLayout getfLayout() {
		return fLayout;
	}

	public Label getLblMarque() {
		return lblMarque;
	}
	
	public Button getBtnEnregistrer() {
		return btnEnregistrer;
	}


	public Button getBtnEffacer() {
		return btnEffacer;
	}

	

}
