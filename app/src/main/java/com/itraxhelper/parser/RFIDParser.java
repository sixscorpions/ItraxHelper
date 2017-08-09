package com.itraxhelper.parser;

import android.content.Context;

import com.itraxhelper.models.HelperAppFeatureModel;
import com.itraxhelper.models.LoginModel;
import com.itraxhelper.models.Model;
import com.itraxhelper.models.RFIDModel;
import com.itraxhelper.models.RolesModel;
import com.itraxhelper.utils.Constants;
import com.itraxhelper.utils.Utility;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by shankar on 7/17/2017.
 */

public class RFIDParser implements Parser<Model> {

    @Override
    public Model parse(String s, Context context) {
        RFIDModel rfidModel = new RFIDModel();
        try {
            JSONObject jsonObject = new JSONObject(s);
            rfidModel.setStudentName(jsonObject.optString("StudentName"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rfidModel;
    }
}