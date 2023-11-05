package learn.web.api.services.impl;

import learn.web.api.daos.MemberDao;
import learn.web.api.models.Member;
import learn.web.api.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultMemberService implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public List<Member> getMembers() {
        return memberDao.findAll();
    }

    @Override
    public Member createMember(Member member) {
        return memberDao.save(member);
    }
}
