package learn.web.api.facades.populators.impl;

import learn.web.api.facades.populators.Populator;
import org.springframework.stereotype.Component;

@Component
public class MemberToMemberDataPopulator implements Populator<Member, MemberData> {

    @Override
    public void populate(Member source, MemberData target) {
        target.setUserName(source.getUserName());
    }
}
