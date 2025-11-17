package myModel;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

import entities.Subject;
import entities.Teacher;

@Stateless
public class TeacherService {

    @PersistenceContext(unitName = "tp2jee+")
    private EntityManager em;

    public List<Teacher> getAllTeachers() {
    	List<Teacher> teachers = new ArrayList<Teacher>();
        TypedQuery<Teacher> query = em.createQuery("Teacher.findAll", Teacher.class);
        teachers=query.getResultList();
        return teachers;
    }

    public Teacher getTeacherById(int id) {
        return em.find(Teacher.class, id);
    }

    public List<Teacher> getTeachersBySubjectTitle(String subjectTitle) {
    	List<Teacher> teachers = new ArrayList<Teacher>();
        TypedQuery<Teacher> query = em.createQuery("Teacher.findAllBySubjectTitle", Teacher.class);
        query.setParameter("title", subjectTitle);
        teachers=query.getResultList();
        return teachers;
    }
}
