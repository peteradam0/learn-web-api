package learn.web.api.facade;

import learn.web.api.facade.dto.*;

import java.util.List;

public interface OrganizationFacade {
    OrganizationData createOrganization(OrganizationData organizationData);

    OrganizationData getOrganizationByName(String name);

    List<OrganizationData> getOrganizations();

    List<OrganizationData> getOrganizationsExclude(String toExclude);

    void deleteOrganization(String name);

    OrganizationMemberInvData addMemberToOrganization(OrganizationMemberData organizationMemberData);

    void confirmInvitation(String id);

    List<OrganizationData> getOrganizationsOfMember(String memberId);

    List<UserSuggestionData> getUserSuggestionsForOrganization(String organizationName);
}
