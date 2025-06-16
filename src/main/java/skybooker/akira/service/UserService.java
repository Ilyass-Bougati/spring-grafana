package skybooker.akira.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import skybooker.akira.entity.User;
import skybooker.akira.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUser(String username) {
        logger.trace("Getting user: " + username);
        return userRepository.findByUsername(username);
    }

    public void createUser(String username, String password) {
        User user = new User();
        logger.trace("Creating user: " + username);
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);
    }
}
