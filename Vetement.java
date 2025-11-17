package mvcEntities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the vetements database table.
 * 
 */
@Entity
@Table(name="vetements")
@NamedQueries({
	  @NamedQuery(name = "Vetement.findAll", query = "SELECT v FROM Vetement v"),
	  @NamedQuery(
		        name = "Vetement.findByNom",
		        query = "SELECT v FROM Vetement v WHERE v.nom LIKE :searchTerm"
		    )
})
public class Vetement  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String adresse;

	@Lob
	private String description;

	private String imageurl;

	private String nom;

	private float prix;

	
	public Vetement() {
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

	public float getPrix() {
		return this.prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}



}