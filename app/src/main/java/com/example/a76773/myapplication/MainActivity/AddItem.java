package com.example.a76773.myapplication.MainActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.a76773.myapplication.BuValue.ReleaseValue;
import com.example.a76773.myapplication.R;
import com.example.a76773.myapplication.common.DatabaseHelper;
import com.example.a76773.myapplication.service.FaBuservice;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;

public class AddItem extends AppCompatActivity  implements View.OnClickListener {
    private static final byte REQUEST_SYSTEM_PIC = 10;
    private DatabaseHelper dbHelper;
    private Spinner sp;
    private ImageButton imageButton;
    private byte[] image;
    private FaBuservice faBuservice;
    String imagePath;
    String kinds;
    Bitmap bitmap;
    Uri selectedImage;
    LoginMainActivity activity;
    Button wodefabu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss ");
        String[] ctype = new String[]{"生活用品", "学习用品", "电子产品", "体育用品"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ctype);  //创建一个数组适配器
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     //设置下拉列表框的下拉选项样式
        Spinner spinner = (Spinner) super.findViewById(R.id.m1_style);
        spinner.setAdapter(adapter);
        sp = (Spinner) findViewById(R.id.m1_style);

        //选择值
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             kinds  = String.valueOf(sp.getSelectedItem());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        imageButton=findViewById(R.id.m1_image);
        imageButton.setOnClickListener(this);
        Button but1 = (Button)findViewById(R.id.but1_m1);
        but1.setOnClickListener(this);
        Button but2 = (Button)findViewById(R.id.but2_m1);
        but2.setOnClickListener(this);
        Button fabu=(Button)findViewById(R.id.fabu);
        fabu.setOnClickListener(this);
        wodefabu=findViewById(R.id.but2_m1);
        wodefabu.setOnClickListener(this);
        Button button=findViewById(R.id.but1_m1);
        button.setOnClickListener(this);

    }

    //加载图片
    private void showImage(String imaePath) {


        Bitmap bm = BitmapFactory.decodeFile(imaePath);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        image =  baos.toByteArray();
        imageButton.setImageBitmap(bm);
    }
    public byte[] getImageFormBundle() {
        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        byte[] bytes = data.getByteArray("bytes");
        return bytes;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,  int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取图片路径
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            imagePath= c.getString(columnIndex);
            showImage(imagePath);
            c.close();
        }
    }
    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.m1_image:
                //发现没有权限，调用requestPermissions方法向用户申请权限，requestPermissions接收三个参数，第一个是context，第二个是一个String数组，我们把要申请的权限
                //名放在数组中即可，第三个是请求码，只要是唯一值就行
                if (ContextCompat.checkSelfPermission(AddItem.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(AddItem.this, new
                            String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    Toast.makeText(AddItem.this,"应许",Toast.LENGTH_LONG).show();
                } else {
                    //打开系统相册
                    Intent intent = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 1);
                }
                break;
                //我的发布
            case R.id.but1_m1:
                startActivity(new Intent(AddItem.this,main_page.class));
                break;
            case R.id.but2_m1:
                startActivity(new Intent(AddItem.this,MyItems.class));
                break;
            case R.id.fabu:
                EditText title=(EditText)findViewById(R.id.m1_title);
                EditText price=(EditText)findViewById(R.id.m1_price);
                EditText phone=(EditText)findViewById(R.id.m1_phone);
                EditText nr=(EditText)findViewById(R.id.m1_nr);
                faBuservice =new FaBuservice(AddItem.this);
                activity =new LoginMainActivity();

                String userId=activity.value.getUserId();

                faBuservice.getList(userId,title.getText().toString(),kinds,
                       price.getText().toString(), nr.getText().toString()
                        ,image,phone.getText().toString());
//
            Toast.makeText(AddItem.this,"发布成功",Toast.LENGTH_LONG).show();
            startActivity(new Intent(AddItem.this,MyItems.class));
                break;

        }
    }

}

