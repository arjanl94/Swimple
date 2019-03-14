package swimple.models;


import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment extends ApplicationRecord {

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Training.class)
    @JoinColumn(name = "training_id", nullable = false)
    private Training training;

    private String body;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
