package com.example.a76773.myapplication.BuValue;

import android.graphics.Bitmap;

public class ReleaseValue {
    //  商品编号id，发布者账号userId，标题title，类别kind，内容info，价格price，图片image
    private  int  id;
    private  String userId;
    private String title;
    private String kind;
    private String price;
    private String info;

    public Bitmap getImage() {
        return Image;
    }

    public void setImage(Bitmap image) {
        Image = image;
    }

    private Bitmap Image;
    private  String phone;



    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    public  ReleaseValue(){

    }



    public int getId() {
        return id;
    }

    public ReleaseValue setId(int id) {
        this.id=id;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public ReleaseValue setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ReleaseValue setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getKind() {
        return kind;
    }

    public ReleaseValue setKind(String kind) {
        this.kind = kind;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public ReleaseValue setPrice(String price) {
        this.price = price;
        return this;
    }

    public String getInfo() {
        return info;
    }

    public ReleaseValue setInfo(String info) {
        this.info = info;
        return this;
    }



    public ReleaseValue( String userId, String title, String kind, String price, String info,    Bitmap image
            ,String phone) {

        this.userId = userId;
        this.title = title;
        this.kind = kind;
        this.price = price;
        this.info = info;
        Image = image;
        this.phone=phone;
    }


}
