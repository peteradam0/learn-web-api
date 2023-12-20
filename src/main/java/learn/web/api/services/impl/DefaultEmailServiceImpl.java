package learn.web.api.services.impl;

import learn.web.api.controller.CourseController;
import learn.web.api.services.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class DefaultEmailServiceImpl implements EmailService {

    private final Logger LOGGER = LoggerFactory.getLogger(CourseController.class);

    public static final String UTF_8_ENCODING = "UTF-8";

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private final TemplateEngine templateEngine = new TemplateEngine();

    @Value("${spring.mail.username}")
    private String fromMail;

    @Value("${spring.invite.confirm}")
    private String domainToConfirmInvitation;

    @Override
    public void sengOrganizationInvite(String to, String subject) {

        try {
            String content = templateEngine.process("emailtemplate", createContextForOrganizationMemberEmail("10"));
            javaMailSender.send(createMessage(subject, content, to));
        } catch (MessagingException e) {
            LOGGER.error("Message creation failed", e);
        }

    }

    private Context createContextForOrganizationMemberEmail(String organizationId) {
        Context context = new Context();
        context.setVariable("canvasLink", domainToConfirmInvitation + organizationId);
        context.setVariable("organization.name", "The office");
        return context;
    }


    private MimeMessage createMessage(String subject, String text, String to) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
        mimeMessageHelper.setPriority(1);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setFrom(fromMail);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setText(text, true);
        return message;
    }
}