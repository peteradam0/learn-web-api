package learn.web.api.facades;

import learn.web.api.facades.dtos.MemberData;

import java.util.List;

public interface MemberFacade {
    public List<MemberData> getMemberData();

    public MemberData createMember(String name);
}
