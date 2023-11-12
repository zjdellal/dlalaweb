package com.dlalaweb.utils.dialogconfirmation;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class DialogConfirmation {
	private Label						lblMessage;
	private Button					btnOk, btnCancel;
	private Window					winContent;
	private VerticalLayout	vContent;

	public DialogConfirmation() {
		vContent = new VerticalLayout();
		vContent.setResponsive(true);
		vContent.setSizeUndefined();
		lblMessage = new Label("Êtes-vous sûr de vouloir continuer ?");
		lblMessage.addStyleName(ValoTheme.LABEL_BOLD);
		lblMessage.addStyleName(ValoTheme.LABEL_H3);
		HorizontalLayout hButtons = new HorizontalLayout();
		btnCancel = new Button("");
		btnCancel.setIcon(VaadinIcons.EXIT);
		
		btnOk = new Button("OK");

		hButtons.addComponents(btnCancel, btnOk);

		vContent.addComponent(lblMessage);
		vContent.addComponent(hButtons);
		vContent.setComponentAlignment(hButtons, Alignment.BOTTOM_CENTER);
		vContent.addStyleName(ValoTheme.LAYOUT_CARD);
		winContent = new Window("<b>Alert !<b>", vContent);
		winContent.setModal(true);
		winContent.setResizable(false);
		winContent.center();
		winContent.setDraggable(false);
		winContent.setResponsive(true);
		winContent.setCaptionAsHtml(true);

	}

	public Label getLblMessage() {
		return lblMessage;
	}

	public Button getBtnOk() {
		return btnOk;
	}

	public Button getBtnCancel() {
		return btnCancel;
	}

	public Window getWinContent() {
		return winContent;
	}

}
