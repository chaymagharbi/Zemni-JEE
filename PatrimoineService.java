package mvcModel;

import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.TypedQuery;
import mvcEntities.Patrimoine;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import jakarta.ejb.Stateless;

/**
 * Session Bean implementation class PatrimoineService
 */
@Stateless
@LocalBean
public class PatrimoineService {
	@PersistenceContext(unitName="zemni")
	private EntityManager em;
    public PatrimoineService() {    	
    }
    public boolean addPatrimoine(String nom, String description, String imageUrl,String adresse) {
        try {
        
    	Patrimoine p = new Patrimoine();
        
        p.setNom(nom);
        p.setDescription(description);
        p.setImageurl(imageUrl);
        p.setAdresse(adresse);
        em.persist(p);
        System.out.println("Patrimoine persisté avec ID: " + p.getId());
        return true;
    } catch (Exception e) {
        System.err.println("Erreur persistance Patrimoine:");
        e.printStackTrace();
        return false;
    }
}
    public boolean deletePatrimoineById(int id) {
        try {
            Patrimoine p = em.find(Patrimoine.class, id);
            if (p != null) {
                em.remove(p);
                return true; // Suppression réussie
            }
            return false; // Aucun élément trouvé avec cet ID
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Erreur lors de la suppression
        }
    }

    public List<Patrimoine> getAllPatrimoines() {
        List<Patrimoine> patrimoine = new ArrayList<Patrimoine>();
        TypedQuery<Patrimoine> query = em.createNamedQuery("Patrimoine.findAll", Patrimoine.class);
        patrimoine = query.getResultList();
        return patrimoine;
    }

    public List<Patrimoine> getPatrimoinesByNom(String nom) {
        try {
            return em.createNamedQuery("Patrimoine.findByNom", Patrimoine.class)
                     .setParameter("searchTerm", "%" + nom + "%") 
                     .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche de patrimoine", e);
        }
    }
    public List<Patrimoine> searchPatrimoines(String term) {
        try {
            return em.createNamedQuery("Patrimoine.findByNom", Patrimoine.class)
                     .setParameter("searchTerm", "%" + term + "%")
                     .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche", e);
        }
    }      public Patrimoine getPatrimoineById(int id) {
        try {
            return em.find(Patrimoine.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean update(int id, String nom, String description, String imageurl,String adresse) {
        Patrimoine p = em.find(Patrimoine.class, id);
        if(p == null) return false;
        
        p.setNom(nom);
        p.setDescription(description);
        p.setImageurl(imageurl);
        p.setAdresse(adresse);
        
        try {
            em.merge(p);
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Patrimoine getById(int id) {
        return em.find(Patrimoine.class, id);
    }
    }
