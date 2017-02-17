package com.zmz.qzone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zmz.qzone.adapter.MessageAdapter;
import com.zmz.qzone.model.Message;
import com.zmz.qzone.model.PraiseUser;
import com.zmz.qzone.widget.RxPraisefriendsView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.msg_list)
    RecyclerView mRecyclerView;
    Message msg;
    List<PraiseUser> testBeans;
    List<Message> msgList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        msgList=new ArrayList<>();
        //手动生成五条说说
        for (int i=0;i<5;i++){
            msg=new Message();
            testBeans=new ArrayList<>();
            //手动生成十个点赞好友
            for (int j=0;j<10;j++){
                PraiseUser bean=new PraiseUser();
                bean.userNick="Friend"+j;
                bean.userId=j;
                testBeans.add(bean);
            }
            msg.setPraise_members(testBeans);
            msgList.add(msg);
        }
        mRecyclerView.setAdapter(new MessageAdapter(this, msgList));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxPraisefriendsView.clearPraiseWidgetCache();
    }
}
