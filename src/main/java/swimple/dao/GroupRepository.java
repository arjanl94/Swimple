package swimple.dao;

import swimple.models.Group;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;

@Stateless
public class GroupRepository extends CrudRepository<Group> {

    @PostConstruct
    public void initialize() {
        setEntityClass(Group.class);
    }

}
