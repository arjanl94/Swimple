package swimple.dao;

import swimple.models.Training;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;

@Stateless
public class TrainingRepository extends CrudRepository<Training> {

    @PostConstruct
    private void initialize() {
        setEntityClass(Training.class);
    }
}
