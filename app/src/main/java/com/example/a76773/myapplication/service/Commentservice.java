package com.example.a76773.myapplication.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.a76773.myapplication.BuValue.CommentsValue;
import com.example.a76773.myapplication.BuValue.ReleaseValue;
import com.example.a76773.myapplication.common.AbstractService;

import java.util.ArrayList;
import java.util.List;

public class Commentservice extends AbstractService {
    String TABLENAME = "iteminfo";
    byte[] imagedata;
    Bitmap imagebm;
    Context context; //上下文
    public ReleaseValue  releaseValue;
    public Commentservice(Context context) {
        super(context);
        this.context=context;
    }
    public void init(String id){
        //取得启动该Activity的Intent对象
        String sql="SELECT * FROM iteminfo WHERE id=?";
        Cursor cursor=db.rawQuery(sql,new String[]{id});
        List<ReleaseValue> accountList = new ArrayList<ReleaseValue>();
        while(cursor.moveToNext()) {
            //  商品编号id，发布者账号userId，标题title，类别kind，内容info，价格price，图片image
             releaseValue= new ReleaseValue();
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

    }

    public void getList(String userId, String itemId,String comment ,String time) {
        // 执行数据库插入
            ContentValues values = new ContentValues();
            values.put("userId",userId);
            values.put("itemId", itemId );
            values.put("comment",comment );
            values.put("time", time);
            db.insert("comments", null, values);
        }

    // 评论信息
    public List<CommentsValue> getcommentList(String itemId) {
        //评论者账号userId，评论商品编号itemId，评论内容comment，评论时间time
        List<CommentsValue> accountList = new ArrayList<CommentsValue>();
        String sql="SELECT * FROM comments WHERE itemId=?";
        Cursor cursor=db.rawQuery(sql,new String[]{itemId});
        while(cursor.moveToNext()) {
            //  商品编号id，发布者账号userId，标题title，类别kind，内容info，价格price，图片image
            CommentsValue releaseValue= new CommentsValue();
            releaseValue.setId(cursor.getInt(0));
            releaseValue.setUserId(cursor.getString(1));
            releaseValue.setItemId(cursor.getString(2));
            releaseValue.setComment(cursor.getString(3));
            releaseValue.setTime(cursor.getString(4));
            accountList.add(releaseValue);
        }
        cursor.close();
        return accountList;
    }
}
