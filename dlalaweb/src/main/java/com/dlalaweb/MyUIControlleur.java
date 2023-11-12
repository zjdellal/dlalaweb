package com.dlalaweb;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import com.dlalacore.dlala.entities.Phone;
import com.dlalacore.dlala.entities.Utilisateur;
import com.dlalaweb.login.LoginPresenter;
import com.dlalaweb.phones.PhonesPresenter;
import com.dlalaweb.phones.details.DetailsController;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
public class MyUIControlleur implements Observer, Serializable {
	private LoginPresenter		login;
	private PhonesPresenter		phones;

	private DetailsController	detailController;

	public MyUIControlleur() {
		login = new LoginPresenter();
		login.addObserver(this);

	}

	public MyUIControlleur(boolean refresh) {
		phones = new PhonesPresenter();
		phones.addObserver(this);
		UI.getCurrent().setContent(phones.getView());

	}

	@Override
	public void update(Observable o, Object arg) {
		Utilisateur user = null;
		if (o instanceof LoginPresenter) {
			if (arg instanceof Utilisateur && arg != null) {
				user = (Utilisateur) arg;
				setUserSession(user);
				getLogin().getView().getWinContent().close();
				phones = new PhonesPresenter();
				phones.addObserver(this);
				UI.getCurrent().setContent(phones.getView());
		
			}

		}
		if (o instanceof PhonesPresenter) {
			if (arg instanceof Phone) {
				detailController = new DetailsController((Phone) arg);
				detailController.addObserver(this);
				UI.getCurrent().addWindow(detailController.getView().getWinContent());

			}

		

		}

		if (arg.equals("Ajouter téléphone")) {
			detailController = new DetailsController();
			UI.getCurrent().addWindow(detailController.getDetailsPhone().getView().getWinContent());

		}

		if (o instanceof DetailsController) {
			
			if (arg.equals("close window")) {
				phones = new PhonesPresenter();
				phones.addObserver(this);
				UI.getCurrent().setContent(phones.getView());
			}
			if(arg.equals("téléphone supprimé")) {
				phones = new PhonesPresenter();
				phones.addObserver(this);
				UI.getCurrent().setContent(phones.getView());
				System.err.println("notif de details control");
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
