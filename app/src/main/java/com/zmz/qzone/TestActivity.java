package com.zmz.qzone;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TestActivity extends AppCompatActivity{
    private TextView tv_recieve;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        tv_recieve = (TextView) findViewById(R.id.tv_recieve);
        tv_recieve.setMovementMethod(LinkMovementMethod.getInstance());//不设置 没有点击事件
        //仿qq空间点赞text文本分段点击事件
        setTextMultClick();

    }
    //准备数据
    private void setTextMultClick() {
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < 20; i++) {
            sb.append("好友"+i+",");
        }

        String users=sb.substring(0,sb.lastIndexOf(","));
        tv_recieve.setText(addClickPart(users), TextView.BufferType.SPANNABLE);
    }

    //定义点击每个部分文字的处理方法
    private SpannableStringBuilder addClickPart(String users) {
        ImageSpan imageSpan=new ImageSpan(getApplicationContext(),R.mipmap.ic_launcher);
        SpannableString spanStr=new SpannableString("i.");//任意文字 主要是实现效果
        //Spanned.SPAN_EXCLUSIVE_EXCLUSIVE 前后都不包括
        spanStr.setSpan(imageSpan,0,1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        //创建一个ssb 存储总的用户
        SpannableStringBuilder ssb=new SpannableStringBuilder(spanStr);
        ssb.append(users);

        //为每段数据创建点击 事件
        String[] users_array=users.split(",");
        if(users_array.length>0){
            for (int i = 0; i < users_array.length; i++) {
                final String user_name = users_array[i];//好友0
                int start=users.indexOf(user_name)+spanStr.length();

                //为每段数据增加点击事件
                ssb.setSpan(new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {
                        Toast.makeText(TestActivity.this,user_name, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setColor(Color.BLUE);
//                        ds.setUnderlineText(false);
                    }
                },start,start+user_name.length(),0);
            }
        }

        return ssb.append("等"+users_array.length+"人觉得很赞");
    }

}
