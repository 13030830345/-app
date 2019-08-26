package com.example.a76773.myapplication.MainActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.a76773.myapplication.BuValue.RegisiterValue;
import com.example.a76773.myapplication.R;
import com.example.a76773.myapplication.service.Registerservice;

public class MyselfActivity extends AppCompatActivity {
    private TextView userid;
    private EditText username;
    private EditText usersubject;
    private EditText userphone;
    private EditText userqq;
    private EditText useraddress;
    private Button usersave;
    private Button back;
    protected Intent intent;
    Registerservice registerservice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myself);
        registerservice =new Registerservice(MyselfActivity.this);
        userid=(TextView)findViewById(R.id.showUser);
        username=(EditText)findViewById(R.id.name);
        usersubject=(EditText)findViewById(R.id.subject);
        userphone=(EditText)findViewById(R.id.phone);
        userqq=(EditText)findViewById(R.id.qq);
        useraddress=(EditText)findViewById(R.id.address);
        usersave=(Button)findViewById(R.id.save);
        back=(Button)findViewById(R.id.back);

        LoginMainActivity loginMainActivity=new LoginMainActivity();
        registerservice.myself(loginMainActivity.value.getUserId());
        userid.setText(  loginMainActivity.value.getUserId());
        username.setText(registerservice.releaseValue.getName());
        usersubject.setText(registerservice.releaseValue.getSubject());
        userphone.setText(registerservice.releaseValue.getPhone());
        userqq.setText(registerservice.releaseValue.getQq());
        useraddress.setText(registerservice.releaseValue.getAddress());

        usersave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String post_name=username.getText().toString();
                String   post_subject=usersubject.getText().toString();
                String   post_phone=userphone.getText().toString();
                String  post_qq=userqq.getText().toString();
                String  post_address=useraddress.getText().toString();
                registerservice.getAccountList(post_name,post_subject,post_phone,post_qq,post_address);
                startActivity(new Intent(MyselfActivity.this,MyselfActivity.class));

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyselfActivity.this,main_page.class));
            }
        });
    }
}
