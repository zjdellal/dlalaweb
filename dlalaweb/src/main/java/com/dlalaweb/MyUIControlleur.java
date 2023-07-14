package com.dlalaweb;

import java.util.Observable;
import java.util.Observer;

import com.dlalacore.dlala.entities.Utilisateur;
import com.dlalaweb.login.LoginPresenter;
import com.vaadin.ui.Notification;

public class MyUIControlleur implements Observer {
	private LoginPresenter login;
	private Utilisateur user;
	
	 public MyUIControlleur() {
		login =  new LoginPresenter();
		login.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof LoginPresenter) {
			if(arg instanceof Utilisateur) {
				user = (Utilisateur) arg;
			}
			getLogin().getView().getWinContent().close();
			Notification.show("bienvenue " + user.toString(), Notification.Type.WARNING_MESSAGE);

		}

	}

	public LoginPresenter getLogin() {
		return login;
	}

	
}
