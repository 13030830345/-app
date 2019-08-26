package com.example.a76773.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a76773.myapplication.BuValue.ReleaseValue;
import com.example.a76773.myapplication.MainActivity.Item_infoActivity;
import com.example.a76773.myapplication.R;

import java.util.List;

public class CommodityaAdapter extends ArrayAdapter<ReleaseValue> {
    Context context;
     Intent intent;
     public  ReleaseValue value;
     public View  name1;

    public CommodityaAdapter(Context context, int textViewResourceId, List<ReleaseValue> objects) {
        super(context,textViewResourceId,objects);
        this.context=context;
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        convertView = convertView != null ? convertView : LayoutInflater.from(getContext()).inflate(R.layout.commodityadapter, parent, false);

          name1  = convertView;
        //获取控件
        //  商品编号id，发布者账号userId，标题title，类别kind，内容info，价格price，图片image-->
        ImageView image_C=convertView.findViewById(R.id.image_ap);
        TextView title_C=convertView.findViewById(R.id.title_ap);
        TextView kind_C=convertView.findViewById(R.id.kind_ap);
        TextView info_C=convertView.findViewById(R.id.info_ap);
        TextView price_C=convertView.findViewById(R.id.price_ap);
        TextView id_ip=convertView.findViewById(R.id.id_ap);

        //初始化控件
        value=getItem(position);
        image_C.setImageBitmap(value.getImage());
        title_C.setText(value.getTitle());
        kind_C.setText(value.getKind());
        info_C.setText(value.getInfo());
        price_C.setText(value.getPrice());
        id_ip.setText(String.valueOf(value.getId()));

//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                intent = new Intent(context, Item_infoActivity.class);
//                intent.putExtra("id",String.valueOf(value.getId()));
//                context.startActivity(intent);
//                Toast.makeText(context,getid,Toast.LENGTH_LONG).show();
//            }
//        });

        return convertView;
    }
}
