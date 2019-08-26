package com.example.a76773.myapplication.MainActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a76773.myapplication.BuValue.RegisiterValue;
import com.example.a76773.myapplication.R;
import com.example.a76773.myapplication.service.Registerservice;

public class LoginMainActivity extends AppCompatActivity {
   public  static RegisiterValue value;

    private EditText User;
    private EditText Password;
    private Button button_login;
    private TextView first;
    private TextView toRegister;
    private RadioButton button1;
    private RadioButton button2;
    private RadioButton button3;
    protected Intent intent;
    protected static String post_userid;
    String user=null;
    String password=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        User=(EditText)findViewById(R.id.login_user);
        Password=(EditText)findViewById(R.id.login_password);
        button_login=(Button)findViewById(R.id.login);
        toRegister=(TextView)findViewById(R.id.toRegister);
        post_userid="";



        //登录验证，成功后跳转到主页
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user=User.getText().toString();
                password=Password.getText().toString();

                if(user.equals("")||user==null){
                    Toast.makeText(LoginMainActivity.this, "请输入用户账号！", Toast.LENGTH_SHORT).show();
                   return;
                }
                if(password.equals("")||password==null){
                    Toast.makeText(LoginMainActivity.this, "请输入用户密码！", Toast.LENGTH_SHORT).show();
                    return;
                }


                Registerservice registerservice= new Registerservice(LoginMainActivity.this);
                Boolean b= registerservice.checkUser_login(user,password);
                try {
                    if (b!=false){
                        value =new RegisiterValue();
                        value.setUserId(user);
                        User.setText("");
                        Password.setText("");

                    }
                }catch (Exception  e){
                    Toast.makeText(LoginMainActivity.this, "用户密码错误！", Toast.LENGTH_SHORT).show();
                }



            }

        });
        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(LoginMainActivity.this,RegisterMainActivity.class);
                startActivity(intent);
            }
        });

    }
}
