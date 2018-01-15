package com.itraxhelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itraxhelper.models.LoginModel;
import com.itraxhelper.parser.LoginParser;
import com.itraxhelper.utils.Constants;
import com.itraxhelper.utils.DBHelper;
import com.itraxhelper.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.ll_escort)
    LinearLayout ll_escort;

    @BindView(R.id.ll_mess)
    LinearLayout ll_mess;

    @BindView(R.id.tv_logout)
    TextView tv_logout;

    private LoginModel loginModel;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(MainActivity.this);

        ButterKnife.bind(this);
        LoginParser loginParser = new LoginParser();
        loginModel = (LoginModel) loginParser.parse(Utility.getSharedPrefStringData(MainActivity.this, Constants.LOGIN_RESPONSE), MainActivity.this);
        if (loginModel.getHelperAppFeatureModel().getName().equalsIgnoreCase("escort")) {
            ll_escort.setVisibility(View.VISIBLE);
            ll_mess.setVisibility(View.GONE);
        } else if (loginModel.getHelperAppFeatureModel().getName().equalsIgnoreCase("mess")) {
            ll_escort.setVisibility(View.GONE);
            ll_mess.setVisibility(View.VISIBLE);
        } else {
            ll_escort.setVisibility(View.VISIBLE);
            ll_mess.setVisibility(View.VISIBLE);
        }

        tv_logout.setTypeface(Utility.getMaterialIconsRegular(MainActivity.this));
    }

    @OnClick(R.id.tv_logout)
    void logOut() {
        Intent intent = new Intent(this, LoginActivity.class);
        Utility.setSharedPrefStringData(this, Constants.LOGIN_ID, "");
        Utility.setSharedPrefStringData(this, Constants.LOGIN_NAME, "");
        Utility.setSharedPrefStringData(this, Constants.LOGIN_USERNAME, "");
        Utility.setSharedPrefStringData(this, Constants.LOGIN_ORGANIZATION_ID, "");
        Utility.setSharedPrefStringData(this, Constants.LOGIN_ZONE_ID, "");
        Utility.setSharedPrefStringData(this, Constants.LOGIN_BRANCH_ID, "");
        Utility.setSharedPrefStringData(this, Constants.LOGIN_CONTACT_EMAIL, "");
        Utility.setSharedPrefStringData(this, Constants.LOGIN_CONTACT_MOBILE, "");
        Utility.setSharedPrefStringData(this, Constants.LOGIN_PHOTO, "");
        Utility.setSharedPrefStringData(this, Constants.LOGIN_SESSION_ID, "");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.ll_mess)
    void navigateToMess() {
        Intent intent = new Intent(this, DashBoardActivity.class);
        intent.putExtra(Constants.TYPE, "mess");
        intent.putExtra(Constants.MODE, "");
        startActivity(intent);
    }

    @OnClick(R.id.ll_in)
    void navigateToDropIn() {
        Intent intent = new Intent(this, DashBoardActivity.class);
        intent.putExtra(Constants.TYPE, "escort");
        intent.putExtra(Constants.MODE, "in");
        startActivity(intent);
    }

    @OnClick(R.id.ll_out)
    void navigateToDropOut() {
        Intent intent = new Intent(this, DashBoardActivity.class);
        intent.putExtra(Constants.TYPE, "escort");
        intent.putExtra(Constants.MODE, "out");
        startActivity(intent);
    }
}
