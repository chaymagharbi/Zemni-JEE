package mvcEntities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the article database table.
 * 
 */
@Entity
@NamedQueries({
@NamedQuery(name="Article.findAll", query="SELECT a FROM Article a"),
@NamedQuery(name="Article.findByTitre", query="SELECT a FROM Article a WHERE a.titre LIKE :articleTitre")
})
public class Article  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	private Date datepub;

	@Lob
	private String description;

	private String imageurl;

	private String titre;

	public Article() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatepub() {
		return this.datepub;
	}

	public void setDatepub(Date datepub) {
		this.datepub = datepub;
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

	public String getTitre() {
		return this.titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

}