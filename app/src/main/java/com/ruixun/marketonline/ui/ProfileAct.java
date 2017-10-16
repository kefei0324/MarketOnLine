package com.ruixun.marketonline.ui;


import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.ruixun.marketonline.R;
import org.feona.commonlib.BaseAct;


public class ProfileAct extends BaseAct {

    @Override
    protected int getChildViewId() {
        return R.layout.act_profile;
    }

    @Override
    protected void initWidget() {
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileAct.this.finish();
            }
        });
        getSupportActionBar().setTitle(R.string.camera);
        toolbar.setNavigationIcon(R.drawable.back_slct);
    }
}
