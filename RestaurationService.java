package mvcModel;

import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.TypedQuery;
import mvcEntities.Restauration;

import java.util.ArrayList;
import java.util.List;

/**
 * Session Bean implementation class RestaurationService
 */
@Stateless
@LocalBean
public class RestaurationService {
	@PersistenceContext(unitName="zemni")
	private EntityManager em;

    
    public RestaurationService() {
        // TODO Auto-generated constructor stub
    }
    public List<Restauration> getRestaurationsByNom(String nom) {
        try {
            return em.createNamedQuery("Restauration.findByNom", Restauration.class)
                     .setParameter("searchTerm", "%" + nom + "%")
                     .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche de restauration", e);
        }
    }
    public boolean addRestauration(String nom, String description, String imageUrl, String adresse) {
        try {
            Restauration r = new Restauration();
           
            r.setNom(nom);
            r.setDescription(description);
            r.setImageurl(imageUrl);
            r.setAdresse(adresse);

                em.persist(r);
                return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean deleteRestaurationById(int id) {
        try {
            Restauration r = em.find(Restauration.class, id);
            if (r != null) {
                em.remove(r);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<Restauration> getAllRestaurations() {
        List<Restauration> patrimoine = new ArrayList<Restauration>();
        TypedQuery<Restauration> query = em.createNamedQuery("Restauration.findAll", Restauration.class);
        patrimoine = query.getResultList();
        return patrimoine;
    }

   
    public List<Restauration> searchRestaurations(String term) {
        try {
            return em.createNamedQuery("Restauration.findByNom", Restauration.class)
                     .setParameter("searchTerm", "%" + term + "%")
                     .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche de restaurations", e);
        }
    }
	public Object getRestaurationById(int id) {
		try {
	        return em.find(Restauration.class, id);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	public boolean update(int id, String nom,String description, String imageurl,String adresse ) {
	    Restauration r = em.find(Restauration.class, id);
	    if(r == null) return false;
	    
	    r.setNom(nom);
	    r.setDescription(description);
	    r.setImageurl(imageurl);
	    r.setAdresse(adresse);
	    try {
	        em.merge(r);
	        return true;
	    } catch(Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public Restauration getById(int id) {
	    return em.find(Restauration.class, id);
	}
}

