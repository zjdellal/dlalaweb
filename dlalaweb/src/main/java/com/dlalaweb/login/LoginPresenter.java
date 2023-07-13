
/**
 * Classe login présenter: intéragit avec la vue et le moel afin de manipuler les composants
 *  et les données qui vont y transités
 *  @author dev-zak
 *  @créer le 09/07/2023
 */
package com.dlalaweb.login;

import java.util.Observable;

public class LoginPresenter extends Observable {
	private LoginView		view;
	private LoginModel	model;

	public LoginPresenter() {
		this.view = new LoginView();
		this.model = new LoginModel();

	}

	public LoginView getView() {
		return view;
	}

	public LoginModel getModel() {
		return model;
	}

}
