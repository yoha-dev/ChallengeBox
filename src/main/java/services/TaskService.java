package services;

import entities.Challenge;
import entities.Task;
import entities.Venturer;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

@RequestScoped
public class TaskService {

    @PersistenceContext
    EntityManager em;

    public Task getTask(int id) {
        return em.find(Task.class, id);
    }

    public Task createTask(Challenge challenge, int venturer_id
    ) {
        Venturer venturer = em.find(Venturer.class, venturer_id);
        Task t = new Task(challenge, venturer, new Date(), null, null, null);
        em.persist(t);
        return t;
    }

    public List<Challenge> getTasks(int venturer_id) {
        Query query = em.createNamedQuery("findAllTasks");
        query.setParameter("venturer_id", venturer_id);
        return query.getResultList();
    }

    public List<Challenge> getDoneTasks(int venturer_id) {
        Query query = em.createNamedQuery("findDoneTasks");
        query.setParameter("venturer_id", venturer_id);
        return query.getResultList();
    }

    public List<Challenge> getOpenTasks(int venturer_id) {
        Query query = em.createNamedQuery("findOpenTasks");
        query.setParameter("venturer_id", venturer_id);
        return query.getResultList();
    }

    public List<Challenge> getTerminatedTasks(int venturer_id) {
        Query query = em.createNamedQuery("findTerminatedTasks");
        query.setParameter("venturer_id", venturer_id);
        return query.getResultList();
    }
}
