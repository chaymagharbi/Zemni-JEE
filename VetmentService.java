package mvcModel;

import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.TypedQuery;
import mvcEntities.Vetement;

import java.util.ArrayList;
import java.util.List;

/**
 * Session Bean implementation class VetmentService
 */
@Stateless
@LocalBean
public class VetmentService {
	@PersistenceContext(unitName="zemni")
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public VetmentService() {
        // TODO Auto-generated constructor stub
    }public boolean AddVetement(String nom, String description, String imageUrl,String adresse,float prix) {
    	 try {
    	        
        Vetement v = new Vetement();
           v.setNom(nom);
           v.setDescription(description);
           v.setImageurl(imageUrl);
           v.setAdresse(adresse);
           v.setPrix(prix);
           em.persist(v);
           System.out.println("Vetement persisté avec ID: " + v.getId());
           return true;
       } catch (Exception e) {
           System.err.println("Erreur persistance Vetement:");
           e.printStackTrace();
           return false;
       }
             
       }

    public boolean deleteVetementById(int id) {
        try {
            Vetement v = em.find(Vetement.class, id);
            if (v != null) {
                em.remove(v);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }       public List<Vetement> getAllVetements() {
           List<Vetement> vetement = new ArrayList<Vetement>();
           TypedQuery<Vetement> query = em.createNamedQuery("Vetement.findAll", Vetement.class);
           vetement = query.getResultList();
           return vetement;
       }

       public List<Vetement> getVetementsByNom(String nom) {
    	    try {
    	        return em.createNamedQuery("Vetement.findByNom", Vetement.class)
    	                 .setParameter("searchTerm", "%" + nom + "%")
    	                 .getResultList();
    	    } catch (Exception e) {
    	        throw new RuntimeException("Erreur lors de la recherche de vetement", e);
    	    }
    	}
       public List<Vetement> searchVetements(String term) {
           try {
               return em.createNamedQuery("Vetement.findByNom", Vetement.class)
                        .setParameter("searchTerm", "%" + term + "%")
                        .getResultList();
           } catch (Exception e) {
               throw new RuntimeException("Erreur lors de la recherche de vêtements", e);
           }
       }
	public Object getVetementById(int id) {
		try {
	        return em.find(Vetement.class, id);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	public boolean update(int id, String nom, float prix, String description, String imageurl,String adresse) {
	    Vetement v = em.find(Vetement.class, id);
	    if(v == null) return false;
	    
	    v.setNom(nom);
	    v.setPrix(prix);
	    v.setDescription(description);
	    v.setImageurl(imageurl);
	    v.setAdresse(adresse);
	    
	    try {
	        em.merge(v);
	        return true;
	    } catch(Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public Vetement getById(int id) {
	    return em.find(Vetement.class, id);
	}


   }


