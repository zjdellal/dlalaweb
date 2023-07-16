package com.dlalaweb.phones;

import java.util.ArrayList;
import java.util.List;

import com.dlalacore.dlala.entities.Phone;

public class PhonesModel {
	private List<Phone> phoneList;
	private PhoneListener listener;
	
	public PhonesModel() {
		phoneList =  new ArrayList<>();
	}

	public List<Phone> getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(List<Phone> phoneList) {
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
