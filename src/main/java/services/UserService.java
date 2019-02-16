package services;

import models.Training;
import models.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

@Stateless
public class UserService {

    @PersistenceContext
    EntityManager em;

    public List<User> getAll() {
        Query query = em.createQuery("select u from User u", User.class);

        return query.getResultList();
    }

    public User create(User user) {
        try {
            user.setPassword(encodeSHA256(user.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        em.persist(user);

        return user;
    }

    public User findByEmail(String email) {
        Query query = em.createQuery("select u from User u where u.email = :email", User.class);
        query.setParameter("email", email);

        try {
            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public static String encodeSHA256(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hash);
    }
}
