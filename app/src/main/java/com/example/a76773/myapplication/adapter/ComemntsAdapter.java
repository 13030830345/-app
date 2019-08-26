package com.example.a76773.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.a76773.myapplication.BuValue.CommentsValue;
import com.example.a76773.myapplication.R;

import java.util.List;

public class ComemntsAdapter extends ArrayAdapter<CommentsValue> {
    Context context; //适配器上下文
    public ComemntsAdapter(Context context, int resource,  List<CommentsValue> objects) {
        super(context, resource, objects);
        this.context=context;
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        // 账号userId_c  用户comment_c

        convertView = convertView != null ? convertView : LayoutInflater.from(getContext()).inflate(R.layout.commentadapter, parent, false);
        TextView userId_c=convertView.findViewById(R.id.userId_c);
        TextView comment_c=convertView.findViewById(R.id.comment_c);
        TextView time_c= convertView.findViewById(R.id.time_c);

        //空间初始化
        final CommentsValue value=getItem(position);
        userId_c.setText(value.getUserId());
        comment_c.setText(value.getComment());
        time_c .setText(value.getTime());
        return  convertView;
    }

}
