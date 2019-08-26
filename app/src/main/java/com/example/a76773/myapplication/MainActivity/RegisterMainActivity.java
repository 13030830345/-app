package com.example.a76773.myapplication.MainActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a76773.myapplication.R;
import com.example.a76773.myapplication.common.DatabaseHelper;
import com.example.a76773.myapplication.service.Registerservice;

public class RegisterMainActivity extends AppCompatActivity  {
    //账号userId，密码passWord，姓名name，专业subject，电话phone，QQ号qq,地址address
    private EditText User;
    private EditText Password1;
    private EditText Password2;
    private Button button_register;
    private Button button_return;
    private TextView first;
    String user=null;
    String password1=null;
    String password2=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        //点击注册
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user=User.getText().toString();
                password1=Password1.getText().toString();
                password2=Password2.getText().toString();

                if(user==null||user.equals("")){
                    Toast.makeText(getApplicationContext(), "请输入用户学号！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password1==null||password1.equals("")){
                    Toast.makeText(getApplicationContext(), "请输入密码！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!password1.equals(password2)){
                    Toast.makeText(getApplicationContext(), "两次输入的密码不一致!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Registerservice registerservice= new Registerservice(RegisterMainActivity.this);
               registerservice.checkUser(user,password1);
                AlertDialog dialog = new AlertDialog.Builder(RegisterMainActivity.this).create();//创建对话框
                dialog.setIcon(R.mipmap.ic_launcher);//设置对话框icon
                dialog.setTitle("是否马上登入");//设置对话框标题
                //分别设置三个button
                dialog.setButton(DialogInterface.BUTTON_POSITIVE,"是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(RegisterMainActivity.this,LoginMainActivity.class));
                    }
                });

                dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();//关闭对话框
                    }
                });
                dialog.show();//显示对话框
            }
        });

        button_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterMainActivity.this,LoginMainActivity.class));
            }
        });
    }
    public void init(){

        User=(EditText)findViewById(R.id.user);
        Password1=(EditText)findViewById(R.id.password1);
        Password2=(EditText)findViewById(R.id.password2);
        button_register=(Button)findViewById(R.id.register);
        button_return=findViewById(R.id.toReturn);

    }
}