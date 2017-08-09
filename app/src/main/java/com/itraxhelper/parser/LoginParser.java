package com.itraxhelper.parser;

import android.content.Context;

import com.itraxhelper.models.HelperAppFeatureModel;
import com.itraxhelper.models.LoginModel;
import com.itraxhelper.models.Model;
import com.itraxhelper.models.RolesModel;
import com.itraxhelper.utils.Constants;
import com.itraxhelper.utils.Utility;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by shankar on 7/17/2017.
 */

public class LoginParser implements Parser<Model> {

    @Override
    public Model parse(String s, Context context) {
        LoginModel loginModel = new LoginModel();
        try {
            JSONObject jsonObject = new JSONObject(s);
            loginModel.setId(jsonObject.optString("Id"));
            loginModel.setName(jsonObject.optString("Name"));
            loginModel.setUserName(jsonObject.optString("UserName"));
            loginModel.setOrganizationId(jsonObject.optString("OrganizationId"));
            loginModel.setZoneId(jsonObject.optString("ZoneId"));
            loginModel.setBranchId(jsonObject.optString("BranchId"));
            loginModel.setContactEmail(jsonObject.optString("ContactEmail"));
            loginModel.setContactMobile(jsonObject.optString("ContactMobile"));
            loginModel.setPhoto(jsonObject.optString("Photo"));

            HelperAppFeatureModel helperAppFeatureModel = new HelperAppFeatureModel();
            JSONObject helperAppFeatureJsonObject = jsonObject.optJSONObject("HelperAppFeature");
            helperAppFeatureModel.setId(helperAppFeatureJsonObject.optString("Id"));
            helperAppFeatureModel.setName(helperAppFeatureJsonObject.optString("Name"));
            loginModel.setHelperAppFeatureModel(helperAppFeatureModel);

            ArrayList<RolesModel> rolesModels = new ArrayList<>();
            JSONArray jsonArray = jsonObject.optJSONArray("Roles");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                RolesModel rolesModel = new RolesModel();
                rolesModel.setName(jsonObject1.optString("Name"));
                rolesModel.setId(jsonObject1.optString("Id"));
                rolesModels.add(rolesModel);
            }
            loginModel.setRolesModels(rolesModels);
            Utility.setSharedPrefStringData(context, Constants.LOGIN_RESPONSE, s);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return loginModel;
    }
}