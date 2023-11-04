package learn.web.api.facades.populators.impl;

import learn.web.api.facades.dtos.MemberData;
import learn.web.api.facades.populators.Populator;
import learn.web.api.models.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberToMemberDataPopulator implements Populator<Member, MemberData> {

    @Override
    public void populate(Member source, MemberData target) {
        target.setUserName(source.getUserName());
    }
}
