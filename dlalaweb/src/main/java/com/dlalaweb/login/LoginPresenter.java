
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
import com.vaadin.server.ErrorEvent;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.ErrorMessage;
import com.vaadin.server.UserError;
import com.vaadin.shared.ui.ErrorLevel;
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
		//récupération des saisie
		String username = view.getTxtUserName().getValue();
		String password = view.getTxtPassword().getValue();
		Utilisateur user = service.connect(username, password);
		
		if(user != null) {
			setChanged();
			notifyObservers(user);
			this.getView().getBtnConnexion().setComponentError(null);
		}else {
			this.getView().getBtnConnexion().setComponentError(new UserError("Nom d'utilisateur ou password incorrect!"));
		}
		

	}

	public LoginView getView() {
		return view;
	}

	public LoginModel getModel() {
		return model;
	}

}
