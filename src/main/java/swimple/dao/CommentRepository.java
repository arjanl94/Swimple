package swimple.dao;

import swimple.models.Comment;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class CommentRepository extends CrudRepository<Comment> {

    @PostConstruct
    private void initialize() {
        setEntityClass(Comment.class);
    }

    public List<Comment> findByTrainingId(int id) {
        Query query = em.createQuery("select c from Comment c where c.training.id = :training_id", Comment.class);
        query.setParameter("training_id", id);

        return query.getResultList();
    }
}
