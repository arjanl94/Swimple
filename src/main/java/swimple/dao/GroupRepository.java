package swimple.dao;

import swimple.models.Group;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

@Stateless
public class GroupRepository extends CrudRepository<Group> {

    @PostConstruct
    public void initialize() {
        setEntityClass(Group.class);
    }

}
