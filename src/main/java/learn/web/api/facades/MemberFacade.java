package learn.web.api.facades;

import learn.web.api.facades.dtos.MemberData;

import java.util.List;

public interface MemberFacade {
    List<MemberData> getMemberData();

    MemberData createMember(String name);
}
