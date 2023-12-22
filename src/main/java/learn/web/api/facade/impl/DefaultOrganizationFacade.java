package learn.web.api.facade.impl;

import learn.web.api.facade.OrganizationFacade;
import learn.web.api.facade.dto.OrganizationData;
import learn.web.api.facade.dto.OrganizationMemberData;
import learn.web.api.facade.dto.OrganizationMemberInvData;
import learn.web.api.facade.populator.impl.OrganizationDataToOrganizationPopulator;
import learn.web.api.facade.populator.impl.OrganizationInvToOrganizationInvDataPopulator;
import learn.web.api.facade.populator.impl.OrganizationMemberDataToOrganizationMember;
import learn.web.api.facade.populator.impl.OrganizationToOrganizationDataPopulator;
import learn.web.api.model.Organization;
import learn.web.api.model.OrganizationMemberInvitation;
import learn.web.api.service.OrganizationMemberService;
import learn.web.api.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
        organizationInvToOrganizationInvDataPopulator.populate(createdInvitation,organizationMemberDataResult);
        return organizationMemberDataResult;
    }

}
