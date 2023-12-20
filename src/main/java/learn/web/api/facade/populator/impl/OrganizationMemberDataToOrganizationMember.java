package learn.web.api.facade.populator.impl;

import learn.web.api.facade.dtos.OrganizationMemberData;
import learn.web.api.facade.populator.Populator;
import learn.web.api.models.Organization;
import learn.web.api.models.OrganizationMember;
import learn.web.api.models.User;
import learn.web.api.services.OrganizationMemberService;
import learn.web.api.services.OrganizationService;
import learn.web.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMemberDataToOrganizationMember implements Populator<OrganizationMemberData, OrganizationMember> {

    @Autowired
    private OrganizationMemberService organizationMemberService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrganizationService organizationService;

    @Override
    public void populate(OrganizationMemberData source, OrganizationMember target) {

        User organizationMember = userService.getUserByEmail(source.getUserEmail());
        Organization organization = organizationService.getOrganizationByName(source.getOrganizationName());

        if (organizationMember != null && organization != null) {
            target.setOrganizationId(organization.getId());
            target.setUserId(organizationMember.getId());
        } else {
            throw new RuntimeException("Organization or user not found");
        }

    }
}
