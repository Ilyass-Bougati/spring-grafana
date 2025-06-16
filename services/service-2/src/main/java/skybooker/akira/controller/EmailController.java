package skybooker.akira.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import skybooker.akira.entity.Email;
import skybooker.akira.service.CustomMetricsService;
import skybooker.akira.service.EmailService;

import java.util.List;

@RestController
@RequestMapping("/email")
public class EmailController {
    Logger logger = LoggerFactory.getLogger(EmailController.class);
    private final EmailService userService;
    private final CustomMetricsService customMetricsService;

    public EmailController(EmailService userService, CustomMetricsService customMetricsService) {
        this.userService = userService;
        this.customMetricsService = customMetricsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Email>> home(@PathVariable Long id) {
        customMetricsService.incrementCustomMetric();
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PostMapping("/{id}/{content}")
    public ResponseEntity<Void> create(@PathVariable Long id, @PathVariable String content) {
        customMetricsService.incrementCustomMetric();
        userService.createEmail(id, content);
        return ResponseEntity.ok().build();
    }
}
