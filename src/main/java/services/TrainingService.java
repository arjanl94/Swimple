package services;

import models.Training;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class TrainingService {

    @PersistenceContext
    private EntityManager em;

    public List<Training> getAll() {
        Query query = em.createQuery("select t from Training t order by t.startDate asc ", Training.class);
        return query.getResultList();
    }

    public Training get(String id) {
        Query query = em.createQuery("select t from Training t where t.id = :id", Training.class);
        query.setParameter("id", Integer.parseInt(id));
        return (Training) query.getSingleResult();
    }

    public Training create(Training training) {
        em.persist(training);
        return training;
    }

    public Training update(Training training) {
        return em.merge(training);
    }
}
