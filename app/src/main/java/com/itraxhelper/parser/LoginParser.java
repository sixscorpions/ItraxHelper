package com.itraxhelper.parser;

import com.itraxhelper.models.LoginModel;
import com.itraxhelper.models.Model;

import org.json.JSONObject;

/**
 * Created by shankar on 7/17/2017.
 */

public class LoginParser implements Parser<Model> {

    @Override
    public Model parse(String s) {
        LoginModel loginModel = new LoginModel();
        try {
            JSONObject jsonObject = new JSONObject(s);
            loginModel.setId(jsonObject.optString("Id"));
            loginModel.setName(jsonObject.optString("Name"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return loginModel;
    }
}