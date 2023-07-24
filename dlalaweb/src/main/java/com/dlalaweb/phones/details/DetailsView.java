package com.dlalaweb.phones.details;

import com.vaadin.ui.MenuBar;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class DetailsView {

	private VerticalLayout	vContent;
	private MenuBar					menuBarre;;
	private TabSheet				tabSheetContent;
	private Window					winContent;

	public DetailsView() {
		this.vContent = new VerticalLayout();
		this.vContent.setSizeFull();
		buildScreen();
		buildWindow();
	}

	private void buildScreen() {
		buildTabSheet();
	}

	private void buildTabSheet() {
		menuBarre = new MenuBar();
		menuBarre.addItem("Details téléphone");
		menuBarre.addItem("Historique réparation");
		menuBarre.setHeightUndefined();
		this.tabSheetContent = new TabSheet();
		this.tabSheetContent.setSizeFull();
		this.vContent.addComponent(menuBarre);
		this.vContent.addComponent(tabSheetContent);
		this.vContent.setExpandRatio(tabSheetContent, 1.85f);
	}

	private void buildWindow() {
		this.winContent = new Window();
		this.winContent.center();
		this.winContent.setDraggable(false);
		this.winContent.setModal(false);
		this.winContent.setContent(vContent);
		this.winContent.setSizeFull();
	}

	public VerticalLayout getvContent() {
		return vContent;
	}

	public MenuBar getMenuBarre() {
		return menuBarre;
	}

	public TabSheet getTabSheetContent() {
		return tabSheetContent;
	}

	public Window getWinContent() {
		return winContent;
	}

	
}
