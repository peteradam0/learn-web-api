package learn.web.api.service;

public interface EmailService {
    void sengOrganizationInvite(
            String to, String organizationName, String invitationId);

    void sendUserInvite(String email);
}
