package learn.web.api.daos;

import learn.web.api.models.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemberDao extends MongoRepository<Member, String> {

}
