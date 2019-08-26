package com.example.a76773.myapplication.MainActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a76773.myapplication.BuValue.ReleaseValue;
import com.example.a76773.myapplication.R;
import com.example.a76773.myapplication.adapter.CommodityaAdapter;
import com.example.a76773.myapplication.service.Kindpageservice;

import java.util.List;

public class kind_page3 extends AppCompatActivity implements View.OnClickListener {
    Intent intent;
    //服务
    Kindpageservice releaseservice;
    // 数据
    List<ReleaseValue> releaselist;
    //适配器实例化
    CommodityaAdapter commodityaAdapter;
    // 账户ListView
    ListView releaselistew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kind_page3);
        RadioButton btn1 = (RadioButton)findViewById(R.id.button_1);
        RadioButton btn2 = (RadioButton)findViewById(R.id.button_2);
        RadioButton btn3 = (RadioButton)findViewById(R.id.button_3);
        TextView kind =findViewById(R.id.kinds);
        releaselistew =findViewById(R.id.kind_list1);

        releaseservice = new Kindpageservice(kind_page3.this);
        //数据添加
//     releaseservice.init();

        releaselist=releaseservice.getAccountList(kind.getText().toString());

        commodityaAdapter = new CommodityaAdapter(kind_page3.this, 0,releaselist);

        releaselistew .setAdapter(commodityaAdapter);
        releaselistew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //最重要获取TextView里的值
                TextView textView =view.findViewById(R.id.id_ap);
                String text= textView.getText().toString();


                intent = new Intent(kind_page3.this, Item_infoActivity.class);
                intent.putExtra("id",text);
                startActivity(intent);
            }
        });
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_1:
                Intent button1 = new Intent(this,main_page.class);
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
