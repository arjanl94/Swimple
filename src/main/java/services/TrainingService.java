package services;

import models.Training;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class TrainingService {

    @PersistenceContext
    private EntityManager em;

    public List<Training> getAll() {
        Query query = em.createQuery("select t from Training t", Training.class);
        return query.getResultList();
    }
}
