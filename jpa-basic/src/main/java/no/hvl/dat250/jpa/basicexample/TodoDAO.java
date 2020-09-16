package no.hvl.dat250.jpa.basicexample;

import org.eclipse.jetty.server.Authentication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class TodoDAO {

    private EntityManager em;

    public TodoDAO() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("todos");
        em = factory.createEntityManager();
    }

    public List<Todo> read() {
        Query q = em.createQuery("Select todo from Todo todo");
        return q.getResultList();
    }

    public Todo find(int id) {
        Todo t = em.find(Todo.class, id);
        return t;
    }

    public Todo create(Todo todo) {
        em.getTransaction().begin();
        em.persist(todo);
        em.getTransaction().commit();
        return todo;
    }

    public Todo update(Todo todo) {
        em.getTransaction().begin();
        em.merge(todo);
        em.getTransaction().commit();
        return todo;
    }

    public Todo delete(int id) {
        Todo todo = em.find(Todo.class, id);
        em.getTransaction().begin();
        em.remove(todo);
        em.getTransaction().commit();
        return todo;
    }
}
