package org.feona.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by feona on 2017/9/13.
 */

public class BaseDialog extends Dialog {

    protected Context mContext;
    private int layoutId;
    private View mView;

    public BaseDialog(Context mContext, int layoutId) {
        super(mContext);
        this.mContext = mContext;
        this.layoutId = layoutId;
        mView = LayoutInflater.from(mContext).inflate(layoutId, null);
    }

    /**
     * 获取元素对象
     *
     * @param viewId
     * @param <V>
     * @return
     */
    public <V extends View> V getViewById(int viewId) {
//        View childView = this.mViews.get(viewId);
//        if(childView == null) {
//            childView = mConvertView.findViewById(viewId);
//            this.mViews.put(viewId, childView);
//        }
//        return (V)childView;
        return (V) mView.findViewById(viewId);
    }

    /**
     * 给元素赋值
     *
     * @param viewId
     * @param data
     */
    public void setValueById(int viewId, Object data, int placeHolder) {
//        View view = mViews.get(viewId);
        View view = getViewById(viewId);
        if (view == null) {
//            view = mConvertView.findViewById(viewId);
//            mViews.put(viewId, view);
            return;
        }
        if (data == null) {
            return;
        }

        if (TextView.class.isAssignableFrom(view.getClass())) {
            ((TextView) view).setText(data.toString());
        } else if (Button.class.isAssignableFrom(view.getClass())) {
            ((Button) view).setText(data.toString());
        } else if (CheckBox.class.isAssignableFrom(view.getClass())) {
            ((CheckBox) view).setText(data.toString());
        } else if (RadioButton.class.isAssignableFrom(view.getClass())) {
            ((RadioButton) view).setText(data.toString());
        } else if (EditText.class.isAssignableFrom(view.getClass())) {
            ((EditText) view).setText(data.toString());
        } else if (ImageView.class.isAssignableFrom(view.getClass())) {
            if (Integer.class == data.getClass()
                    || int.class == data.getClass()) {
                ((ImageView) view).setImageResource(Integer.valueOf(data.toString()));
            } else {
                // 图片下载，可以在此集成第三方异步加载图片网络库
                try {
//                    RequestOptions options = new RequestOptions();
//                    options.placeholder(placeHolder);
//                    Glide.with(mContext).load(data.toString()).apply(options).into(((ImageView) view));
                    Glide.with(mContext).load(data.toString()).placeholder(placeHolder).into((ImageView) view);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 给元素赋值
     *
     * @param viewId
     * @param data
     */
    public void setValueById(int viewId, Object data) {
//        View view = mViews.get(viewId);
        View view = getViewById(viewId);
        if (view == null) {
//            view = mConvertView.findViewById(viewId);
//            mViews.put(viewId, view);
            return;
        }
        if (data == null) {
            return;
        }

        if (TextView.class.isAssignableFrom(view.getClass())) {
            ((TextView) view).setText(data.toString());
        } else if (Button.class.isAssignableFrom(view.getClass())) {
            ((Button) view).setText(data.toString());
        } else if (CheckBox.class.isAssignableFrom(view.getClass())) {
            ((CheckBox) view).setText(data.toString());
        } else if (RadioButton.class.isAssignableFrom(view.getClass())) {
            ((RadioButton) view).setText(data.toString());
        } else if (EditText.class.isAssignableFrom(view.getClass())) {
            ((EditText) view).setText(data.toString());
        } else if (ImageView.class.isAssignableFrom(view.getClass())) {
            if (Integer.class == data.getClass()
                    || int.class == data.getClass()) {
                ((ImageView) view).setImageResource(Integer.valueOf(data.toString()));
            } else {
                // 图片下载，可以在此集成第三方异步加载图片网络库
                try {
                    Glide.with(mContext).load(data.toString()).into(((ImageView) view));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
