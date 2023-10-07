package com.dlalaweb.phones.details.onglet.reparations.details;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class DetailsReparationView {

	private VerticalLayout	vContent;
	private TextField				txtTitre, txtCout, txtDate;
	private TextArea				txtDetail;
	private Window					winContent;

	public DetailsReparationView() {
		vContent = new VerticalLayout();
		vContent.setSizeFull();
		vContent.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

		winContent = new Window("Détail de la fiche", vContent);
		winContent.setSizeFull();
		winContent.center();
		winContent.setModal(false);
		winContent.setResizable(false);
		winContent.setDraggable(false);
		buildScreen();

	}

	private void buildScreen() {
		this.txtTitre = new TextField("Titre");
		this.txtDate = new TextField("Date de réparation");
		this.txtCout = new TextField("Cout");
		HorizontalLayout h = new HorizontalLayout(txtTitre, txtDate, txtCout);
		h.setWidth("100%");
		this.txtDetail = new TextArea("Détail:");

		vContent.addComponents(h, txtDetail);

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

}
