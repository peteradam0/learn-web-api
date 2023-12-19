package learn.web.api.services;

public interface EmailService {
    void sengOrganizationInvite(
            String to, String subject, String text);
}
