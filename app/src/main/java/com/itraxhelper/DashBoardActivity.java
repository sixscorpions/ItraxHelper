package com.itraxhelper;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.itraxhelper.aynctask.IAsyncCaller;
import com.itraxhelper.aynctask.ServerJSONAsyncTask;
import com.itraxhelper.models.Model;
import com.itraxhelper.parser.LoginParser;
import com.itraxhelper.utils.APIConstants;
import com.itraxhelper.utils.Constants;
import com.itraxhelper.utils.Utility;

import org.json.JSONArray;

import java.util.LinkedHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by shankar on 8/4/2017.
 */

public class DashBoardActivity extends BaseActivity implements IAsyncCaller {

    @BindView(R.id.et_id)
    EditText et_id;

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
        }
    }

    /**
     * This method is used to send data to server
     */
    @OnClick(R.id.btn_submit)
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
                LoginParser loginParser = new LoginParser();
                ServerJSONAsyncTask serverJSONAsyncTask = new ServerJSONAsyncTask(
                        this, Utility.getResourcesString(this, R.string.please_wait), true,
                        APIConstants.CREATE_ESCORT_MESS_ATTENDANCE, linkedHashMap,
                        APIConstants.REQUEST_TYPE.POST, this, loginParser);
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

    }
}
