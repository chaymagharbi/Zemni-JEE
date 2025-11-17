package mvcController;

import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvcModel.*;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import mvcEntities.*;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private PatrimoineService patrmoineService;
	@EJB
	private RestaurationService restauarationService;
	@EJB
	private VetmentService vetmentService;
	@EJB
	private GastronomieService gastronomieService;
	@EJB
	private ArticleService articleService;
    public Controller() {
        super();
       
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        String btn = request.getParameter("myBtn");    
        String action = request.getParameter("action");

        try {
            if (btn != null && btn.equals("Rechercher")) { 
                String nom = request.getParameter("nom");  
                System.out.println("Terme de recherche reçu: " + nom);

                List<Patrimoine> patrimoines = patrmoineService.searchPatrimoines(nom);
                List<Restauration> restaurations = restauarationService.searchRestaurations(nom);
                List<Gastronomie> gastronomies = gastronomieService.searchGastronomies(nom);
                List<Vetement> vetements = vetmentService.searchVetements(nom);
                
                List<Object> allResults = new ArrayList<>();
                allResults.addAll(patrimoines);
                allResults.addAll(restaurations);
                allResults.addAll(gastronomies);
                allResults.addAll(vetements);
                
                if (!allResults.isEmpty()) {
                    request.setAttribute("listCategories", allResults);
                } else {
                    request.setAttribute("msg", "Aucun résultat ne correspond au critère de recherche");
                }
                request.getRequestDispatcher("recherche.jsp").forward(request, response);
            }
            else if(btn != null && btn.equals("ajouter")) {
                String nom = request.getParameter("nom");
                String description = request.getParameter("description");
                String adresse = request.getParameter("adresse");
                String imageUrl = request.getParameter("imageUrl");
                String categorie = request.getParameter("categorie");
                boolean test = false;
                
                if(categorie!=null && categorie.equals("patrimoine")) {
                    test = patrmoineService.addPatrimoine(nom, description, imageUrl, adresse);
                } else if(categorie!=null && categorie.equals("restauration")) {
                    test = restauarationService.addRestauration(nom, description, imageUrl, adresse);
                } else if(categorie!=null && categorie.equals("gastronomie")) {
                    float prix = Float.parseFloat(request.getParameter("prix"));
                    test = gastronomieService.addGastronomie(nom, description, imageUrl, adresse, prix);
                } else if(categorie!=null && categorie.equals("vetement")) {
                    float prix = Float.parseFloat(request.getParameter("prix"));
                    test = vetmentService.AddVetement(nom, description, imageUrl, adresse, prix);
                } else {
                    response.getWriter().append("Aucune catégorie sélectionnée");
                    return;
                }
                
                if(test) {
                    request.setAttribute("msg", "Élément ajouté avec succès");
                } else {
                    request.setAttribute("msg", "Échec d'ajout de l'élément");
                }
                request.getRequestDispatcher("ajoutCategorie.jsp").forward(request, response);
            }else if(btn != null && btn.equals("rechercherPourModification")) {
                try {
                    String searchTerm = request.getParameter("searchTerm");
                    
                    if(searchTerm == null || searchTerm.trim().isEmpty()) {
                        throw new Exception("Veuillez entrer un terme de recherche");
                    }
                    
                    Object foundItem = null;
                    String foundCategory = null;
                    
                    // Recherche par ID (si numérique)
                    try {
                        int id = Integer.parseInt(searchTerm);
                        
                        // Cherche dans toutes les catégories
                        foundItem = patrmoineService.getById(id);
                        if(foundItem != null) {
                            foundCategory = "patrimoine";
                        } else {
                            foundItem = restauarationService.getById(id);
                            if(foundItem != null) {
                                foundCategory = "restauration";
                            } else {
                                foundItem = gastronomieService.getById(id);
                                if(foundItem != null) {
                                    foundCategory = "gastronomie";
                                } else {
                                    foundItem = vetmentService.getById(id);
                                    if(foundItem != null) {
                                        foundCategory = "vetement";
                                    }
                                }
                            }
                        }
                    } catch(NumberFormatException e) {
                        // Si ce n'est pas un nombre, on cherche par nom
                    }
                    
                    // Si pas trouvé par ID, recherche par nom
                    if(foundItem == null) {
                        List<?> results;
                        
                        // Cherche dans chaque catégorie
                        results = patrmoineService.searchPatrimoines(searchTerm);
                        if(!results.isEmpty()) {
                            foundItem = results.get(0);
                            foundCategory = "patrimoine";
                        } else {
                            results = restauarationService.searchRestaurations(searchTerm);
                            if(!results.isEmpty()) {
                                foundItem = results.get(0);
                                foundCategory = "restauration";
                            } else {
                                results = gastronomieService.searchGastronomies(searchTerm);
                                if(!results.isEmpty()) {
                                    foundItem = results.get(0);
                                    foundCategory = "gastronomie";
                                } else {
                                    results = vetmentService.searchVetements(searchTerm);
                                    if(!results.isEmpty()) {
                                        foundItem = results.get(0);
                                        foundCategory = "vetement";
                                    }
                                }
                            }
                        }
                    }
                    
                    if(foundItem != null) {
                        request.setAttribute("element", foundItem);
                        request.setAttribute("categorie", foundCategory);
                    } else {
                        throw new Exception("Aucun élément trouvé avec ce critère");
                    }
                    
                    request.getRequestDispatcher("modifierCategorie.jsp").forward(request, response);
                    
                } catch(Exception e) {
                    request.setAttribute("msg", e.getMessage());
                    request.getRequestDispatcher("modifierCategorie.jsp").forward(request, response);
                }
            }else if(btn != null && btn.equals("modifier")) {
                int id = Integer.parseInt(request.getParameter("id"));
                String categorie = request.getParameter("categorie");
                Object element = null;
                
                try {
                    if(request.getParameter("nom") != null) {
                        String nom = request.getParameter("nom");
                        String description = request.getParameter("description");
                        String imageurl = request.getParameter("imageurl");
                        String adresse = request.getParameter("adresse");
                        
                        boolean success = false;
                        
                        switch(categorie) {
                            case "gastronomie":
                            case "vetement":
                                float prix = 0.0f;
                                try {
                                    String prixParam = request.getParameter("prix");
                                    if (prixParam != null && !prixParam.trim().isEmpty()) {
                                        prix = Float.parseFloat(prixParam);
                                    }
                                } catch (NumberFormatException e) {
                                    request.setAttribute("msg", "Le prix doit être un nombre valide (ex: 12.99)");
                                    request.getRequestDispatcher("modifierCategorie.jsp").forward(request, response);
                                    return;
                                }
                                
                                if("gastronomie".equals(categorie)) {
                                    success = gastronomieService.update(id, nom, prix, description, imageurl, adresse);
                                } else {
                                    success = vetmentService.update(id, nom, prix, description, imageurl, adresse);
                                }
                                break;
                                
                            case "patrimoine":
                                success = patrmoineService.update(id, nom, description, imageurl, adresse);
                                break;
                                
                            case "restauration":
                                success = restauarationService.update(id, nom, description, imageurl, adresse);
                                break;
                        }
                        
                        if(success) {
                            request.setAttribute("msg", "Modification réussie!");
                            request.setAttribute("msgType", "success");
                            // Conservez les mêmes attributs pour réafficher le formulaire
                            request.setAttribute("element", element);
                            request.setAttribute("categorie", categorie);
                        } else {
                            request.setAttribute("msg", "Erreur lors de la modification");
                            request.setAttribute("msgType", "error");
                        }
                        request.getRequestDispatcher("modifierCategorie.jsp").forward(request, response);
                        return;}
                } catch(Exception e) {
                    System.out.println("Erreur lors de la modification - ID: " + id);
                    System.out.println("Erreur: " + e.getMessage());
                    e.printStackTrace();
                    request.setAttribute("msg", "Erreur technique lors de la modification");
                }
                
                try {
                    element = switch(categorie) {
                        case "patrimoine" -> patrmoineService.getById(id);
                        case "restauration" -> restauarationService.getById(id);
                        case "gastronomie" -> gastronomieService.getById(id);
                        case "vetement" -> vetmentService.getById(id);
                        default -> null;
                    };
                    
                    if(element != null) {
                        request.setAttribute("element", element);
                    }
                } catch(Exception e) {
                    System.out.println("Erreur lors de la récupération - ID: " + id);
                    e.printStackTrace();
                }
                
                request.getRequestDispatcher("modifierCategorie.jsp").forward(request, response);
            }else if(btn != null && btn.equals("rechercherPourSuppression")) {
                String searchTerm = request.getParameter("searchTerm");  
                System.out.println("Recherche de: " + searchTerm);
                
                try {
                    // Recherche dans toutes les catégories
                    List<Object> results = new ArrayList<>();
                    results.addAll(patrmoineService.searchPatrimoines(searchTerm));
                    results.addAll(restauarationService.searchRestaurations(searchTerm));
                    results.addAll(gastronomieService.searchGastronomies(searchTerm));
                    results.addAll(vetmentService.searchVetements(searchTerm));
                    
                    if(!results.isEmpty()) {
                        Object element = results.get(0);
                        String elementType = element.getClass().getSimpleName(); 
                        // Stocker l'élément et son type
                        request.setAttribute("element", element);
                        request.setAttribute("elementType", element.getClass().getSimpleName());
                    } else {
                        request.setAttribute("msg", "Aucun élément trouvé avec: '" + searchTerm + "'");
                        request.setAttribute("msgType", "error");
                    }
                    
                    request.getRequestDispatcher("supprimerCategorie.jsp").forward(request, response);
                    
                } catch(Exception e) {
                    e.printStackTrace();
                    request.setAttribute("msg", "Erreur recherche: " + e.getMessage());
                    request.setAttribute("msgType", "error");
                    request.getRequestDispatcher("supprimerCategorie.jsp").forward(request, response);
                }
            }
            else if(btn != null && btn.equals("supprimer")) {
            	 try {
            		 int id = Integer.parseInt(request.getParameter("id"));
                     String type = request.getParameter("type"); 
            	     System.out.println("Suppression - ID: " + id + ", Type: " + type);
            	     if(type == null || type.isEmpty()) {
            	            throw new IllegalArgumentException("Type d'élément non spécifié");
            	     }

               
                    boolean success = false;
                    
                    switch(type.toLowerCase()) {
                    case "patrimoine":
                        success = patrmoineService.deletePatrimoineById(id);
                        break;
                    case "restauration":
                        success = restauarationService.deleteRestaurationById(id);
                        break;
                    case "gastronomie":
                        success = gastronomieService.deleteGastronomieById(id);
                        break;
                    case "vetement":
                        success = vetmentService.deleteVetementById(id);
                        break;
                    default:
                        throw new IllegalArgumentException("Type inconnu: " + type);
                    }
                    
                    if(success) {
                        request.setAttribute("msg", "Élément supprimé avec succès !");
                        request.setAttribute("msgType", "success");
                        request.getRequestDispatcher("supprimerCategorie.jsp").forward(request, response);
                    } else {
                        request.setAttribute("msg", "Échec de la suppression");
                        request.setAttribute("msgType", "error");
                        request.getRequestDispatcher("supprimerCategorie.jsp").forward(request, response);
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                    request.setAttribute("msg", "Erreur: " + e.getMessage());
                    request.getRequestDispatcher("supprimerCategorie.jsp").forward(request, response);
                }
            }else if (btn != null && btn.equals("ajouterarticle")) {
                String titre = request.getParameter("titre");
                String description = request.getParameter("description");
                String imageurl = request.getParameter("imageurl");
                String dateParam = request.getParameter("datepub");
                
                try {
                    // Conversion de la date dans un bloc séparé
                    java.util.Date datepub;
                    if (dateParam != null && !dateParam.isEmpty()) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						datepub = sdf.parse(dateParam);
                    } else {
                        datepub = new java.util.Date(); // Date courante par défaut
                    }

                    // Appel du service
                    boolean result = articleService.ajouterArticle(titre, description, datepub, imageurl);
                    
                    if (result) {
                        request.setAttribute("message", "success:Article ajouté avec succès!");
                        request.setAttribute("resetForm", "true");

                    } else {
                        request.setAttribute("message", "error:Échec de l'ajout de l'article");
                    }
                } catch (Exception e) {
                    request.setAttribute("message", "error:Erreur technique: " + e.getMessage());
                    e.printStackTrace();
                }
                
                request.getRequestDispatcher("article.jsp").forward(request, response);     
        }else if(btn != null && btn.equals("rechercherarticle")) {
            try {
                String searchTerm = request.getParameter("searchTerm");

                if(searchTerm == null || searchTerm.trim().isEmpty()) {
                    throw new Exception("Veuillez entrer un terme de recherche");
                }

                Article element = null;

                try {
                    int id = Integer.parseInt(searchTerm);
                    element = articleService.getArticleById(id);
                } catch (NumberFormatException e) {
                    List<Article> results = articleService.searchArticles(searchTerm);
                    if (!results.isEmpty()) {
                        element = results.get(0); 
                    }
                }

                if(element != null) {
                    request.setAttribute("element", element);
                } else {
                    throw new Exception("Aucun article trouvé avec ce critère");
                }

                request.getRequestDispatcher("modifier.jsp").forward(request, response);

            } catch(Exception e) {
                request.setAttribute("msg", e.getMessage());
                request.setAttribute("msgType", "error");
                request.getRequestDispatcher("modifier.jsp").forward(request, response);
            }

        } else if(btn != null && btn.equals("modifier")) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                String nom = request.getParameter("nom");
                String description = request.getParameter("description");
                String imageurl = request.getParameter("imageurl");
                Date datepub = Date.valueOf(request.getParameter("datepub")); // Assure-toi que le format est correct (yyyy-MM-dd)

                boolean success = articleService.update(id, nom, description, imageurl, datepub);

                if(success) {
                    request.setAttribute("msg", "Article modifié avec succès !");
                    request.setAttribute("msgType", "success");
                } else {
                    request.setAttribute("msg", "Erreur lors de la modification");
                    request.setAttribute("msgType", "error");
                }

                Article element = articleService.getById(id);
                request.setAttribute("element", element);
                request.getRequestDispatcher("modifier.jsp").forward(request, response);

            } catch(Exception e) {
                request.setAttribute("msg", "Erreur technique lors de la modification");
                request.setAttribute("msgType", "error");
                request.getRequestDispatcher("modifier.jsp").forward(request, response);
            }
        }

        else if (btn != null && btn.equals("supprimer")) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                String titre = request.getParameter("titre"); 
                System.out.println("Suppression - ID: " + id + ", Titre: " + titre);

                if (titre == null || titre.isEmpty()) {
                    throw new IllegalArgumentException("Titre de l'article non spécifié");
                }

                boolean success = articleService.deleteArticleById(id); // Suppression de l'article

                if (success) {
                    request.setAttribute("msg", "Article supprimé avec succès !");
                    request.setAttribute("msgType", "success");
                } else {
                    request.setAttribute("msg", "Échec de la suppression");
                    request.setAttribute("msgType", "error");
                }

                request.getRequestDispatcher("supprimer.jsp").forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("msg", "Erreur: " + e.getMessage());
                request.setAttribute("msgType", "error");
                request.getRequestDispatcher("supprimerCategorie.jsp").forward(request, response);
            }
        }
            else if(page != null) {
                switch(page) {
                    case "patrimoine":
                        request.setAttribute("listPatrimoines", patrmoineService.getAllPatrimoines());
                        request.getRequestDispatcher("patrimoine.jsp").forward(request, response);
                        break;
                        
                    case "restauration":
                        request.setAttribute("listRestaurations", restauarationService.getAllRestaurations());
                        request.getRequestDispatcher("restauration.jsp").forward(request, response);
                        break;
                        
                    case "gastronomie":
                        request.setAttribute("listGastronomies", gastronomieService.getAllGastronomie());
                        request.getRequestDispatcher("gastronomie.jsp").forward(request, response);
                        break;
                        
                    case "vetement":
                        request.setAttribute("listVetements", vetmentService.getAllVetements());
                        request.getRequestDispatcher("vetement.jsp").forward(request, response);
                        break;
                    case "histoire":
                        System.out.println("Chargement des articles..."); // Log simplifié
                    request.setAttribute("listArticles", articleService.getAllArticles());
                    request.getRequestDispatcher("article.jsp").forward(request, response);
                    break;
                    default:
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            } else {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Erreur: " + e.getMessage());
            request.getRequestDispatcher("erreur.jsp").forward(request, response);}
        
    }
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	
}}
	