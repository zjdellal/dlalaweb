package com.dlalaweb.service.impl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;

import com.dlalacore.dlala.entities.Utilisateur;
import com.dlalaweb.service.client.RestClient;
import com.dlalaweb.service.impl.interfaces.IUtilisateurService;

public class UtilisateurService implements IUtilisateurService {
	private RestClient<Utilisateur> client;
	private String utilisateur = "utilisateur/";
	private String connect = "connect/";

	public UtilisateurService() {
		client = new RestClient<>();
	}

	@Override
	public List<Utilisateur> findAll() {

		return null;
	}

	@Override
	public Utilisateur findByID(int id) {
		Utilisateur user = client.get(utilisateur+id, new ParameterizedTypeReference<Utilisateur>() {
		});
		if (client.getStatus().equals(HttpStatus.NOT_FOUND)) {
			return null;
		}
		return user;

	}

	@Override
	public Utilisateur save() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur update(Utilisateur user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Utilisateur connect(String nom, String password) {
		String info = nom+"/"+password; 
		Utilisateur user  =  client.get(connect+ info, new ParameterizedTypeReference<Utilisateur>() {
		});
		if (client.getStatus().equals(HttpStatus.NOT_FOUND)) {
			return null;
		}
		return user;
	}

}
