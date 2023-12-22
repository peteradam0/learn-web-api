package learn.web.api.dao;

import learn.web.api.model.OrganizationMemberInvitation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrganizationMemberInvitationDao extends MongoRepository<OrganizationMemberInvitation, String> {
}
