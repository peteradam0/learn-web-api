package learn.web.api.facade.populator.impl;

import learn.web.api.facade.dto.OrganizationData;
import learn.web.api.facade.dto.UserData;
import learn.web.api.facade.populator.Populator;
import learn.web.api.model.Organization;
import learn.web.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrganizationToOrganizationDataPopulator implements Populator<Organization, OrganizationData> {

    @Autowired
    private UserToUserDataPopulator userToUserDataPopulator;

    @Override
    public void populate(Organization source, OrganizationData target) {
        target.setName(source.getName());
        target.setImageUrl(source.getImageUrl());
        target.setId(source.getId());
        if(source.getMembers() != null){
            populateMembers(source, target);
        }
    }

    private void populateMembers(Organization source, OrganizationData target) {
        List<UserData> userDataList = new ArrayList<>();
        for(User user : source.getMembers()){
            UserData userData = new UserData();
            userToUserDataPopulator.populate(user,userData);
            userDataList.add(userData);
        }
        target.setMembers(userDataList);
    }
}
