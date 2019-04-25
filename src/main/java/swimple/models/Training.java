package swimple.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.json.bind.annotation.JsonbNillable;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "trainings")
@JsonbNillable
public class Training extends ApplicationRecord {
    private Date startDate;

    @NotNull
    private Date endDate;

    @NotNull
    private String description;

    @NotNull
    private String location;

    @Column(columnDefinition="TEXT")
    private String workout;

    @JsonbTransient
    @OneToMany(targetEntity = Comment.class, fetch = FetchType.LAZY, mappedBy = "training")
    private List<Comment> comments = new ArrayList<>();


    public Training() {
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date date) {
        this.startDate = date;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWorkout() {
        return workout;
    }

    public void setWorkout(String workout) {
        this.workout = workout;
    }

    @JsonIgnore
    public List<Comment> getComments() {
        return comments;
    }
}
