package com.dlalaweb.phones.details.onglet.historiquereparations;

import com.dlalacore.dlala.entities.Fiche;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class HistoreparationsView {
	private VerticalLayout	vContent;
	private Label						lblCaption;
	private Grid<Fiche>			gFiche;
	private Button					btnAjouter;

	public HistoreparationsView() {
		vContent = new VerticalLayout();
		vContent.setSizeFull();
		vContent.setCaption("<b>Historique de réparations:</b>");
		vContent.setCaptionAsHtml(true);
		vContent.setSpacing(false);
		this.buildScreen();
	}

	private void buildScreen() {
		lblCaption = new Label();
		gFiche = new Grid<Fiche>();
		gFiche.setSizeFull();

		vContent.addComponent(lblCaption);
		vContent.addComponent(gFiche);
		vContent.setExpandRatio(gFiche, 1.5f);
		this.buildBtnSide();

	}

	private void buildBtnSide() {
		HorizontalLayout hButtons = new HorizontalLayout();
		this.btnAjouter = new Button("Ajouter Téléphone");
		hButtons.addComponent(btnAjouter);
		vContent.addComponent(hButtons);
		vContent.setComponentAlignment(hButtons, Alignment.BOTTOM_CENTER);
	}

	public Label getLblCaption() {
		return lblCaption;
	}

	public Grid<Fiche> getgFiche() {
		return gFiche;
	}

	public Button getBtnAjouter() {
		return btnAjouter;
	}

	public VerticalLayout getContent() {

		return vContent;
	}

}
