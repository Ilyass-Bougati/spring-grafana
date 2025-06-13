package skybooker.akira.controller;

import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import skybooker.akira.entity.User;
import skybooker.akira.service.UserService;

@RestController
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Observed(
            name = "user.get",
            contextualName = "getting-user-controller"
    )
    @GetMapping("/{username}")
    public ResponseEntity<User> home(@PathVariable String username) {
        logger.info("Getting user: " + username);
        User user = userService.getUser(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(user);
        }
    }

    @PostMapping("/{username}/{password}")
    public ResponseEntity<Void> create(@PathVariable String username, @PathVariable String password) {
        logger.info("Creating user: " + username);
        userService.createUser(username, password);
        return ResponseEntity.ok().build();
    }
}
