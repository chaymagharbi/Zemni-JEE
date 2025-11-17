package mvcController;

import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import myModel.StudentService;
import myModel.SubjectService;
import entities.Student;
import entities.Subject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private SubjectService subjectService;
	@EJB
	private StudentService studentService;


	public Controller() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    String btn = request.getParameter("myBtn");
	    

	    if (btn != null && btn.equals("Rechercher Matieres")) {
	      
	    String intitule=request.getParameter("intitule");
	    String affiliation=request.getParameter("affiliation");
	    System.out.println("Recherche avec intitulé: " + intitule + ", affiliation: " + affiliation);
        List<Subject> subjects =subjectService.getAllSubjectsByTitleAndAffiliation(intitule,affiliation); 
        if (subjects != null && !subjects.isEmpty()) {
            // Debug: Afficher le nombre de résultats
            System.out.println("Nombre de matières trouvées: " + subjects.size());
            
            // Ajouter la liste des matières à la requête
            request.setAttribute("listSubjects", subjects);
            
            // Redirection vers la page JSP
            RequestDispatcher rd = request.getRequestDispatcher("rechercheSubject.jsp");
            rd.forward(request, response);
        } else {
            // Debug: Aucun résultat trouvé
            System.out.println("Aucun résultat trouvé");
            
            request.setAttribute("error", "Aucune résultat ne correspond au critère de recherche");
            RequestDispatcher rd = request.getRequestDispatcher("rechercheSubject.jsp");
            rd.forward(request, response);
        }}
	    else{
        request.setAttribute("error", "Aucune résultat ne correspond au critère de recherche");
        RequestDispatcher rd = request.getRequestDispatcher("rechercheSubject.jsp");
        rd.forward(request, response);
	    }


	}
	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    String btn = request.getParameter("myBtn");
	    

	    if (btn != null && btn.equals("Login")) {
	        String login = request.getParameter("login");
	        String password = request.getParameter("password");
	        Student student = studentService.getStudentByLoginAndPassword(login, password);
	        if (student != null) {
	            HttpSession session = request.getSession(true);
	            session.setMaxInactiveInterval(300);
	            session.setAttribute("activeUser", student);
	            response.sendRedirect("rechercheSubject.jsp");
	            response.getWriter().append("Bienvenue" + ((Student)session.getAttribute("activeUser")).getFirstName()+
	            		" "+((Student)session.getAttribute("activeUser")).getName());
	            
	        } else {
	        	System.out.println("Login failed - Invalid credentials or student not found");
	        	request.setAttribute("error", "parametres invalides");
	            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
	            rd.forward(request, response);
	            
	        }

	    }
	}
	
	

}
