package learn.web.api.service.impl;

import learn.web.api.dao.CanvasDao;
import learn.web.api.dao.OrganizationDao;
import learn.web.api.dao.UserDao;
import learn.web.api.facade.dto.CanvasUser;
import learn.web.api.model.Organization;
import learn.web.api.model.OrganizationMemberInvitation;
import learn.web.api.model.User;
import learn.web.api.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DefaultOrganizationService implements OrganizationService {

    @Autowired
    private OrganizationDao organizationDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CanvasDao canvasDao;

    @Override
    public void createOrganization(Organization organization) {
        Organization foundOrganizations = organizationDao.findOrganizationByName(organization.getName());

        if (foundOrganizations == null) {
            organizationDao.save(organization);
            return;
        }
        throw new RuntimeException("Organization already exists, create failed");
    }

    @Override
    public List<Organization> getOrganizations() {
        return organizationDao.findAll();
    }

    @Override
    public List<Organization> getOrganizationsExclude(String organizationToExclude) {
        return organizationDao.findAllActiveOrganizationsExclude(organizationToExclude);
    }

    @Override
    public void deleteOrganization(String name) {
        Organization foundOrganizations = organizationDao.findOrganizationByName(name);
        if(foundOrganizations != null){
            organizationDao.delete(foundOrganizations);
        }else{
            throw new RuntimeException("Deletion failed ");
        }
    }

    @Override
    public Organization getOrganizationByName(String name) {
        return organizationDao.findOrganizationByName(name);
    }

    @Override
    public Optional<Organization> getOrganizationById(String id) {
        return organizationDao.findById(id);
    }

    @Override
    public void addMember(OrganizationMemberInvitation updatedInvitation) {
        Optional<Organization> organization = getOrganizationById(updatedInvitation.getOrganizationId());
        Optional<User> newMember = userDao.findById(updatedInvitation.getUserId());
        if(organization.isPresent() && newMember.isPresent()){
            Organization foundOrganization = organization.get();
            if(foundOrganization.getMembers() != null){
                List<User> members = organization.get().getMembers();
                members.add(newMember.get());
                foundOrganization.setMembers(members);
            }else{
                List<User> members = new ArrayList<>();
                members.add(newMember.get());
                foundOrganization.setMembers(members);
            }
            organizationDao.save(foundOrganization);
        }else{
            throw new RuntimeException("Organization or new member not found");
        }

    }

    @Override
    public List<Organization> getOrganizationsOfMember(String memberId) {
        return null;
    }

    @Override
    public List<CanvasUser> getAllCanvasUsers() {
        return canvasDao.findAllCanvasUsers();
    }
}
