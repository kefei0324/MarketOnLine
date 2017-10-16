package org.feona.commonlib;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;
import feona.org.commonlib.BuildConfig;
import feona.org.commonlib.R;

/**
 * @AUTHER feona
 * @CREATE 2017/10/16  11:39
 **/
public abstract class BaseAct extends AppCompatActivity {

    /**
     * LogTAG
     */
    private String TAG = getClass().getSimpleName();

    /**
     * 延时加载视图（无数据视图/无网络视图/常规视图）
     */
    protected ViewStub noData, noNet, norMal;
    /**
     * 公共toolbar
     */
    protected Toolbar toolbar;

    /**
     * 是否已经添加水印
     */
    private boolean hasWater;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_container);

        toolbar = $(R.id.toolbar);
        noData = $(R.id.vs_nodata);
        noNet = $(R.id.vs_nonet);
        norMal = $(R.id.vs_container);

        initUi();//加载视图
        initToolbar(toolbar);//初始化toolbar
    }

    /**
     * 加载视图/判断是否有网络
     */
    protected void initUi() {
        if (NetManager.isNetworkConnected(this)) {//判断网络连接是否可用
            visibleNormal();//加载常规视图
            loadData();//加载数据（子类实现）
        } else {
            visibleNoNet();//加载无网络视图
        }
    }

    /**
     * 加载数据（后台线程）
     */
    protected void loadData() {
    }

    /**
     * 加载无网络视图
     */
    protected void visibleNoNet() {
        if (noNet.getParent() != null) {
            noNet.inflate();
        }
        if (noData.getParent() == null) {
            noData.setVisibility(View.GONE);
        }
        if (norMal.getParent() == null) {
            norMal.setVisibility(View.GONE);
        }
    }

    /**
     * 加载无数据视图
     */
    protected void visibleNoData() {

        if (noData.getParent() != null) {
            noData.inflate();
        }
        if (noNet.getParent() == null) {
            noNet.setVisibility(View.GONE);
        }
        if (norMal.getParent() == null) {
            norMal.setVisibility(View.GONE);
        }
    }

    /**
     * 加载常规视图
     */
    protected void visibleNormal() {

        if (norMal.getParent() != null) {
            norMal.setLayoutResource(getChildViewId());
            norMal.inflate();
            initWidget();
        }
        if (noData.getParent() == null) {
            noData.setVisibility(View.GONE);
        }
        if (noNet.getParent() == null) {
            noNet.setVisibility(View.GONE);
        }
    }

    /**
     * 公共方法/绑定组件
     *
     * @param resId 组件id
     * @param <V>   组件类型
     * @return 组件对象
     */
    protected <V extends View> V $(int resId) {
        return (V) findViewById(resId);
    }

    /**
     * 获取常规视图id（子类实现）
     *
     * @return
     */
    protected abstract int getChildViewId();

    protected abstract void initWidget();

    /**
     * 初始化toolbar
     * 设置标题，添加监听器，添加menu
     *
     * @param toolbar
     */
    protected abstract void initToolbar(Toolbar toolbar);

    /**
     * 跳转activity
     *
     * @param clz 目标activity ClassName
     */
    protected void jump(Class clz) {
        Intent intent = new Intent(this, clz);
        startActivity(intent);
    }

    /**
     * 携带数据跳转activity
     *
     * @param clz    目标activity ClassName
     * @param bundle 数据包
     */
    protected void jumpWithData(Class clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //是否debug模式（debug模式下才加水印）
        if (BuildConfig.DEBUG) {
            if (!hasWater) {
                ViewGroup viewGroup = getRootView(this);
                viewGroup.addView(initWater());
                hasWater = true;
            }
        }
    }

    /**
     * 初始化水印视图
     */
    private final View initWater() {
        View water = LayoutInflater.from(this).inflate(R.layout.view_watermark, null, false);
        String name = UserManager.getInstance().getUserName();//获取开发者调试id
        if (TextUtils.isEmpty(name)) {
            PackageManager pm = this.getPackageManager();
            name = getApplicationInfo().loadLabel(pm).toString();//开发者调试id为空则使用appname作为水印
        }
        ((TextView) water.findViewById(R.id.tv_watermark_left_up)).setText(name);
        ((TextView) water.findViewById(R.id.tv_watermark_left_down)).setText(name);
        ((TextView) water.findViewById(R.id.tv_watermark_centre)).setText(name);
        ((TextView) water.findViewById(R.id.tv_watermark_right_down)).setText(name);
        ((TextView) water.findViewById(R.id.tv_watermark_right_up)).setText(name);
        return water;
    }

    /**
     * 获取界面根容器
     * @param activity
     * @return
     */
    private final ViewGroup getRootView(Activity activity) {
        return (ViewGroup) activity.findViewById(android.R.id.content);
    }

    /**
     * 重新加载视图
     * @param view
     */
    protected void reLoad(View view) {
        initUi();
    }
}
