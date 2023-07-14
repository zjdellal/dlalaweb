package com.dlalaweb.phones;

import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class PhonesView<T> extends VerticalLayout {
	private Label		lblCaption;
	private Grid<T>	gPhones;

	public PhonesView() {
		this.setSizeFull();
		this.setCaption("<b>Liste téléphones:</b>");
		this.setCaptionAsHtml(true);
		this.buildScreen();

	}

	private void buildScreen() {
		lblCaption = new Label();
		gPhones = new Grid<>();
		gPhones.setSizeFull();

	}

	public Label getLblCaption() {
		return lblCaption;
	}

	public Grid<T> getgPhones() {
		return gPhones;
	}

}
