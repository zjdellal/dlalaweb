package com.dlalaweb.service.impl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;

import com.dlalacore.dlala.entities.Fiche;
import com.dlalaweb.service.client.RestClient;

public class FicheService {
	private String						fiches	= "fichesbyid/";
	private RestClient<Fiche>	client;

	public FicheService() {
		client = new RestClient<>();
	}

	public List<Fiche> getFichesByIdPhone(int idPhone) {

		List<Fiche> ficheListe = client.getAll(fiches + idPhone, new ParameterizedTypeReference<List<Fiche>>() {
		});
		if (client.getStatus().equals(HttpStatus.NOT_FOUND)) {
			return null;
		}
		return ficheListe;

	}
}