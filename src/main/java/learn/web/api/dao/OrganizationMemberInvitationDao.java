package learn.web.api.dao;

import learn.web.api.model.OrganizationMemberInvitation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OrganizationMemberInvitationDao extends MongoRepository<OrganizationMemberInvitation, String> {
    Optional<OrganizationMemberInvitation> findById(String id);
}
