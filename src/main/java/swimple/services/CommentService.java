package swimple.services;

import swimple.models.Comment;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class CommentService {

    @PersistenceContext
    EntityManager em;

    public List<Comment> getForTraining(int id) {
        Query query = em.createQuery("select c from Comment c where c.training.id = :training_id", Comment.class);
        query.setParameter("training_id", id);
        return query.getResultList();
    }

    public Comment create(Comment comment) {
        em.persist(comment);
        return comment;
    }
}
