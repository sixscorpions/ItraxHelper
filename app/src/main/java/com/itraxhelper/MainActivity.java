package com.itraxhelper;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.itraxhelper.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.ll_in)
    LinearLayout ll_in;

    @BindView(R.id.ll_out)
    LinearLayout ll_out;

    @BindView(R.id.ll_mess)
    LinearLayout ll_mess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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
