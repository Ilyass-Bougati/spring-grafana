package skybooker.akira.service;

import io.opentelemetry.instrumentation.annotations.WithSpan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import skybooker.akira.entity.User;
import skybooker.akira.repository.UserRepository;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @WithSpan("get-user")
    public User getUser(String username) {
        logger.info("Getting user: " + username);
        return userRepository.findByUsername(username)
                .orElse(null);
    }

    @WithSpan("create-user")
    public void createUser(String username, String password) {
        User user = new User();
        logger.info("Creating user: " + username);
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);
    }
}
