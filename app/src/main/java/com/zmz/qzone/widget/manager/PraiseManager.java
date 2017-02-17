package com.zmz.qzone.widget.manager;

import android.content.Context;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.widget.TextView;

import com.zmz.qzone.R;
import com.zmz.qzone.widget.PraiseClickableSpan;
import com.zmz.qzone.widget.RxPraisefriendsView;

/**
 * Created by zmz on 2017/1/22.
 */

public class PraiseManager {
    private Context context;
    private SpannableString spanStr;
    public static PraiseManager mPraiseManager;
    private PraiseClickableSpan mPraiseClickableSpan;
    private RxPraisefriendsView mRxPraisefriendsView;

    private PraiseManager(Context context) {
        this.context = context;
    }

    public void setTextMultClick(String friends) {
        String users = friends.substring(0, friends.lastIndexOf("。"));
        mRxPraisefriendsView.setText(addClickPart(users), TextView.BufferType.SPANNABLE);
    }

    public void bindView(RxPraisefriendsView mRxPraisefriendsView) {
        this.mRxPraisefriendsView = mRxPraisefriendsView;
    }

    private void setImageSpanStart() {
        ImageSpan imageSpan = new ImageSpan(context, R.drawable.skin_feed_icon_review_praise);
        spanStr = new SpannableString("z");//任意文字 主要是实现效果
        //Spanned.SPAN_EXCLUSIVE_EXCLUSIVE 前后都不包括
        spanStr.setSpan(imageSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    //定义点击每个部分文字的处理方法
    private SpannableStringBuilder addClickPart(String users) {
        setImageSpanStart();
        //创建一个ssb 存储总的用户
        SpannableStringBuilder ssb = new SpannableStringBuilder(spanStr);
        ssb.append(users);

        //为每段数据创建点击 事件
        String[] users_array = users.split("、");
        if (users_array.length > 0) {
            for (int i = 0; i < users_array.length; i++) {
                final String user_name = users_array[i];//好友0
                int start = users.indexOf(user_name) + spanStr.length();

                //为每段数据增加点击事件
                ssb.setSpan(createClickableSpan(), start, start + user_name.length(), 0);
            }
        }

        return ssb.append("等" + users_array.length + "人觉得很赞");
    }

    public static PraiseManager getInstance(Context context) {
        synchronized (PraiseManager.class) {
            if (PraiseManager.mPraiseManager == null) {
                PraiseManager.mPraiseManager = new PraiseManager(context);
            }
        }
        return PraiseManager.mPraiseManager;
    }

    private PraiseClickableSpan createClickableSpan() {
        return new PraiseClickableSpan();
    }

    public void initSpan(boolean underlineText, int textColor) {
        this.mPraiseClickableSpan = createClickableSpan();
        this.mPraiseClickableSpan.init(underlineText, textColor);
    }
    public PraiseClickableSpan getSpan() {
       return this.mPraiseClickableSpan;
    }
}
