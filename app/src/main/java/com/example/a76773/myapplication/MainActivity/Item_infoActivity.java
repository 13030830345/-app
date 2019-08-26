package com.example.a76773.myapplication.MainActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a76773.myapplication.BuValue.CommentsValue;
import com.example.a76773.myapplication.BuValue.RegisiterValue;

import com.example.a76773.myapplication.R;
import com.example.a76773.myapplication.adapter.ComemntsAdapter;

import com.example.a76773.myapplication.service.Commentservice;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Item_infoActivity extends AppCompatActivity {
    private EditText comment;
    private ImageView imageView;
    LoginMainActivity activity;
    private Button submit;
    //服务
    Commentservice releaseservice;
    // 数据
    List<CommentsValue> releaselist;
    //适配器实例化
    ComemntsAdapter commodityaAdapter;
    // 账户ListView
    ListView releaselistew;
    Commentservice commentservice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);
        commentservice =new Commentservice(Item_infoActivity.this);
        activity=new LoginMainActivity();
        RegisiterValue regisiterValue=new RegisiterValue();
        //取得TextView对象
        comment =  findViewById(R.id.comment);
        imageView=findViewById(R.id.imageView_item);
        TextView textView=findViewById(R.id.item_price);
        TextView textView1=findViewById(R.id.item_title);
        TextView textView2=findViewById(R.id.item_info);
        TextView textView3=findViewById(R.id.contact);
        submit=findViewById(R.id.submit);
        //取得启动该Activity的Intent对象
        Intent intent =getIntent();
        String first = intent.getStringExtra("id");
        commentservice.init(first);
        //获取图片
        imageView.setImageBitmap(commentservice.releaseValue.getImage());
        textView.setText(commentservice.releaseValue.getPrice());
        textView1.setText(commentservice.releaseValue.getTitle());
        textView2.setText(commentservice.releaseValue.getInfo());
        textView3.setText(commentservice.releaseValue.getPhone());
        //评论
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String c= comment.getText().toString();
                String time =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

                String userId=activity.value.getUserId();
                String itemId=String.valueOf(commentservice.releaseValue.getId());
                //评论者账号userId，评论商品编号itemId，评论内容comment，评论时间time
                commentservice.getList(userId,itemId,c,time);
                Toast.makeText(Item_infoActivity.this,"评论成功",Toast.LENGTH_LONG).show();
                comment.setText("");
                releaselistew=findViewById(R.id.commentList);

                releaseservice = new Commentservice(Item_infoActivity.this);

                releaselist=releaseservice.getcommentList(String.valueOf(commentservice.releaseValue.getId()));

                commodityaAdapter = new ComemntsAdapter(Item_infoActivity.this, 0,releaselist);

                releaselistew.setAdapter(commodityaAdapter);


            }
        });
        init();
    }
    private void init(){
        // 获取listView对象
        releaselistew=findViewById(R.id.commentList);

        releaseservice = new Commentservice(Item_infoActivity.this);

//      releaseservice.init();

        releaselist=releaseservice.getcommentList(String.valueOf(commentservice.releaseValue.getId()));

        commodityaAdapter = new ComemntsAdapter(Item_infoActivity.this, 0,releaselist);

        releaselistew.setAdapter(commodityaAdapter);



    }
}
