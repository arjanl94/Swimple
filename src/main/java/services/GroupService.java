package services;

import models.Group;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class GroupService {

    @PersistenceContext
    EntityManager em;

    public List<Group> getAll() {
        Query query = em.createQuery("select g from Group g", Group.class);
        return query.getResultList();
    }
}
