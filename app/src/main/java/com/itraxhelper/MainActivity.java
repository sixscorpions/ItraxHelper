package com.itraxhelper;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

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
}
