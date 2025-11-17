package myModel;

import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import entities.Student;
@Stateless
public class StudentService extends Exception {
    
    @PersistenceContext(unitName="tp2jee+")
    private EntityManager em;

    public Student getStudentByLoginAndPassword(String login, String password) {
       try {
            TypedQuery<Student> query = em.createNamedQuery("Student.findByLoginAndPassword", Student.class);
            query.setParameter(1, login);
            query.setParameter(2, password);
            return query.getSingleResult() ;
       }catch(Exception e) {
    	   System.out.println(e.getMessage());
    	   return null;
       }
        }
    }

