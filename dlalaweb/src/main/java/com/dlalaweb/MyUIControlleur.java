package com.dlalaweb;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import com.dlalacore.dlala.entities.Phone;
import com.dlalacore.dlala.entities.Utilisateur;
import com.dlalaweb.login.LoginPresenter;
import com.dlalaweb.phones.PhonesPresenter;
import com.dlalaweb.phones.details.DetailsController;
import com.dlalaweb.phones.details.onglet.Phone.DetailsPhonePresenter;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
public class MyUIControlleur implements Observer, Serializable {
	private LoginPresenter				login;
	private PhonesPresenter				phones;
	private DetailsPhonePresenter	phone;
	private DetailsController			detailController;

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
				this.detailController = new DetailsController();
				this.detailController.getView().getTabSheetContent()
				    .addTab(phone.getView(), "Détails du téléphone", VaadinIcons.PHONE).setClosable(true);

				UI.getCurrent().addWindow(detailController.getView().getWinContent());

			}
			if (arg.equals("Ajouter téléphone")) {
				phone = new DetailsPhonePresenter();
				UI.getCurrent().addWindow(phone.getView().getWinContent());

			}

		}
		if (o instanceof DetailsPhonePresenter) {
			if (arg.equals("close window")) {
				phones.getView().getgPhones().select(null);
				System.out.println("select null");
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
