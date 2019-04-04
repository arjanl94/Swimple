package swimple.services;

import swimple.dao.CommentRepository;
import swimple.models.Comment;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class CommentService {

    @Inject
    CommentRepository commentRepository;

    public List<Comment> getForTraining(int id) {
        return commentRepository.findByTrainingId(id);
    }

    public Comment create(Comment comment) {
        commentRepository.save(comment);
        return comment;
    }

}
