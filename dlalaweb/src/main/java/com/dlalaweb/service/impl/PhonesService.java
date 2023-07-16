package com.dlalaweb.service.impl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;

import com.dlalacore.dlala.entities.Phone;
import com.dlalaweb.service.client.RestClient;

public class PhonesService {
	private RestClient<Phone> client;
	private String phones = "phones/";
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

	
	

}
