package com.dlalaweb;

import java.util.Observable;
import java.util.Observer;

import com.dlalaweb.login.LoginPresenter;

public class MyUIControlleur implements Observer {
	private LoginPresenter login;
	
	 public MyUIControlleur() {
		login =  new LoginPresenter();
		login.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	public LoginPresenter getLogin() {
		return login;
	}

	
}
