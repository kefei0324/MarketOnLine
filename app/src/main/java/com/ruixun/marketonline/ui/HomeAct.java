package com.ruixun.marketonline.ui;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.ruixun.marketonline.R;
import org.feona.commonlib.BaseAct;
import org.feona.commonlib.UserManager;

public class HomeAct extends BaseAct implements View.OnClickListener {

    private Button btnHome;

    @Override
    protected int getChildViewId() {
        return R.layout.act_home;
    }

    @Override
    protected void initWidget() {
        btnHome = $(R.id.btn_home);
        btnHome.setOnClickListener(this);
        UserManager.getInstance().setUserName("FEONA");//TODO 实现在 application 中的onCreate 或者 登录成功的返回中..保证全局唯一
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back_slct);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeAct.this.finish();
            }
        });
        getSupportActionBar().setTitle(R.string.camera);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home_camera:
                        break;
                    case R.id.home_picture:
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_home:
                jump(ProfileAct.class);
                break;
        }
    }
}
