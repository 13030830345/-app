package com.example.a76773.myapplication.service;

import android.accounts.Account;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import com.example.a76773.myapplication.BuValue.ReleaseValue;
import com.example.a76773.myapplication.R;
import com.example.a76773.myapplication.common.AbstractService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Releaseservice extends AbstractService {
    Context context;
    ReleaseValue  releaseValue;
    byte[] imagedata;
    Bitmap imagebm;
    public Releaseservice(Context context) {
        super(context);
        this.context=context;
    }

    // 获取帐号信息
    public List<ReleaseValue> getAccountList() {

        List<ReleaseValue> accountList = new ArrayList<ReleaseValue>();

        Cursor cursor = db.rawQuery("select * from iteminfo", null);

        while(cursor.moveToNext()) {
            //  商品编号id，发布者账号userId，标题title，类别kind，内容info，价格price，图片image
            ReleaseValue  releaseValue= new ReleaseValue();
            releaseValue.setId(cursor.getInt(0));
            releaseValue.setUserId(cursor.getString(1));
            releaseValue.setTitle(cursor.getString(2));
            releaseValue.setKind(cursor.getString(3));
            releaseValue.setInfo(cursor.getString(4));
            releaseValue.setPrice(cursor.getString(5));
            imagedata = cursor.getBlob(6);
            imagebm = BitmapFactory.decodeByteArray(imagedata, 0, imagedata.length);
            releaseValue.setImage(imagebm);
            releaseValue.setPhone(cursor.getString(7));
            accountList.add(releaseValue);
        }
        cursor.close();
        return accountList;
    }
    // 个人发布信息
    public List<ReleaseValue> getfabu( String userId) {

        List<ReleaseValue> accountList = new ArrayList<ReleaseValue>();
        //定义sql语句
        String sql="select * from iteminfo where userId=?";
        //定义cousor对象
        Cursor cursor = db.rawQuery(sql, new String[]{userId});

        while(cursor.moveToNext()) {
            //  商品编号id，发布者账号userId，标题title，类别kind，内容info，价格price，图片image
            ReleaseValue  releaseValue= new ReleaseValue();
            releaseValue.setId(cursor.getInt(0));
            releaseValue.setUserId(cursor.getString(1));
            releaseValue.setTitle(cursor.getString(2));
            releaseValue.setKind(cursor.getString(3));
            releaseValue.setInfo(cursor.getString(4));
            releaseValue.setPrice(cursor.getString(5));
            imagedata = cursor.getBlob(6);
            imagebm = BitmapFactory.decodeByteArray(imagedata, 0, imagedata.length);
            releaseValue.setImage(imagebm);
            releaseValue.setPhone(cursor.getString(7));
            accountList.add(releaseValue);
        }
        cursor.close();
        return accountList;
    }
    //删除商品
    public  void  delete_item(int id){
        db.execSQL("DELETE FROM iteminfo WHERE id = '"+id+"';");
        Toast.makeText(context,"删除成功",Toast.LENGTH_LONG).show();
    }

}
