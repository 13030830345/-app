package com.example.a76773.myapplication.BuValue;

public class CommentsValue {
    //评论者账号userId，评论商品编号itemId，评论内容comment，评论时间time 数据库Id;
    public  CommentsValue(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id;
    String userId;
    String itemId;
    String comment;
    String time;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



    public CommentsValue(String userId, String itemId, String comment, String time) {
        this.userId = userId;
        this.itemId = itemId;
        this.comment = comment;
        this.time = time;
    }


}
