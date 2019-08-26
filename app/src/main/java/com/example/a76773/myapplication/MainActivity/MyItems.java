package com.example.a76773.myapplication.MainActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AlphabetIndexer;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a76773.myapplication.BuValue.ReleaseValue;
import com.example.a76773.myapplication.R;
import com.example.a76773.myapplication.adapter.CommodityaAdapter;
import com.example.a76773.myapplication.service.Releaseservice;

import java.util.List;

public class MyItems extends AppCompatActivity  implements View.OnClickListener  {
    //服务
    Releaseservice releaseservice;
    // 数据
    List<ReleaseValue> releaselist;
    //适配器实例化
  CommodityaAdapter commodityaAdapter;
    // 账户ListView
    ListView releaselistew;
    LoginMainActivity loginMainActivity;
    //按钮
    Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_items);

        loginMainActivity =new LoginMainActivity();

        String userId=loginMainActivity.value.getUserId();

        releaselistew =findViewById(R.id.show_fabu);

        releaseservice = new Releaseservice(MyItems.this);

        releaselist=releaseservice.getfabu(userId);

        commodityaAdapter = new CommodityaAdapter(MyItems.this, 0,releaselist);

        releaselistew .setAdapter(commodityaAdapter);

        button1=findViewById(R.id.but1);//首页
        button2=findViewById(R.id.but2);//刷新
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);



        //商品长时间点击删除
        releaselistew.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //最重要获取TextView里的值
                TextView textView =view.findViewById(R.id.id_ap);
                final String text= textView.getText().toString();
                AlertDialog dialog = new AlertDialog.Builder(MyItems.this).setTitle("警告")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setPositiveButton("商品下架", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                        releaseservice.delete_item(Integer.parseInt(text));
                                       // Toast.makeText(MyItems.this, "11", Toast.LENGTH_SHORT).show();
                            }
                        }).show();

                dialog.setCanceledOnTouchOutside(false);


                return true;
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.but1:
                startActivity(new Intent(MyItems.this,main_page.class));
                break;
            case R.id.but2:
                startActivity(new Intent(MyItems.this,MyItems.class));
                break;
        }

    }


}
