package swimple.services;

import swimple.models.Role;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class RoleService {

    @PersistenceContext
    EntityManager em;

    public void create(Role role) {
        em.persist(role);
    }
}
