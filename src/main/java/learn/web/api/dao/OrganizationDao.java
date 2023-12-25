package learn.web.api.dao;

import learn.web.api.model.Organization;
import learn.web.api.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrganizationDao extends MongoRepository<Organization, String> {
    Organization findOrganizationByName(String name);

    List<Organization> findOrganizationByMembersContains(User member);
}
