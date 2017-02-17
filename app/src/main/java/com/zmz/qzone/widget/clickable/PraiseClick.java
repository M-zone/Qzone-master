package com.zmz.qzone.widget.clickable;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.view.View;

import com.zmz.qzone.model.PraiseUser;
import com.zmz.qzone.utils.UIHelper;


/**
 * Created by Mzone on 2017/2/21.
 * 点击事件
 */
public class PraiseClick extends ClickableSpanEx {
    private static final int DEFAULT_COLOR = 0xff517fae;
    private int color;
    private Context mContext;
    private int textSize;
    private PraiseUser mPraiseInfo;

    private PraiseClick() {}

    private PraiseClick(Builder builder) {
        super(builder.color,builder.clickBgColor);
        mContext = builder.mContext;
        mPraiseInfo = builder.mPraiseInfo;
        this.textSize = builder.textSize;
    }

    @Override
    public void onClick(View widget) {
        if (mPraiseInfo!=null)
            UIHelper.ToastMessage("当前用户名是： " + mPraiseInfo.getNick() + "   它的ID是： " + mPraiseInfo.getUserid());
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setTextSize(textSize);
    }


    public static class Builder {
        private int color;
        private Context mContext;
        private int textSize=16;
        private PraiseUser mPraiseInfo;
        private int clickBgColor;

        public Builder(Context context, @NonNull PraiseUser info) {
            mContext = context;
            mPraiseInfo=info;
        }

        public Builder setTextSize(int textSize) {
            this.textSize = UIHelper.sp2px(textSize);
            return this;
        }

        public Builder setColor(int color) {
            this.color = color;
            return this;
        }

        public Builder setClickEventColor(int color){
            this.clickBgColor=color;
            return this;
        }

        public PraiseClick build() {
            return new PraiseClick(this);
        }
    }
}
