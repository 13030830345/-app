package com.example.a76773.myapplication.service;

import android.content.ContentValues;
import android.content.Context;

import com.example.a76773.myapplication.R;
import com.example.a76773.myapplication.common.AbstractService;

public class FaBuservice  extends AbstractService {
    Context context; //上下文
    public FaBuservice(Context context) {
        super(context);
        this.context=context;
    }
                    ///accountList.add(new ReleaseValue("cl","一个哇娃","生活用品","2002","一个哇娃",R.drawable.buy_item1 ,"13030830345"));

    //  商品编号id，发布者账号userId，标题title，类别kind，内容info，价格price，图片image
    public void getList(String userId, String title, String kind, String info , String price, byte[] image, String phone) {
        // 执行数据库插入
        ContentValues values = new ContentValues();
        values.put("userId",userId);
        values.put("title",title);
        values.put("kind", kind);
        values.put("info",info);
        values.put("price", price);
        values.put("phone",phone);
        values.put("image", image);
        db.insert("iteminfo", null, values);
    }
}
