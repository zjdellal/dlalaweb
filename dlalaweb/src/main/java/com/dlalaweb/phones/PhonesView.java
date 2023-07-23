package com.dlalaweb.phones;

import com.dlalacore.dlala.entities.Phone;
import com.vaadin.server.PaintTarget;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class PhonesView extends VerticalLayout {
	private Label				lblCaption;
	private Grid<Phone>	gPhones;
	private Button			btnAjouter;

	public PhonesView() {
		this.setSizeFull();
		this.setCaption("<b>Liste téléphones:</b>");
		this.setCaptionAsHtml(true);
		this.setSpacing(false);
		this.buildScreen();

	}

	private void buildScreen() {
		lblCaption = new Label();
		gPhones = new Grid<Phone>();
		gPhones.setSizeFull();

		
		this.addComponent(lblCaption);
		this.addComponent(gPhones);
		this.setExpandRatio(gPhones, 1.5f);
		this.buildBtnSide();

	}

	private void buildBtnSide() {
		HorizontalLayout hButtons = new HorizontalLayout();
		this.btnAjouter = new Button("Ajouter Téléphone");
		hButtons.addComponent(btnAjouter);
		this.addComponent(hButtons);
		this.setComponentAlignment(hButtons, Alignment.BOTTOM_CENTER);
	}

	public Label getLblCaption() {
		return lblCaption;
	}

	public Grid<Phone> getgPhones() {
		return gPhones;
	}

	public Button getBtnAjouter() {
		return btnAjouter;
	}

}
