package swimple.services;

import swimple.dao.UserRepository;
import swimple.models.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Stateless
public class UserService {

    @Inject
    UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User create(User user) {
        try {
            user.setPassword(encodeSHA256(user.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        userRepository.save(user);
        return user;
    }

    public User createFromOAuth(User user) {
        userRepository.save(user);
        return user;
    }

    public User findByEmail(String email) {
        Optional<User> result = userRepository.findByEmail(email);
        return result.orElse(null);
    }

    public static String encodeSHA256(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hash);
    }
}
