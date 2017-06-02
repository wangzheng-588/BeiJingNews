package com.wz.beijingnews.common.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by wz on 17-4-26.
 */

public class ToastUtil {

    private static Toast mToast;

    public static void show(Context context, String text){
        if (mToast == null){
            mToast = Toast.makeText(context,text,Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
        }

        mToast.show();
    }

}
