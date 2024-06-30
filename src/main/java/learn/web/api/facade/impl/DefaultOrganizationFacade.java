package learn.web.api.facade.impl;

import learn.web.api.exception.ServiceLayerException;
import learn.web.api.facade.OrganizationFacade;
import learn.web.api.facade.dto.*;
import learn.web.api.facade.populator.impl.*;
import learn.web.api.model.Organization;
import learn.web.api.model.OrganizationMemberInvitation;
import learn.web.api.model.User;
import learn.web.api.service.OrganizationMemberService;
import learn.web.api.service.OrganizationService;
import learn.web.api.service.SessionService;
import learn.web.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class DefaultOrganizationFacade implements OrganizationFacade {

    @Autowired
    private SessionService sessionService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private UserService userService;

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
        User user = userService.getUserByClerkId(sessionService.getCurrentUserId());
        populateAdminMember(organization, user);
        organizationService.createOrganization(organization);
        return organizationData;
    }

    private void populateAdminMember(final Organization organization, final User user) {
        List<User> members = Collections.singletonList(user);
        organization.setMembers(members);
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
        return createOrganizationData(organizationService.getOrganizations());
    }

    private List<OrganizationData> createOrganizationData(List<Organization> organizationList) {

        List<OrganizationData> organizationDataList = new ArrayList<>();

        for (Organization organization : organizationList) {
            OrganizationData organizationData = new OrganizationData();
            organizationToOrganizationDataPopulator.populate(organization, organizationData);
            organizationDataList.add(organizationData);
        }

        return organizationDataList;
    }

    @Override
    public List<OrganizationData> getOrganizationsExclude(String toExclude) {
        return createOrganizationData(organizationService.getOrganizationsExclude(toExclude));
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
    public List<UserSuggestionData> getUserSuggestionsForOrganization(String organizationName, String canvasDomain) {
        List<CanvasUser> canvasUserList = organizationService.getAllCanvasUsers(canvasDomain);
        List<User> organizationMembers = organizationService.getOrganizationByName(organizationName).getMembers();

        if (canvasUserList == null || organizationMembers == null) {
            throw new ServiceLayerException("No suggestions were found or this organization has no users");
        }

        List<String> namesList = organizationMembers.stream()
                .map(User::getEmail)
                .toList();

        return createSuggestions(namesList, canvasUserList);
    }

    @Override
    public List<UserSuggestionData> getUserSuggestions(String canvasDomain) {
        List<CanvasUser> canvasUserList = organizationService.getAllCanvasUsers(canvasDomain);
        List<User> userData = userService.getUsers();
        List<String> namesList = userData.stream()
                .map(User::getEmail)
                .toList();

        return createSuggestions(namesList, canvasUserList);
    }

    public List<UserSuggestionData> createSuggestions(final List<String> namesList, List<CanvasUser> canvasUserList) {
        List<UserSuggestionData> userSuggestionDataList = new ArrayList<>();
        for (CanvasUser canvasUser : canvasUserList) {
            filterOutAlreadyAddedUsers(namesList, canvasUser, userSuggestionDataList);
        }
        return userSuggestionDataList;
    }

    private void filterOutAlreadyAddedUsers(List<String> namesList, CanvasUser canvasUser, List<UserSuggestionData> userSuggestionDataList) {
        if (!namesList.contains(canvasUser.getLogin_id())) {
            UserSuggestionData userSuggestionData = new UserSuggestionData();
            canvasUserToUserSuggestionPopulator.populate(canvasUser, userSuggestionData);
            userSuggestionDataList.add(userSuggestionData);
        }
    }

}
