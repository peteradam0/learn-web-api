package learn.web.api.facade;

import learn.web.api.facade.dto.OrganizationData;
import learn.web.api.facade.dto.OrganizationMemberData;
import learn.web.api.facade.dto.OrganizationMemberInvData;
import learn.web.api.facade.dto.UserData;

import java.util.List;

public interface OrganizationFacade {
    OrganizationData createOrganization(OrganizationData organizationData);

    OrganizationData getOrganizationByName(String name);

    List<OrganizationData> getOrganizations();

    void deleteOrganization(String name);

    OrganizationMemberInvData addMemberToOrganization(OrganizationMemberData organizationMemberData);

    void confirmInvitation(String id);
}
