package com.example.a76773.myapplication.common;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        账号userId，密码passWord，姓名name，专业subject，电话phone，QQ号qq,地址address
            db.execSQL("create table users(" +
                    "id integer primary key autoincrement," +
                    "userId text," +
                    "passWord text," +
                    "name text," +
                    "subject text," +
                    "phone text," +
                    "qq text," +
                    "address text)"
            );
      //  商品编号id，发布者账号userId，标题title，类别kind，内容info，价格price，图片image,电话 phone
        db.execSQL("create table iteminfo(" +
                "id integer primary key autoincrement," +
                "userId text," +
                "title text," +
                "kind text," +
                "info text," +
                "price text," +
                "image blob," +
                "phone text)");
        //评论者账号userId，评论商品编号itemId，评论内容comment，评论时间time
        db.execSQL("create table comments(" +
                "id integer primary key autoincrement," +
                "userId text," +
                "itemId text," +
                "comment text," +
                "time text)");
        Toast.makeText(context, "数据库创建成功", Toast.LENGTH_SHORT).show();
}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
