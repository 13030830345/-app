package com.example.a76773.myapplication.service;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import android.database.sqlite.SQLiteException;

import android.graphics.BitmapFactory;
import android.widget.Toast;


import com.example.a76773.myapplication.BuValue.RegisiterValue;
import com.example.a76773.myapplication.BuValue.ReleaseValue;
import com.example.a76773.myapplication.MainActivity.LoginMainActivity;
import com.example.a76773.myapplication.MainActivity.main_page;
import com.example.a76773.myapplication.MainActivity.RegisterMainActivity;
import com.example.a76773.myapplication.common.AbstractService;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.content.ContextCompat.startActivities;


public class Registerservice extends AbstractService {
    private Context context ;
    LoginMainActivity loginMainActivity;
   public RegisiterValue  releaseValue;
    private  RegisterMainActivity mainActivity;
    public Registerservice(Context context) {
        super(context);
        this.context=context;
        loginMainActivity=new LoginMainActivity();

}

    public void checkUser(String user, String password1) {
        try{

            String sql="SELECT * FROM users WHERE userId=?";
            Cursor cursor=db.rawQuery(sql,new String[]{user});
            if(cursor.getCount()>0){
                Toast.makeText(context, "学号已存在！", Toast.LENGTH_SHORT).show();
            }
            else{
                ContentValues values = new ContentValues();
                //开始组装第一条数据   //账号userId，密码passWord，姓名name，专业subject，电话phone，QQ号qq,地址address
                values.put("userId",user);
                values.put("passWord",password1);
                db.insert("users",null,values);//插入第一条数据
//                        Intent intent = new Intent(RegisterMainActivity.this,LoginMainActivity.class);
//                        startActivity(intent);
                Toast.makeText(context, "注册成功", Toast.LENGTH_SHORT).show();
            }
            cursor.close();
            db.close();
        }catch (SQLiteException e){
            Toast.makeText(context, "注册失败！", Toast.LENGTH_SHORT).show();
        }
    }

    //登入方法
    public Boolean checkUser_login(String user,String password){
        Boolean a = null;
        try{
            String sql="SELECT * FROM users WHERE userId=? and passWord=?";
            Cursor cursor=db.rawQuery(sql,new String[]{user,password});
            if(cursor.getCount()==0){
                Toast.makeText(context, "用户密码错误！", Toast.LENGTH_SHORT).show();
            }
            else{

                Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,main_page.class);
                context.startActivity(intent);
                a=true;
            }

            cursor.close();
            db.close();
        }catch (SQLiteException e){
            Toast.makeText(context, "登录失败", Toast.LENGTH_SHORT).show();
            
            a= false;
        }
        return a;
    }

    //开始组装第一条数据   //账号userId，密码passWord，姓名name，专业subject，电话phone，QQ号qq,地址address
    // 个人信息修改
    public void getAccountList( String name,String subject,String phone,String qq,String address) {

        ContentValues values = new ContentValues();

        values.put("name",  name);
        values.put("subject",subject);
        values.put("phone",phone);
        values.put("qq",qq);
        values.put("address",address);

        db.update("users", values, "userId = ?", new String[] { loginMainActivity.value.getUserId() });
        Toast.makeText(context,"资料已修改",Toast.LENGTH_LONG).show();
    }
    public List<RegisiterValue> myself( String userId) {

        List<RegisiterValue> accountList = new ArrayList<RegisiterValue>();
        //定义sql语句
        String sql="select * from users where userId=?";
        //定义cousor对象
        Cursor cursor = db.rawQuery(sql, new String[]{userId});

        while(cursor.moveToNext()) {
            //开始组装第一条数据   //账号userId，密码passWord，姓名name，专业subject，电话phone，QQ号qq,地址address
             releaseValue= new RegisiterValue();

            releaseValue.setName(cursor.getString(3));
            releaseValue.setSubject(cursor.getString(4));
            releaseValue.setPhone(cursor.getString(5));
            releaseValue.setQq(cursor.getString(6));
            releaseValue.setAddress(cursor.getString(7));


            accountList.add(releaseValue);
        }
        cursor.close();
        return accountList;
    }

}

