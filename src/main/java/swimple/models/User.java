package swimple.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends ApplicationRecord {

    @NotBlank
    private String name;

    @NotBlank
    @Column(unique = true)
    @Email
    private String email;

//    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(columnDefinition="TEXT")
    private String oauthToken;

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_groups",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "group_id") }
    )
    private List<Group> groups = new ArrayList<>();

//    @ManyToMany(mappedBy = "users", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
//    private Set<Role> roles = new HashSet<>();
//    @Column(name = "is_admin")
    private boolean isAdmin;

//    @Column(name = "is_coach")
    private boolean isCoach;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    public User() {
    }

    public User(@NotBlank String name, @NotBlank @Email String email, @NotNull String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

//    public Set<Role> getRoles() {
//        return roles;
//   ƒ }
//
//    public void setRoles(Set<Role> roles) {
//        this.roles = roles;
//    }
//
//    public void setRole(Role role) {
//        role.getUsers().add(this);
//        this.roles.add(role);
//    }


    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isCoach() {
        return isCoach;
    }

    public void setCoach(boolean coach) {
        isCoach = coach;
    }

    public String[] getRoles() {
        List<String> roles = new ArrayList<>();
        roles.add("user");

        if(isAdmin()) {
            roles.add("admin");
        }

        if(isCoach()) {
            roles.add("coach");
        }

        return roles.toArray(new String[0]);
    }

    @JsonIgnore
    @JsonbTransient
    public List<Comment> getComments() {
        return comments;
    }

    @JsonIgnore
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getOAuthToken() {
        return oauthToken;
    }

    public void setOAuthToken(String oauthToken) {
        this.oauthToken = oauthToken;
    }

}
