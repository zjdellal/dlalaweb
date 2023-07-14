package com.dlalaweb.service.impl.interfaces;

import java.util.List;

import com.dlalacore.dlala.entities.Utilisateur;

public interface IUtilisateurService {
	public List<Utilisateur> findAll();
	
	public Utilisateur findByID(int id);
	
	public Utilisateur save();
	
	public Utilisateur update(Utilisateur user);
	
	public void delete(int id);
	
	public Utilisateur connect(String nom, String password);
	
	
}
