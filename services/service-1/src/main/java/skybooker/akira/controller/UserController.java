package skybooker.akira.controller;

import io.micrometer.tracing.annotation.NewSpan;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import skybooker.akira.entity.User;
import skybooker.akira.service.CustomMetricsService;
import skybooker.akira.service.UserService;

import java.util.List;

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
    public ResponseEntity<List<User>> home(@PathVariable String username) {
        customMetricsService.incrementCustomMetric();
        return ResponseEntity.ok(userService.getUser(username));
    }

    @PostMapping("/{username}/{password}")
    public ResponseEntity<Void> create(@PathVariable String username, @PathVariable String password) {
        customMetricsService.incrementCustomMetric();
        userService.createUser(username, password);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/email/{id}/{content}")
    public ResponseEntity<Void> create(@PathVariable Long id, @PathVariable String content) {
        customMetricsService.incrementCustomMetric();
        logger.info("Email created");
        userService.createEmail(id, content);
        return ResponseEntity.ok().build();
    }
}
