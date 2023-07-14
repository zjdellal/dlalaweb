
/**
 * Classe login présenter: intéragit avec la vue et le moel afin de manipuler les composants
 *  et les données qui vont y transités
 *  @author dev-zak
 *  @créer le 09/07/2023
 */
package com.dlalaweb.login;

import java.util.Observable;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;

import com.dlalacore.dlala.entities.Utilisateur;
import com.dlalaweb.service.client.RestClient;
import com.dlalaweb.service.impl.UtilisateurService;
import com.vaadin.ui.Notification;

import aj.org.objectweb.asm.Type;

public class LoginPresenter extends Observable {
	private LoginView						view;
	private LoginModel					model;
	private UtilisateurService	service;

	public LoginPresenter() {
		this.view = new LoginView();
		this.model = new LoginModel();
		service = new UtilisateurService();
		setlistenersComponents();

	}

	private void setlistenersComponents() {
		this.view.getBtnConnexion().addClickListener(e -> onBtnLoginClicked());
	}

	private void onBtnLoginClicked() {
		Utilisateur u = service.connect("hbibti", "test");
		System.out.println(u);
		Notification.show("bienvenue " + u.toString(), Notification.Type.WARNING_MESSAGE);

	}

	public LoginView getView() {
		return view;
	}

	public LoginModel getModel() {
		return model;
	}

}
