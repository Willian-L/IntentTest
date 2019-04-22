package com.william.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class IntentSecondActivity extends AppCompatActivity {

    TextView txIntent;
    private String text;
    private String radio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_second);

        txIntent = findViewById(R.id.tv_Intent);

        //获得Intent对象，并且获取Bundle里面的数据
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();

        //按键值的方式取出Bundle中的数据
        text = bd.getCharSequence("text").toString();
        radio = bd.getCharSequence("rad").toString();
        txIntent.setText("text is " + text + "   radio is " + radio);
    }
}
