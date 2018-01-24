package com.itraxhelper.parser;

import android.content.Context;

import com.itraxhelper.models.Model;
import com.itraxhelper.models.RFIDModel;

import org.json.JSONObject;

/**
 * Created by shankar on 7/17/2017.
 */

public class RFIDParser implements Parser<Model> {

    @Override
    public Model parse(String s, Context context) {
        RFIDModel rfidModel = new RFIDModel();
        try {
            JSONObject jsonObject = new JSONObject(s);
            if (jsonObject.has("StudentName")) {
                rfidModel.setStudentName(jsonObject.optString("StudentName"));
            } else if (jsonObject.has("message")) {
                rfidModel.setStudentName(jsonObject.optString("message"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rfidModel;
    }
}