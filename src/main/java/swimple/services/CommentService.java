package swimple.services;

import swimple.controllers.CommentUpdatesController;
import swimple.dao.CommentRepository;
import swimple.models.Comment;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class CommentService {

    @Inject
    CommentRepository commentRepository;

    @Inject
    CommentUpdatesController updatesController;

    public List<Comment> getForTraining(int id) {
        return commentRepository.findByTrainingId(id);
    }

    public Comment create(Comment comment) {
        commentRepository.save(comment);
        sendMessages(comment);
        return comment;
    }

    private void sendMessages(Comment comment) {
        updatesController.sendMessage(comment.getTraining().getId(), "Created a comment");
    }

}
