package skybooker.akira.controller;

import io.micrometer.tracing.annotation.NewSpan;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import skybooker.akira.entity.User;
import skybooker.akira.service.CustomMetricsService;
import skybooker.akira.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final CustomMetricsService customMetricsService;

    public UserController(UserService userService, CustomMetricsService customMetricsService) {
        this.userService = userService;
        this.customMetricsService = customMetricsService;
    }

    @GetMapping("/{username}")
    @WithSpan("get-user-controller")
    public ResponseEntity<User> home(@PathVariable String username) {
        logger.trace("Getting user: " + username);
        customMetricsService.incrementCustomMetric();
        User user = userService.getUser(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(user);
        }
    }

    @PostMapping("/{username}/{password}")
    @NewSpan("test-span")
    public ResponseEntity<Void> create(@PathVariable String username, @PathVariable String password) {
        logger.trace("Creating user: " + username);
        customMetricsService.incrementCustomMetric();
        userService.createUser(username, password);
        return ResponseEntity.ok().build();
    }
}
