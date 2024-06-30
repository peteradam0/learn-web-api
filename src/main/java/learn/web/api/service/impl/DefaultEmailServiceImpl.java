package learn.web.api.service.impl;

import learn.web.api.controller.CourseController;
import learn.web.api.service.EmailService;
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
    public void sengOrganizationInvite(String to, String organizationName, String invitationId) {
        try {
            String content = templateEngine.process("emailtemplate", createContextForOrganizationMemberEmail(invitationId, organizationName));
            javaMailSender.send(createMessage(content, to));
        } catch (MessagingException e) {
            LOGGER.error("Message creation failed", e);
        }
    }

    @Override
    public void sendUserInvite(String email) {
        try {
            String content = templateEngine.process("usertemplate", createContextForEmail());
            javaMailSender.send(createMessage(content, email));
        } catch (MessagingException e) {
            LOGGER.error("Message creation failed", e);
        }
    }

    private Context createContextForEmail() {
        Context context = new Context();
        context.setVariable("confirmationUrl", "http://localhost:3000");
        return context;
    }


    private Context createContextForOrganizationMemberEmail(final String invitationId, final String organizationName) {
        Context context = new Context();
        context.setVariable("confirmationUrl", domainToConfirmInvitation + invitationId);
        context.setVariable("organization", organizationName);
        return context;
    }


    private MimeMessage createMessage(String text, String to) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
        mimeMessageHelper.setPriority(1);
        mimeMessageHelper.setSubject("Organization Invitation");
        mimeMessageHelper.setFrom(fromMail);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setText(text, true);
        return message;
    }
}