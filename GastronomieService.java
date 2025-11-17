package mvcModel;

import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.TypedQuery;
import mvcEntities.Gastronomie;

import java.util.ArrayList;
import java.util.List;

import jakarta.ejb.Stateless;

/**
 * Session Bean implementation class GastronomieService
 */
@Stateless
@LocalBean
public class GastronomieService {
	@PersistenceContext(unitName="zemni")
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public GastronomieService() {
        // TODO Auto-generated constructor stub
    }
    public List<Gastronomie> getGastronomiesByNom(String nom) {
        try {
            return em.createNamedQuery("Gastronomie.findByNom", Gastronomie.class)
                     .setParameter("searchTerm", "%" + nom + "%")
                     .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche de gastronomie", e);
        }
    }

      public boolean addGastronomie(String nom, String description, String imageUrl,String adresse,float prix) {
    	  try {
    	       
       Gastronomie g = new Gastronomie();
          
          g.setNom(nom);
          g.setDescription(description);
          g.setImageurl(imageUrl);
          g.setAdresse(adresse);
          g.setPrix(prix);
          em.persist(g);
          System.out.println("Gastronomie persisté avec ID: " + g.getId());
          return true;
      } catch (Exception e) {
          System.err.println("Erreur persistance gastronomie:");
          e.printStackTrace();
          return false;
      }
      }

      public boolean deleteGastronomieById(int id) {
    	    try {
    	        Gastronomie g = em.find(Gastronomie.class, id);
    	        if (g != null) {
    	            em.remove(g);
    	            return true;
    	        }
    	        return false;
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	        return false;
    	    }
    	}

      public List<Gastronomie> getAllGastronomie() {
          List<Gastronomie> gastronomie = new ArrayList<Gastronomie>();
          TypedQuery<Gastronomie> query = em.createNamedQuery("Gastronomie.findAll", Gastronomie.class);
          gastronomie = query.getResultList();
          return gastronomie;
      }
      public List<Gastronomie> searchGastronomies(String term) {
          try {
              return em.createNamedQuery("Gastronomie.findByNom", Gastronomie.class)
                       .setParameter("searchTerm", "%" + term + "%")
                       .getResultList();
          } catch (Exception e) {
              throw new RuntimeException("Erreur lors de la recherche de gastronomies", e);
          }
      }
  
	public Object getGastronomieById(int id) {
		try {
	        return em.find(Gastronomie.class, id);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	public boolean update(int id, String nom, float prix, String description, String imageurl,String adresse) {
	    Gastronomie g = em.find(Gastronomie.class, id);
	    if(g == null) return false;
	    
	    g.setNom(nom);
	    g.setPrix(prix);
	    g.setDescription(description);
	    g.setImageurl(imageurl);
	    g.setAdresse(adresse);
	    
	    try {
	        em.merge(g);
	        return true;
	    } catch(Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public Gastronomie getById(int id) {
	    return em.find(Gastronomie.class, id);
	}
  }

