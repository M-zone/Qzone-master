package com.zmz.qzone.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zmz.qzone.R;
import com.zmz.qzone.model.Message;
import com.zmz.qzone.widget.CircleImageView;
import com.zmz.qzone.widget.RxPraisefriendsView;

import java.util.List;

/**
 * Created by Mzone on 2016/11/21.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    private List<Message> msgList;
    private Context mContext;

    public MessageAdapter(Context mContext, List<Message> msgList) {
        super();
        this.msgList = msgList;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //通过setDataByArray设置点赞好友
        holder.praise_members.setDatas(msgList.get(position).getPraise_members());
    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView imageView;
        public TextView name;
        public TextView time;
        public TextView content;
        public RxPraisefriendsView praise_members;
        public TextView comment;
        public ImageView emoji;

        public ViewHolder(View view) {
            super(view);
            this.imageView = (CircleImageView) view.findViewById(R.id.imageView);
            this.name = (TextView) view.findViewById(R.id.name);
            this.time = (TextView) view.findViewById(R.id.time);
            this.content = (TextView) view.findViewById(R.id.content);
            this.praise_members = (RxPraisefriendsView) view.findViewById(R.id.praise_members);
            this.comment = (TextView) view.findViewById(R.id.comment);
            this.emoji = (ImageView) view.findViewById(R.id.emoji);
        }
    }
}
