package com.william.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ChoicePictureActivity extends Activity {

    public int[] imgs = new int[]{
            R.drawable.icon1, R.drawable.icon2, R.drawable.icon3,
            R.drawable.icon4, R.drawable.icon5, R.drawable.icon6,
            R.drawable.icon7, R.drawable.icon8, R.drawable.icon9,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_picture);

        GridView gd = findViewById(R.id.grid_picture);
        BaseAdapter baseAdapter = new BaseAdapter() {

            //获得数量
            @Override
            public int getCount() {
                return imgs.length;
            }

            //获得当前选项
            @Override
            public Object getItem(int position) {
                return position;
            }

            //获得当前选项对应的id
            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView imageView;
                if (convertView == null) {
                    imageView = new ImageView(ChoicePictureActivity.this);
                    //设置图片的宽高
                    imageView.setAdjustViewBounds(true);
                    imageView.setMaxHeight(120);
                    imageView.setMaxWidth(120);
                    imageView.setPadding(5, 5, 5, 5);
                } else
                    imageView = (ImageView) convertView;

                imageView.setImageResource(imgs[position]);
                return imageView;
            }
        };

        gd.setAdapter(baseAdapter);

        gd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = getIntent();
                Bundle bundle = new Bundle();
                bundle.putInt("imgid", imgs[position]);
                intent.putExtras(bundle);
                setResult(123, intent);
                finish();
            }
        });
    }
}
