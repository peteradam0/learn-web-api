package learn.web.api.facade.impl;

import learn.web.api.exception.ServiceLayerException;
import learn.web.api.facade.OrganizationFacade;
import learn.web.api.facade.dto.*;
import learn.web.api.facade.populator.impl.*;
import learn.web.api.model.Organization;
import learn.web.api.model.OrganizationMemberInvitation;
import learn.web.api.service.OrganizationMemberService;
import learn.web.api.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DefaultOrganizationFacade implements OrganizationFacade {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private OrganizationMemberService organizationMemberService;

    @Autowired
    private OrganizationToOrganizationDataPopulator organizationToOrganizationDataPopulator;

    @Autowired
    private OrganizationDataToOrganizationPopulator organizationDataToOrganizationPopulator;

    @Autowired
    private OrganizationMemberDataToOrganizationMember organizationMemberDataToOrganizationMember;

    @Autowired
    private OrganizationInvToOrganizationInvDataPopulator organizationInvToOrganizationInvDataPopulator;

    @Autowired
    private CanvasUserToUserSuggestionPopulator canvasUserToUserSuggestionPopulator;

    @Override
    public OrganizationData createOrganization(OrganizationData organizationData) {
        Organization organization = new Organization();
        organizationDataToOrganizationPopulator.populate(organizationData, organization);
        organizationService.createOrganization(organization);
        return organizationData;
    }

    @Override
    public OrganizationData getOrganizationByName(String name) {
        Organization organization = organizationService.getOrganizationByName(name);
        OrganizationData organizationData = new OrganizationData();
        organizationToOrganizationDataPopulator.populate(organization, organizationData);
        return organizationData;
    }

    @Override
    public List<OrganizationData> getOrganizations() {

        List<OrganizationData> organizationDataList = new ArrayList<>();
        for (Organization organization : organizationService.getOrganizations()) {
            OrganizationData organizationData = new OrganizationData();
            organizationToOrganizationDataPopulator.populate(organization, organizationData);
            organizationDataList.add(organizationData);
        }

        return organizationDataList;
    }

    @Override
    public void deleteOrganization(String name) {
        organizationService.deleteOrganization(name);
    }

    @Override
    public OrganizationMemberInvData addMemberToOrganization(OrganizationMemberData organizationMemberData) {
        OrganizationMemberInvitation organizationMemberInvitation = new OrganizationMemberInvitation();
        organizationMemberDataToOrganizationMember.populate(organizationMemberData, organizationMemberInvitation);
        OrganizationMemberInvitation createdInvitation = organizationMemberService.createOrganizationMember(organizationMemberInvitation);
        OrganizationMemberInvData organizationMemberDataResult = new OrganizationMemberInvData();
        organizationInvToOrganizationInvDataPopulator.populate(createdInvitation, organizationMemberDataResult);
        return organizationMemberDataResult;
    }

    @Override
    public void confirmInvitation(String id) {
        Optional<OrganizationMemberInvitation> memberInvitation = organizationMemberService.getMemberInvitationById(id);

        if (memberInvitation.isPresent()) {
            OrganizationMemberInvitation updatedInvitation = memberInvitation.get();
            updatedInvitation.setConfirmed(true);
            organizationMemberService.updateOrganizationMember(updatedInvitation);
            organizationService.addMember(updatedInvitation);

        } else {
            throw new RuntimeException("Member invitation not found");
        }
    }

    @Override
    public List<OrganizationData> getOrganizationsOfMember(String memberId) {
        List<Organization> organizations = organizationService.getOrganizationsOfMember(memberId);
        if (organizations != null) {
            List<OrganizationData> organizationDataList = new ArrayList<>();
            for (Organization organization : organizations) {
                OrganizationData organizationData = new OrganizationData();
                organizationToOrganizationDataPopulator.populate(organization, organizationData);
                organizationDataList.add(organizationData);
            }
            return organizationDataList;
        } else {
            throw new ServiceLayerException("This user has no organizations");
        }
    }

    @Override
    public List<UserSuggestionData> getUserSuggestionsForOrganization(String organizationName) {
        List<CanvasUser> canvasUserList = organizationService.getAllCanvasUsers();

        List<UserSuggestionData> userSuggestionDataList = new ArrayList<>();
        for(CanvasUser canvasUser: organizationService.getAllCanvasUsers()){
            UserSuggestionData userSuggestionData = new UserSuggestionData();
            canvasUserToUserSuggestionPopulator.populate(canvasUser,userSuggestionData);
            userSuggestionDataList.add(userSuggestionData);
        }
        return userSuggestionDataList;
    }

}
