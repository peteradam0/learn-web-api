package learn.web.api.daos;

import learn.web.api.model.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrganizationDao extends MongoRepository<Organization, String> {
    Organization findOrganizationByName(String name);
}
