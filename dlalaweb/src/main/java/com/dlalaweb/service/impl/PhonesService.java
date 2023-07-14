package com.dlalaweb.service.impl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;

import com.dlalacore.dlala.entities.Phones;
import com.dlalaweb.service.client.RestClient;

public class PhonesService {
	private RestClient<Phones> client;
	private String phones = "phones/";
	public PhonesService() {
		client = new RestClient<>();
	}
	
	public List<Phones> findAll() {
		
		List<Phones> phoneList = client.getAll(phones, new ParameterizedTypeReference<List<Phones>>() {
		});
		if (client.getStatus().equals(HttpStatus.NOT_FOUND)) {
			return null;
		}
		return phoneList;
		
	}

	
	

}
