package com.itraxhelper;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.itraxhelper.aynctask.IAsyncCaller;
import com.itraxhelper.aynctask.ServerJSONAsyncTask;
import com.itraxhelper.models.LoginModel;
import com.itraxhelper.models.Model;
import com.itraxhelper.models.RFIDModel;
import com.itraxhelper.parser.LoginParser;
import com.itraxhelper.parser.RFIDParser;
import com.itraxhelper.utils.APIConstants;
import com.itraxhelper.utils.Constants;
import com.itraxhelper.utils.Utility;

import org.json.JSONArray;

import java.util.LinkedHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shankar on 8/4/2017.
 */

public class DashBoardActivity extends BaseActivity implements IAsyncCaller {

    @BindView(R.id.et_id)
    EditText et_id;

    @BindView(R.id.tv_title)
    TextView tv_title;

    private Intent intent;
    private String mType;
    private String mMode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        ButterKnife.bind(this);
        intent = getIntent();
        if (intent.hasExtra(Constants.TYPE)) {
            mType = intent.getStringExtra(Constants.TYPE);
            mMode = intent.getStringExtra(Constants.MODE);
            tv_title.setText(mType.toUpperCase() + " " + mMode.toUpperCase());
        }

        et_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 9)
                    sendDataToServer();
            }
        });
    }

    void sendDataToServer() {
        if (isValidFields()) {
            try {
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                JSONArray jsonArray = new JSONArray();
                linkedHashMap.put("RFId", et_id.getText().toString());
                linkedHashMap.put("Type", mType);
                linkedHashMap.put("Time", Utility.getTime());
                linkedHashMap.put("Date", Utility.getDate());
                linkedHashMap.put("Year", Utility.getYear());
                linkedHashMap.put("Month", Utility.getMonth());
                linkedHashMap.put("Month", Utility.getMonth());
                linkedHashMap.put("Mode", mMode);
                RFIDParser rfidParser = new RFIDParser();
                ServerJSONAsyncTask serverJSONAsyncTask = new ServerJSONAsyncTask(
                        this, Utility.getResourcesString(this, R.string.please_wait), true,
                        APIConstants.CREATE_ESCORT_MESS_ATTENDANCE, linkedHashMap,
                        APIConstants.REQUEST_TYPE.POST, this, rfidParser);
                Utility.execute(serverJSONAsyncTask);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isValidFields() {
        boolean isValidated = false;
        if (Utility.isValueNullOrEmpty(et_id.getText().toString().trim())) {
            Utility.setSnackBar(et_id, "Please scan your card first");
            et_id.requestFocus();
        } else {
            isValidated = true;
        }
        return isValidated;
    }

    @Override
    public void onComplete(Model model) {
        if (model != null) {
            if (model instanceof RFIDModel) {
                RFIDModel mRFIDModel = (RFIDModel) model;
                Utility.showToastMessage(DashBoardActivity.this, "Student Name: " + mRFIDModel.getStudentName());
            }
        }
    }
}
