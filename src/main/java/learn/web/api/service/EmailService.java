package learn.web.api.service;

public interface EmailService {
    void sengOrganizationInvite(
            String to, String subject, String organizationName, String organizationId);
}
