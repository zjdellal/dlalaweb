package com.dlalaweb;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import com.dlalacore.dlala.entities.Phone;
import com.dlalacore.dlala.entities.Utilisateur;
import com.dlalaweb.login.LoginPresenter;
import com.dlalaweb.phones.PhonesPresenter;
import com.dlalaweb.phones.details.DetailsPhonePresenter;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
public class MyUIControlleur implements Observer, Serializable {
	private LoginPresenter				login;
	private PhonesPresenter				phones;
	private DetailsPhonePresenter	phone;

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
			phones = new PhonesPresenter();
			phones.addObserver(this);
			UI.getCurrent().setContent(phones.getView());
		}
		if (o instanceof PhonesPresenter) {
			if (arg instanceof Phone) {
				phone = new DetailsPhonePresenter((Phone) arg);
				UI.getCurrent().addWindow(phone.getView().getWinContent());

			}
			if(arg.equals("Ajouter téléphone")) {
				phone = new DetailsPhonePresenter();
				UI.getCurrent().addWindow(phone.getView().getWinContent());
				
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
