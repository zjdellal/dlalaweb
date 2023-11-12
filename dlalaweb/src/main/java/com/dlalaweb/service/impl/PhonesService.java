package com.dlalaweb.service.impl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;

import com.dlalacore.dlala.entities.Phone;
import com.dlalaweb.service.client.RestClient;

public class PhonesService {
	private RestClient<Phone> client;
	private String phones = "phones/";
	private String phoneAdd = "addPhone";
	private String   deletePhone = "deletePhone/";
	public PhonesService() {
		client = new RestClient<>();
	}
	
	public List<Phone> findAll() {
		
		List<Phone> phoneList = client.getAll(phones, new ParameterizedTypeReference<List<Phone>>() {
		});
		if (client.getStatus().equals(HttpStatus.NOT_FOUND)) {
			return null;
		}
		return phoneList;
		
	}
	
	public Phone save(Phone phone) {
		Phone phoneSer = client.post(phoneAdd, phone, new ParameterizedTypeReference<Phone>() {
		});
		return phoneSer;
	}
	

	public boolean deletePhone(Integer idFiche) {

		@SuppressWarnings("unused")
		Phone phoneServ = client.get(deletePhone+idFiche, new ParameterizedTypeReference<Phone>() {
		});
		if (client.getStatus().equals(HttpStatus.OK)) {
			return true;
		}
		return false;
	}
	
	

	
	

}
