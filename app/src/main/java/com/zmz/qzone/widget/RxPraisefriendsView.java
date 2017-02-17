package com.zmz.qzone.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.util.LruCache;
import android.widget.TextView;

import com.zmz.qzone.R;
import com.zmz.qzone.model.PraiseUser;
import com.zmz.qzone.widget.clickable.ClickableSpanEx;
import com.zmz.qzone.widget.clickable.PraiseClick;

import java.util.List;


/**
 * Created by Mzone on 2016/11/27.
 */
public class RxPraisefriendsView extends TextView {
    private static final String TAG = "PraiseWidget";

    //点赞名字展示的默认颜色
    private int textColor = 0xff517fae;
    //点赞列表心心默认图标
    private int iconRes = R.drawable.skin_feed_icon_review_praise;
    //默认字体大小
    private int textSize = 14;
    //默认点击背景
    private int clickBg = 0x00000000;

    private List<PraiseUser> datas;

    private static final LruCache<String, SpannableStringBuilderAllVer> praiseCache
            = new LruCache<String, SpannableStringBuilderAllVer>(50) {
        @Override
        protected int sizeOf(String key, SpannableStringBuilderAllVer value) {
            return 1;
        }
    };

    public RxPraisefriendsView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RxPraisefriendsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RxPraisefriendsView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RxPraisefriendsView);
        textColor = a.getColor(R.styleable.RxPraisefriendsView_font_color, 0xff517fae);
        textSize = a.getDimensionPixelSize(R.styleable.RxPraisefriendsView_font_size, 14);
        clickBg = a.getColor(R.styleable.RxPraisefriendsView_click_bg_color, 0x00000000);
        iconRes = a.getResourceId(R.styleable.RxPraisefriendsView_like_icon, R.drawable.skin_feed_icon_review_praise);
        a.recycle();
        //如果不设置，clickableSpan不能响应点击事件
        this.setMovementMethod(LinkMovementMethod.getInstance());
        setOnTouchListener(new ClickableSpanEx.ClickableSpanSelector());
        setTextSize(textSize);
    }

    public void setDatas(List<PraiseUser> datas) {
        this.datas = datas;
    }

    @Override
    public boolean onPreDraw() {
        if (datas == null || datas.size() == 0) {
            return super.onPreDraw();
        }
        else {
            createSpanStringBuilder(datas);
            return true;
        }
    }

    private void createSpanStringBuilder(List<PraiseUser> datas) {
        if (datas == null || datas.size() == 0) return;
        String key = Integer.toString(datas.hashCode() + datas.size());
        SpannableStringBuilderAllVer spanStrBuilder = praiseCache.get(key);
        if (spanStrBuilder == null) {
            CustomImageSpan icon = new CustomImageSpan(getContext(), iconRes);
            //因为spanstringbuilder不支持直接append span，所以通过spanstring转换
            SpannableString iconSpanStr = new SpannableString(" ");
            iconSpanStr.setSpan(icon, 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

            spanStrBuilder = new SpannableStringBuilderAllVer(iconSpanStr);
            //给出两个空格，点赞图标后
            spanStrBuilder.append(" ");
            for (int i = 0; i < datas.size(); i++) {
                PraiseClick praiseClick = new PraiseClick.Builder(getContext(), datas.get(i)).setTextSize(textSize)
                        .setColor(textColor)
                        .setClickEventColor(clickBg)
                        .build();
                try {
                    spanStrBuilder.append(datas.get(i).getNick(), praiseClick, 0);
                }catch (NullPointerException e){
                    e.printStackTrace();
                    Log.e(TAG, "praiseUserInfo是空的哦");
                }
                if (i != datas.size() - 1) spanStrBuilder.append(", ");
               // else spanStrBuilder.append("\0");
                else spanStrBuilder.append("等"+datas.size()+"人觉得很赞");
            }
            praiseCache.put(key, spanStrBuilder);
        }
        setText(spanStrBuilder);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        praiseCache.evictAll();
        if (praiseCache.size() == 0) {
            Log.d(TAG, "clear cache success!");
        }
    }
}