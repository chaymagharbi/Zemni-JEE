package myModel;

import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

import entities.Subject;
import entities.Teacher;


/**
 * Session Bean implementation class SubjectService
 */
@Stateless
@LocalBean

public class SubjectService {
    
    @PersistenceContext(unitName = "tp2jee+")
    private EntityManager em;
    
    public SubjectService() {
    }

    public List<Subject> getAllSubjects() {
    	List<Subject> subjects = new ArrayList<Subject>();
    	TypedQuery<Subject> query =em.createNamedQuery("Subject.findAll",Subject.class);
    	subjects = query.getResultList();
    	return subjects;
    }

    public List<Subject> deleteSubjectById(int subjectId) {
    	List<Subject> subjects = new ArrayList<Subject>();Subject sub = em.find(Subject.class, subjectId);if(sub!=null)
    		em.remove(sub);
    		TypedQuery<Subject> query =em.createNamedQuery("Subject.findAll",Subject.class);
    		subjects = query.getResultList();
    		return subjects;
    }

    public  List<Subject> getAllSubjectsByTitleAndAffiliation(String subjectTitle, String teacherAffiliation) {
    	List<Subject> subjects = new ArrayList<Subject>();
    	TypedQuery<Subject> query =em.createNamedQuery("Subject.findAllByTitleAndAffiliation",Subject.class);
        query.setParameter(1, subjectTitle);
        query.setParameter(2, teacherAffiliation);
        subjects = query.getResultList();
        return subjects;
}
   

    public void addSubject(String titre, int teacherId) {
        Teacher teacher = em.find(Teacher.class, teacherId);
        if (teacher != null) {
            Subject subject = new Subject();
            subject.setSubjectTitle(titre); 
            subject.setTeacher(teacher);
            em.persist(subject); 
        }
    }


    
   
}
