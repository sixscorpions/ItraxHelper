package com.itraxhelper;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itraxhelper.aynctask.IAsyncCaller;
import com.itraxhelper.aynctask.ServerJSONAsyncTask;
import com.itraxhelper.aynctaskold.ServerIntractorAsync;
import com.itraxhelper.db.DatabaseHandler;
import com.itraxhelper.db.MessDataSource;
import com.itraxhelper.models.MessEscortDataModel;
import com.itraxhelper.models.Model;
import com.itraxhelper.models.RFIDModel;
import com.itraxhelper.parser.RFIDParser;
import com.itraxhelper.utils.APIConstants;
import com.itraxhelper.utils.Constants;
import com.itraxhelper.utils.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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

    @BindView(R.id.offlineCount)
    TextView offlineCount;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.rl_db_records)
    RelativeLayout rl_db_records;

    private Intent intent;
    private String mType;
    private String mMode;
    private MessDataSource messDataSource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        ButterKnife.bind(this);
        intent = getIntent();
        if (intent.hasExtra(Constants.TYPE)) {
            mType = intent.getStringExtra(Constants.TYPE);
            mMode = intent.getStringExtra(Constants.MODE);
            if (mType.equalsIgnoreCase("mess"))
                tv_title.setText(mType.toUpperCase() + " " + mMode.toUpperCase());
            else
                tv_title.setText(mMode.toUpperCase());
        }

        DatabaseHandler.getInstance(this);
        messDataSource = new MessDataSource(this);
        offlineCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataToServer();
            }
        });

        if (messDataSource.getDataCount() > 0) {
            rl_db_records.setVisibility(View.VISIBLE);
            offlineCount.setText("" + messDataSource.getDataCount());
        } else {
            rl_db_records.setVisibility(View.GONE);
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
                if (s.length() > 9) {
                    if (Utility.isNetworkAvailable(DashBoardActivity.this)) {
                        if (!messDataSource.isExistingAddress(et_id.getText().toString() + mType + Utility.getDate() + mMode)) {
                            saveInLocalDb();
                            sendDataToServer();
                            Utility.showLog("1", "1");
                        } else {
                            Utility.showToastMessage(DashBoardActivity.this, "Already Record there");
                            Utility.showLog("2", "2");
                            et_id.setText("");
                        }
                    } else {
                        if (!messDataSource.isExistingAddress(et_id.getText().toString() + mType + Utility.getDate() + mMode)) {
                            Utility.showLog("3", "3 " + et_id.getText().toString() + mType + Utility.getDate() + mMode);
                            saveInLocalDb();
                        } else {
                            Utility.showToastMessage(DashBoardActivity.this, "Already Record there");
                            Utility.showLog("4", "4 " + et_id.getText().toString() + mType + Utility.getDate() + mMode);
                            et_id.setText("");
                        }
                    }
                }

            }
        });
    }


    /**
     * This method is used for sync
     */
    @OnClick(R.id.offlineCount)
    void sync() {
        sendDataToServer();
    }

    /**
     * This method is used to save data in the local db
     */
    private void saveInLocalDb() {
        MessEscortDataModel messEscortDataModel = new MessEscortDataModel();
        try {
            messEscortDataModel.setRFId(et_id.getText().toString());
            messEscortDataModel.setType(mType);
            messEscortDataModel.setTime(Utility.getTime());
            messEscortDataModel.setDate(Utility.getDate());
            messEscortDataModel.setYear(Utility.getYear());
            messEscortDataModel.setMonth(Utility.getMonth());
            messEscortDataModel.setMode(mMode);
            messEscortDataModel.setAll(et_id.getText().toString() + mType + Utility.getDate() + mMode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        messDataSource.insertData(messEscortDataModel);

        if (!Utility.isNetworkAvailable(this)) {
            et_id.setText("");
            if (messDataSource.getDataCount() > 0) {
                rl_db_records.setVisibility(View.VISIBLE);
                offlineCount.setText("" + messDataSource.getDataCount());
            } else {
                rl_db_records.setVisibility(View.GONE);
            }
            Toast.makeText(DashBoardActivity.this, "Saved record in local DB", Toast.LENGTH_SHORT).show();
        } else {
            et_id.setText("");
        }
    }


    private void sendDataToServer() {
        final ArrayList<MessEscortDataModel> messEscortDataModels = messDataSource.selectAll();
        JSONArray jsonSalesRecordsArray = new JSONArray();
        if (messEscortDataModels != null && messEscortDataModels.size() > 0) {
            for (int i = 0; i < messEscortDataModels.size(); i++) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("RFId", messEscortDataModels.get(i).getRFId());
                    jsonObject.put("Type", messEscortDataModels.get(i).getType());
                    jsonObject.put("Time", messEscortDataModels.get(i).getTime());
                    jsonObject.put("Date", messEscortDataModels.get(i).getDate());
                    jsonObject.put("Year", messEscortDataModels.get(i).getYear());
                    jsonObject.put("Month", messEscortDataModels.get(i).getMonth());
                    jsonObject.put("Mode", messEscortDataModels.get(i).getMode());
                    jsonSalesRecordsArray.put(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put("MessEscortData", jsonSalesRecordsArray);
            RFIDParser rfidParser = new RFIDParser();
            ServerIntractorAsync serverJSONAsyncTask = new ServerIntractorAsync(
                    this, Utility.getResourcesString(this, R.string.please_wait), true,
                    APIConstants.CREATE_ESCORT_MESS_ATTENDANCE, linkedHashMap,
                    APIConstants.REQUEST_TYPE.POST, this, rfidParser);
            Utility.execute(serverJSONAsyncTask);
        } catch (Exception e) {
            e.printStackTrace();
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
                //Utility.showToastMessage(DashBoardActivity.this, "Student Name: " + mRFIDModel.getStudentName());
                SpannableStringBuilder biggerText = new SpannableStringBuilder(mRFIDModel.getStudentName());
                biggerText.setSpan(new RelativeSizeSpan(2.0f), 0, mRFIDModel.getStudentName().length(), 0);
                Toast.makeText(DashBoardActivity.this, biggerText, Toast.LENGTH_SHORT).show();
                messDataSource.deleteAll();
                if (messDataSource.getDataCount() > 0) {
                    rl_db_records.setVisibility(View.VISIBLE);
                    offlineCount.setText("" + messDataSource.selectAll().size());
                } else {
                    rl_db_records.setVisibility(View.GONE);
                }
            }
        }

        et_id.setText("");
    }
}
