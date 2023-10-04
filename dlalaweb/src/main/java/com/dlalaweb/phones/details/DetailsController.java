package com.dlalaweb.phones.details;

import java.util.Observable;
import java.util.Observer;

import com.dlalacore.dlala.entities.Phone;
import com.dlalaweb.phones.details.onglet.Phone.DetailsPhonePresenter;
import com.dlalaweb.phones.details.onglet.historiquereparations.HistoReparationsPresenter;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Window;

public class DetailsController extends Observable implements Observer {
	private DetailsView view;
	private DetailsPhonePresenter			detailsPhone;
	private HistoReparationsPresenter	historique;
	
	public DetailsController() {
		this.view =  new DetailsView();
		detailsPhone  =  new DetailsPhonePresenter();
		detailsPhone.addObserver(this);
	}

	public DetailsController(Phone phoneSelected) {
		this.view =  new DetailsView();
		detailsPhone  =  new DetailsPhonePresenter(phoneSelected);
		detailsPhone.addObserver(this);
		this.getView().getTabSheetContent()
    .addTab(detailsPhone.getView(), "Détails du téléphone", VaadinIcons.PHONE).setClosable(true);
if(!detailsPhone.getHistoreparations().isEmpty()) {
		detailsPhone.ifFicheExiste();

		this.getView().getTabSheetContent().setSelectedTab(0);
}

	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof DetailsPhonePresenter) {
//		if (arg.equals("Historique éxistante")) {
//			historique = new HistoReparationsPresenter(phone.getHistoreparations());
//			historique.addObserver(this);
//			this.detailController.getView().getTabSheetContent()
//			    .addTab(historique.getView().getContent(), "Historique de réparation", VaadinIcons.BOOKMARK)
//			    .setClosable(false);
//
//		}
		if (arg.equals("close window")) {
			setChanged();
			notifyObservers("close window");
			
			
		}
		if(arg.equals("Historique éxistante")){
			
			
			historique = new HistoReparationsPresenter(detailsPhone.getHistoreparations());
			historique.addObserver(this);
		this.getView().getTabSheetContent()
		    .addTab(historique.getView().getContent(), "Historique de réparation", VaadinIcons.BOOKMARK)
		    .setClosable(false);
		this.getView().getTabSheetContent().setSelectedTab(0);
			
		}
	}

	}

	public DetailsView getView() {
		return view;
	}
	
	public Window getOngletPhone() {
		 this.getView().getTabSheetContent()
		    .addTab(detailsPhone.getView(), "Détails du téléphone", VaadinIcons.PHONE).setClosable(true);
		 return getView().getWinContent();
	}


	
	

}
