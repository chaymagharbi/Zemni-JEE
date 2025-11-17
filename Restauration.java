package mvcEntities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;



/**
 * The persistent class for the restauration database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "Restauration.findAll", query = "SELECT r FROM Restauration r"),
	 @NamedQuery(
		        name = "Restauration.findByNom",
		        query = "SELECT r FROM Restauration r WHERE r.nom LIKE :searchTerm"
		    )

	})
public class Restauration  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	
	private String adresse;
	private String description;
	private String imageurl;
	private String nom;

	

	public Restauration() {
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