package learn.web.api.facade.populator.impl;

import learn.web.api.facade.dto.OrganizationMemberInvData;
import learn.web.api.facade.populator.Populator;
import learn.web.api.model.OrganizationMemberInvitation;
import org.springframework.stereotype.Component;

@Component
public class OrganizationInvToOrganizationInvDataPopulator implements Populator<OrganizationMemberInvitation, OrganizationMemberInvData> {
    @Override
    public void populate(OrganizationMemberInvitation source, OrganizationMemberInvData target) {
        target.setOrganizationId(source.getOrganizationId());
        target.setId(source.getId());
        target.setConfirmed(source.isConfirmed());
        target.setUserId(source.getUserId());

    }
}
