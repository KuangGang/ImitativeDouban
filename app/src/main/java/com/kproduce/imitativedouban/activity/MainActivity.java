package com.kproduce.imitativedouban.activity;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.kproduce.imitativedouban.R;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.rv)
    RecyclerView rv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {

    }
}
