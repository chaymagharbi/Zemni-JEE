package mvcModel;

import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.TypedQuery;
import mvcEntities.Article;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Session Bean implementation class ArticleService
 */
@Stateless
@LocalBean
public class ArticleService {
	@PersistenceContext(unitName="zemni")
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public ArticleService() {
        // TODO Auto-generated constructor stub
    }
    public Article getArticleByTitre(String titre) {
	    try {
	        TypedQuery<Article> query = em.createNamedQuery("Article.findByTitre", Article.class);
	        query.setParameter("articleTitre", "%" + titre + "%");	        
	        return query.getSingleResult();
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	        return null;
	    }
	}
    public boolean ajouterArticle(String titre, String description, Date datepub, String imageurl) {
        try {
            if (titre == null || titre.trim().isEmpty()) {
                throw new IllegalArgumentException("Le titre ne peut pas être vide");
            }
            
            // Création et persistance de l'article
            Article a = new Article();
            a.setTitre(titre);
            a.setDescription(description);
            a.setDatepub(datepub);
            a.setImageurl(imageurl);
            
            em.persist(a);
            System.out.println("Article persisté avec ID: " + a.getId()); // Log utile pour le débogage
            
            return true;
        } catch (Exception e) {
            System.err.println("Erreur lors de l'ajout de l'article:");
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Article> getAllArticles() {
        System.out.println("Exécution de getAllArticles()");
        List<Article> articles = em.createNamedQuery("Article.findAll", Article.class)
                                 .getResultList();
        System.out.println("Articles trouvés : " + articles.size());
        return articles;
    }
   
    public boolean deleteArticleById(int id) {
        try {
            Article article = em.find(Article.class, id);
            if (article != null) {
                em.remove(article);
                return true; 
            }
            return false; 
        } catch (Exception e) {
            e.printStackTrace();
            return false; 
        }
    }

    public List<Article> searchArticles(String term) {
        try {
            return em.createNamedQuery("Article.findByTitre", Article.class)
                     .setParameter("searchTerm", "%" + term + "%")
                     .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche d'articles", e);
        }
    }

    public Article getArticleById(int id) {
        try {
            return em.find(Article.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean update(int id, String titre, String description, String imageurl, Date datepub) {
        Article article = em.find(Article.class, id);
        if (article == null) return false;

        article.setTitre(titre);
        article.setDescription(description);
        article.setImageurl(imageurl);
        article.setDatepub(datepub);

        try {
            em.merge(article);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Article getById(int id) {
        return em.find(Article.class, id);
    }

}

