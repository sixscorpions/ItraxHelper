package com.itraxhelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.itraxhelper.models.LoginModel;
import com.itraxhelper.parser.LoginParser;
import com.itraxhelper.utils.Constants;
import com.itraxhelper.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.ll_escort)
    LinearLayout ll_escort;

    @BindView(R.id.ll_mess)
    LinearLayout ll_mess;

    private LoginModel loginModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
