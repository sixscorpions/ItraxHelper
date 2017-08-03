package com.itraxhelper.models;

import java.util.ArrayList;

/**
 * Created by Shankar on 8/3/2017.
 */

public class LoginModel extends Model {
    private String Id;
    private String Name;
    private String UserName;
    private String OrganizationId;
    private String ZoneId;
    private String BranchId;
    private String ContactEmail;
    private String ContactMobile;
    private String Photo;
    private HelperAppFeatureModel helperAppFeatureModel;
    private ArrayList<RolesModel> rolesModels;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getOrganizationId() {
        return OrganizationId;
    }

    public void setOrganizationId(String organizationId) {
        OrganizationId = organizationId;
    }

    public String getZoneId() {
        return ZoneId;
    }

    public void setZoneId(String zoneId) {
        ZoneId = zoneId;
    }

    public String getBranchId() {
        return BranchId;
    }

    public void setBranchId(String branchId) {
        BranchId = branchId;
    }

    public String getContactEmail() {
        return ContactEmail;
    }

    public void setContactEmail(String contactEmail) {
        ContactEmail = contactEmail;
    }

    public String getContactMobile() {
        return ContactMobile;
    }

    public void setContactMobile(String contactMobile) {
        ContactMobile = contactMobile;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

    public HelperAppFeatureModel getHelperAppFeatureModel() {
        return helperAppFeatureModel;
    }

    public void setHelperAppFeatureModel(HelperAppFeatureModel helperAppFeatureModel) {
        this.helperAppFeatureModel = helperAppFeatureModel;
    }

    public ArrayList<RolesModel> getRolesModels() {
        return rolesModels;
    }

    public void setRolesModels(ArrayList<RolesModel> rolesModels) {
        this.rolesModels = rolesModels;
    }
}
