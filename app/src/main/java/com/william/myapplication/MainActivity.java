package com.william.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {

    Button btnDial, btnSendto, btnView, btnMap, btnAudio, btnCapture, btnIntent, btnPicture;
    EditText edtIntent;
    RadioGroup radIntent;

    //退出标识
    private static boolean isExit = false;
    Handler mhandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        btnDial = findViewById(R.id.btn_Dial);
        btnSendto = findViewById(R.id.btn_Sendto);
        btnView = findViewById(R.id.btn_View);
        btnMap = findViewById(R.id.btn_Map);
        btnAudio = findViewById(R.id.btn_Audio);
        btnCapture = findViewById(R.id.btn_Capture);

        btnIntent = findViewById(R.id.btn_Intent);
        edtIntent = findViewById(R.id.edt_Intent);
        radIntent = findViewById(R.id.radioGroup);

        btnPicture = findViewById(R.id.btn_Choice);

        btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("tel:10086");
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
        });

        btnSendto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("smsto:+8618318234529");
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                intent.putExtra("sms_body", "10010");
                startActivity(intent);
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.baidu.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("geo:39.9,116.3");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
               startActivityForResult(intent, 0);
//                Bundle bd = intent.getExtras();
//                Bitmap bitmap = (Bitmap) bd.get("data");
            }
        });

        btnIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text, rad = "";
                Intent it = new Intent(MainActivity.this, IntentSecondActivity.class);
                text = edtIntent.getText().toString();

                //遍历RadioGroup找出被选中的单选按钮
                for (int i = 0; i < radIntent.getChildCount(); i++) {
                    RadioButton radioButton = (RadioButton) radIntent.getChildAt(i);
                    if (radioButton.isChecked()) {
                        rad = radioButton.getText().toString();
                        break;
                    }
                }

                //新建Bundle对象,并把数据写入
                Bundle bd = new Bundle();
                bd.putCharSequence("text", text);
                bd.putCharSequence("rad", rad);

                //将数据包Bundle绑定到Intent上
                it.putExtras(bd);
                startActivity(it);

                //关闭FirstActivity
//                finish();
            }
        });

        btnPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChoicePictureActivity.class);
                startActivityForResult(intent,123);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123 && resultCode == 123){
            Bundle bundle = data.getExtras();
            int imgid = bundle.getInt("imgid");
            ImageView img = findViewById(R.id.imageView);
            img.setImageResource(imgid);
        }
    }

    //双击退出程序
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isExit) {
                isExit = true;
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                //利用handle延迟发送更改状态信息
                mhandle.sendEmptyMessageDelayed(0, 2000);
            } else {
                System.exit(0);
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
