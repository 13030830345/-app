package com.example.a76773.myapplication.MainActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a76773.myapplication.BuValue.ReleaseValue;
import com.example.a76773.myapplication.R;
import com.example.a76773.myapplication.adapter.CommodityaAdapter;
import com.example.a76773.myapplication.service.Releaseservice;

import java.util.HashMap;
import java.util.List;


public class main_page extends AppCompatActivity implements View.OnClickListener {
    String TABLENAME = "iteminfo";
    Intent intent;
    byte[] imagedata;
    Bitmap imagebm;
    Activity activity;
    //服务
    Releaseservice releaseservice;
    // 数据
    List<ReleaseValue> releaselist;
    //适配器实例化
    CommodityaAdapter commodityaAdapter;
    // 账户ListView
    ListView releaselistew;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


        releaselistew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //最重要获取TextView里的值
                    TextView textView =view.findViewById(R.id.id_ap);
                    String text= textView.getText().toString();

                Toast.makeText(main_page.this, text, Toast.LENGTH_SHORT).show();
                    intent = new Intent(main_page.this, Item_infoActivity.class);
                    intent.putExtra("id",text);
                    startActivity(intent);
            }
        });
    }
    private void init(){
        releaselistew =findViewById(R.id.listView);

        releaseservice = new Releaseservice(main_page.this);
        //数据添加
//     releaseservice.init();

        releaselist=releaseservice.getAccountList();

        commodityaAdapter = new CommodityaAdapter(main_page.this, 0,releaselist);

        releaselistew .setAdapter(commodityaAdapter);

        ImageView kind1 = (ImageView) findViewById(R.id.kind1);
        kind1.setOnClickListener(this);
        ImageView kind2 = (ImageView) findViewById(R.id.kind2);
        kind2.setOnClickListener(this);
        ImageView kind3 = (ImageView) findViewById(R.id.kind3);
        kind3.setOnClickListener(this);
        ImageView kind4 = (ImageView) findViewById(R.id.kind4);
        kind4.setOnClickListener(this);

        RadioButton btn1 = (RadioButton)findViewById(R.id.button_1);
        RadioButton btn2 = (RadioButton)findViewById(R.id.button_2);
        RadioButton btn3 = (RadioButton)findViewById(R.id.button_3);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.kind1:
                Intent KindIntent1 = new Intent(this,kind_page1.class);
                startActivity(KindIntent1);
                break;
            case R.id.kind2:
                Intent KindIntent2 = new Intent(this,kind_page2.class);
                startActivity(KindIntent2);
                break;
            case R.id.kind3:
                Intent KindIntent3 = new Intent(this,kind_page3.class);
                startActivity(KindIntent3);
                break;
            case R.id.kind4:
                Intent KindIntent4 = new Intent(this,kind_page4.class);
                startActivity(KindIntent4);
                break;
                //返回首页
            case R.id.button_1:
                Intent button1 = new Intent(main_page.this,main_page.class);
                startActivity(button1);
                break;
            case R.id.button_2:
                Intent button2 = new Intent(this,AddItem.class);
                startActivity(button2);
                break;
            case R.id.button_3:
                Intent button3 = new Intent(this,MyselfActivity.class);
                startActivity(button3);
                break;

        }
    }
}

