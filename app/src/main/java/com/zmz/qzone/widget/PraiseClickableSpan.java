package com.zmz.qzone.widget;

import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;

/**
 * Created by Mzone on 2016/11/21.
 */

public class PraiseClickableSpan extends ClickableSpan {
    private boolean underlineText = false;
    private int textColor = Color.BLUE;
    private OnMultClickListener mOnMultClickListener;

    public void init(boolean underlineText, int textColor) {
        this.underlineText = underlineText;
        this.textColor = textColor;
    }

    @Override
    public void onClick(View widget) {
        if (this.mOnMultClickListener != null) {
            Log.e("onClick", mOnMultClickListener.toString() + "");
            this.mOnMultClickListener.onMultClick(widget);
        }
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setColor(textColor);
        ds.setUnderlineText(underlineText);
    }

    public void setOnMultClickListener(OnMultClickListener mOnMultClickListener) {
        Log.e("setOnMultClickListener", mOnMultClickListener.toString() + "");
        this.mOnMultClickListener = mOnMultClickListener;
    }

    public interface OnMultClickListener {
        void onMultClick(View view);
    }
}
