package swimple.services;

import swimple.models.Team;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class TeamService {

    @PersistenceContext
    EntityManager em;

    public List<Team> getAll() {
        return em.createQuery("select t from Team t", Team.class).getResultList();
    }
}
