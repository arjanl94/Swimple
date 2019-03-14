package swimple.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
public class Group extends ApplicationRecord {
    @NotBlank
    private String name;

    @ManyToMany(mappedBy = "groups")
    @JsonIgnore
    private List<User> users = new ArrayList<>();

    public Group() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
        user.getGroups().add(this);
    }
}
