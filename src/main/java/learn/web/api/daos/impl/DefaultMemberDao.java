package learn.web.api.daos.impl;

import learn.web.api.daos.MemberDao;
import learn.web.api.models.Member;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DefaultMemberDao implements MemberDao {
    @Override
    public List<Member> getMembers() {
        Member member = new Member();
        member.setUserName("John Doe");
        return Arrays.asList(member);
    }
}
