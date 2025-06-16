package skybooker.akira.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import skybooker.akira.entity.User;
import skybooker.akira.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final WebClient webClient;

    public UserService(UserRepository userRepository, WebClient webClient) {
        this.userRepository = userRepository;
        this.webClient = webClient;
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

    public void createEmail(Long id, String content) {
        webClient.post()
                .uri("http://localhost:8081/email/"+id+"/"+content);
    }
}
