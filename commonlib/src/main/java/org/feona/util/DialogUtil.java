package org.feona.util;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

/**
 * Created by feona on 2017/9/13.
 */

public class DialogUtil {

    public static void alertInfo(Context context, int layoutId) {
        final AlertDialog build = new AlertDialog.Builder(context).create();
        //自定义布局
        View view = LayoutInflater.from(context).inflate(layoutId, null);
        build.show();

        Window window = build.getWindow();
        window.setContentView(view);

    }
}
