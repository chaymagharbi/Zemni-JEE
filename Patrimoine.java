package mvcEntities;

import java.io.Serializable;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the patrimoine database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "Patrimoine.findAll", query = "SELECT p FROM Patrimoine p"),
	@NamedQuery(
	        name = "Patrimoine.findByNom",  
	        query = "SELECT p FROM Patrimoine p WHERE p.nom LIKE :searchTerm"
	    )
	})
public class Patrimoine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	

	private String adresse;
	private String description;
	private String imageurl;
	private String nom;

	

	public Patrimoine() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageurl() {
		return this.imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}


}