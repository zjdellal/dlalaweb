package com.dlalaweb.phones;

import java.util.ArrayList;
import java.util.List;

import com.dlalacore.dlala.entities.Phones;

public class PhonesModel {
	private List<Phones> phoneList;
	private PhoneListener listener;
	
	public PhonesModel() {
		phoneList =  new ArrayList<>();
	}

	public List<Phones> getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(List<Phones> phoneList) {
		this.phoneList = phoneList;
		if(!phoneList.isEmpty()) {
			listener.onPhoneListChange();
		}
	}

	public PhoneListener getListener() {
		return listener;
	}

	public void setListener(PhoneListener listener) {
		this.listener = listener;
	}
	
	
	
	
	

}
