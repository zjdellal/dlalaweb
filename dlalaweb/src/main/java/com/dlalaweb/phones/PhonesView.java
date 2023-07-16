package com.dlalaweb.phones;

import com.dlalacore.dlala.entities.Phone;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class PhonesView extends VerticalLayout {
	private Label		lblCaption;
	private Grid<Phone>	gPhones;

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

	}

	public Label getLblCaption() {
		return lblCaption;
	}

	public Grid<Phone> getgPhones() {
		return gPhones;
	}

}
