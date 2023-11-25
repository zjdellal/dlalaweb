
/**
 * Classe login view: interface qui permet la connexion vers l'application 
 * @author dev-Zak
 * @créer le : 09/07/2023
 */
package com.dlalaweb.login;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.PasswordField;


import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class LoginView extends VerticalLayout {
	private TextField	txtUserName;
	private PasswordField txtPassword;
	private Window		winContent;
	private Button		btnConnexion, btnFemer;

	public LoginView() {
		this.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		this.setSizeUndefined();
		this.setMargin(true);

		this.addStyleName(ValoTheme.LAYOUT_WELL);
		this.buildScreen();

	}

	private void buildScreen() {
		this.txtUserName = new TextField("nom d'utilisateur");
		this.txtUserName.setSizeFull();
		this.txtPassword = new PasswordField("Mot de passe");
		this.txtPassword.setSizeFull();
		this.addComponents(this.txtUserName, this.txtPassword);

		this.btnConnexion = new Button("Connexion");
		this.btnFemer = new Button("Fermer");
		HorizontalLayout hButtons = new HorizontalLayout(this.btnConnexion, this.btnFemer);
		this.addComponent(hButtons);

		// ajouter le tout dans window
		this.winContent = new Window("<b>Connexion</b>", this);
		this.winContent.setCaptionAsHtml(true);
		this.winContent.center();
		this.winContent.setModal(true);
		this.winContent.setResizable(false);
		this.winContent.setSizeUndefined();
		this.winContent.setDraggable(false);
		this.winContent.setClosable(false);

	}

	public TextField getTxtUserName() {
		return txtUserName;
	}

	public PasswordField getTxtPassword() {
		return txtPassword;
	}

	public Window getWinContent() {
		return winContent;
	}

	public Button getBtnConnexion() {
		return btnConnexion;
	}

	public Button getBtnFemer() {
		return btnFemer;
	}
	
	

}
