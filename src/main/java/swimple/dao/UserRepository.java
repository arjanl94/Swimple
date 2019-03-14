package swimple.dao;

import swimple.models.User;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.Optional;

@Stateless
public class UserRepository extends CrudRepository<User> {

    @PostConstruct
    private void initialize() {
        setEntityClass(User.class);
    }

    public Optional<User> findByEmail(String email) {
        Query query = em.createQuery("select u from User u where u.email = :email", User.class);
        query.setParameter("email", email);

        return Optional.of((User) query.getSingleResult());
    }
}
