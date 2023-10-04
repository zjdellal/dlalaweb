package com.dlalaweb;

import java.io.Serializable;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.dlalacore.dlala.entities.Fiche;
import com.dlalacore.dlala.entities.Phone;
import com.dlalacore.dlala.entities.Utilisateur;
import com.dlalaweb.login.LoginPresenter;
import com.dlalaweb.phones.PhonesPresenter;
import com.dlalaweb.phones.details.DetailsController;
import com.dlalaweb.phones.details.onglet.Phone.DetailsPhonePresenter;
import com.dlalaweb.phones.details.onglet.historiquereparations.HistoReparationsPresenter;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
public class MyUIControlleur implements Observer, Serializable {
	private LoginPresenter						login;
	private PhonesPresenter						phones;
	private DetailsPhonePresenter			phone;
	private HistoReparationsPresenter	historique;
	private DetailsController					detailController;

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
				detailController =  new DetailsController((Phone) arg);
				detailController.addObserver(this);
				UI.getCurrent().addWindow(detailController.getView().getWinContent());
				
				
				
				
//				phone = new DetailsPhonePresenter((Phone) arg);
//				phone.addObserver(this);
//				this.detailController = new DetailsController();
//				this.detailController.getView().getTabSheetContent()
//				    .addTab(phone.getView(), "Détails du téléphone", VaadinIcons.PHONE).setClosable(true);
//				if(!phone.getHistoreparations().isEmpty()) {
//					historique = new HistoReparationsPresenter(phone.getHistoreparations());
//					historique.addObserver(this);
//					this.detailController.getView().getTabSheetContent()
//					    .addTab(historique.getView().getContent(), "Historique de réparation", VaadinIcons.BOOKMARK)
//					    .setClosable(false);
//					detailController.getView().getTabSheetContent().setSelectedTab(1);
				}

//				UI.getCurrent().addWindow(detailController.getView().getWinContent());

			}
		
			if (arg.equals("Ajouter téléphone")) {
//				phone = new DetailsPhonePresenter();
				detailController =  new DetailsController();
				UI.getCurrent().addWindow(detailController.getOngletPhone());

			}

		

		if (o instanceof DetailsController) {
//			if (arg.equals("Historique éxistante")) {
//				historique = new HistoReparationsPresenter(phone.getHistoreparations());
//				historique.addObserver(this);
//				this.detailController.getView().getTabSheetContent()
//				    .addTab(historique.getView().getContent(), "Historique de réparation", VaadinIcons.BOOKMARK)
//				    .setClosable(false);
//
//			}
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
