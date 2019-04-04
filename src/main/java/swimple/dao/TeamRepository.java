package swimple.dao;

import swimple.models.Team;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

@Stateless
public class TeamRepository extends CrudRepository<Team> {

    @PostConstruct
    public void initialize() {
        setEntityClass(Team.class);
    }

}
