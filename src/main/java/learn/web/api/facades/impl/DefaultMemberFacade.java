package learn.web.api.facades.impl;

import learn.web.api.facades.MemberFacade;
import learn.web.api.facades.dtos.MemberData;
import learn.web.api.facades.populators.impl.MemberToMemberDataPopulator;
import learn.web.api.models.Member;
import learn.web.api.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultMemberFacade implements MemberFacade {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberToMemberDataPopulator memberToMemberDataPopulator;

    @Override
    public List<MemberData> getMemberData() {
        List<MemberData> memberDataListData = new ArrayList<>();
        for (Member memberModel: memberService.getMembers()){
            MemberData memberData = new MemberData();
            memberToMemberDataPopulator.populate(memberModel, memberData);
            memberDataListData.add(memberData);
        }

        return memberDataListData;
    }
}
