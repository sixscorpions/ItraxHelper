package com.itraxhelper;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import com.itraxhelper.aynctask.IAsyncCaller;
import com.itraxhelper.aynctask.ServerJSONAsyncTask;
import com.itraxhelper.models.LoginModel;
import com.itraxhelper.models.Model;
import com.itraxhelper.parser.LoginParser;
import com.itraxhelper.utils.APIConstants;
import com.itraxhelper.utils.Utility;

import org.json.JSONArray;

import java.util.LinkedHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by shankar on 7/16/2017.
 */

public class LoginActivity extends BaseActivity implements IAsyncCaller {

    @BindView(R.id.et_username)
    EditText et_username;

    @BindView(R.id.et_password)
    EditText et_password;

    @BindView(R.id.button_login)
    ImageButton button_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_login)
    void navigateToLogin() {
        if (isValidFields()) {
            try {
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                JSONArray jsonArray = new JSONArray();
                linkedHashMap.put("username", et_username.getText().toString());
                linkedHashMap.put("password", et_password.getText().toString());
                LoginParser loginParser = new LoginParser();
                ServerJSONAsyncTask serverJSONAsyncTask = new ServerJSONAsyncTask(
                        this, Utility.getResourcesString(this, R.string.please_wait), true,
                        APIConstants.HELPER_LOGIN, linkedHashMap,
                        APIConstants.REQUEST_TYPE.POST, this, loginParser);
                Utility.execute(serverJSONAsyncTask);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    private boolean isValidFields() {
        boolean isValidated = false;
        if (Utility.isValueNullOrEmpty(et_username.getText().toString().trim())) {
            Utility.setSnackBar(et_username, "Please enter user name");
            et_username.requestFocus();
        } else if (Utility.isValueNullOrEmpty(et_password.getText().toString().trim())) {
            Utility.setSnackBar(et_password, "Please enter pass word");
            et_username.requestFocus();
        } else {
            isValidated = true;
        }
        return isValidated;
    }

    @Override
    public void onComplete(Model model) {
        if (model != null) {
            if (model instanceof LoginModel) {

            }
        }
    }
}
