package learn.web.api.daos;

import learn.web.api.models.Member;

import java.util.List;

public interface MemberDao {

    public List<Member> getMembers();
}
