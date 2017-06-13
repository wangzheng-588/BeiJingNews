package com.wz.beijingnews.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.CheckBox;

/**
 * Created by wz on 17-6-8.
 */

public class CheckBoxNoTouch extends CheckBox {
    public CheckBoxNoTouch(Context context) {
        super(context);
    }

    public CheckBoxNoTouch(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckBoxNoTouch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
