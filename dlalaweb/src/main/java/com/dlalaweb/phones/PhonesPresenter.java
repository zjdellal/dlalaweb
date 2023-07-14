package com.dlalaweb.phones;

import java.util.Observable;

public class PhonesPresenter extends Observable {
	private PhonesView<Object>	view;
	private PhonesModel					model;

	public PhonesPresenter() {
		view = new PhonesView<>();
		model = new PhonesModel();
	}

}
