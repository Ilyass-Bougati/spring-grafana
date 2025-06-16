package skybooker.akira.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import skybooker.akira.entity.Email;
import skybooker.akira.repository.EmailRepository;

import java.util.List;

@Service
public class EmailService {

    Logger logger = LoggerFactory.getLogger(EmailService.class);
    private final EmailRepository emailRepository;

    public EmailService(EmailRepository userRepository) {
        this.emailRepository = userRepository;
    }

    public List<Email> getUser(Long id) {
        return emailRepository.findByUserId(id);
    }

    public void createEmail(Long id, String content) {
        Email user = new Email();
        user.setUserId(id);
        user.setContent(content);
        emailRepository.save(user);
    }
}
