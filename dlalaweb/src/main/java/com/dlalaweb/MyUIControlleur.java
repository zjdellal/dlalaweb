package com.dlalaweb;

import java.util.Observable;
import java.util.Observer;

import com.dlalacore.dlala.entities.Utilisateur;
import com.dlalaweb.login.LoginPresenter;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

public class MyUIControlleur implements Observer {
	private LoginPresenter login;

	public MyUIControlleur() {
		login = new LoginPresenter();
		login.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		Utilisateur user = null;
		if (o instanceof LoginPresenter) {
			if (arg instanceof Utilisateur && arg != null) {
				user = (Utilisateur) arg;
				setUserSession(user);
				getLogin().getView().getWinContent().close();
				Notification.show("bienvenue " + user.toString(), Notification.Type.WARNING_MESSAGE);
			}

		}

	}

	public LoginPresenter getLogin() {
		return login;
	}

	private static void setUserSession(Utilisateur user) {
		UI.getCurrent().getSession().setAttribute("userSession", user);

	}

}
