package com.example.vaio.magicbox;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String RESULT = "result";
    private TextView txtvPlayerName;
    private TextView txtvGameName;
    private TextView txtvTimes;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private int score=0;
    private int times=5;
    private boolean check=true;
    private boolean checkWin;
    private Random random=new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {
        txtvPlayerName=(TextView)findViewById(R.id.activity_main_player_name);
        txtvGameName=(TextView)findViewById(R.id.activity_main_game_name);
        txtvTimes=(TextView)findViewById(R.id.activity_main_times);
        btn1=(Button)findViewById(R.id.activity_main_btn_1);
        btn1.setOnClickListener(this);
        btn2=(Button)findViewById(R.id.activity_main_btn_2);
        btn2.setOnClickListener(this);
        btn3=(Button)findViewById(R.id.activity_main_btn_3);
        btn3.setOnClickListener(this);
        btn4=(Button)findViewById(R.id.activity_main_btn_4);
        btn4.setOnClickListener(this);
        btn5=(Button)findViewById(R.id.activity_main_btn_5);
        btn5.setOnClickListener(this);
        btn6=(Button)findViewById(R.id.activity_main_btn_6);
        btn6.setOnClickListener(this);
        btn7=(Button)findViewById(R.id.activity_main_btn_7);
        btn7.setOnClickListener(this);
        btn8=(Button)findViewById(R.id.activity_main_btn_8);
        btn8.setOnClickListener(this);
        btn9=(Button)findViewById(R.id.activity_main_btn_9);
        btn9.setOnClickListener(this);
        Intent intent=getIntent();
        String name= intent.getExtras().getString(MenuActivity.NAME);
        txtvPlayerName.setText("Player name : "+name);
    }

    @Override
    public void onClick(View v) {
        if(times==0){
            check=false;
        }
        if(times>0&&check){
            times--;
            txtvTimes.setText("Times : "+Integer.toString(times));
            int tmp= random.nextInt(10);
            score+=tmp;
            v.setBackgroundColor(Color.YELLOW);
            v.setEnabled(false);
            Button btn=(Button)v;
            btn.setText(Integer.toString(tmp));
            btn.setTextSize(20);
            btn.setTextColor(Color.BLACK);
        }
        if(times==0&&score<25){
            Toast.makeText(this,"You lose !",Toast.LENGTH_SHORT).show();
        }else {
            if(score>=25){
                Toast.makeText(this,"You win !",Toast.LENGTH_SHORT).show();
                check=false;
                checkWin=true;
            }
        }
    }

    @Override
    public void onBackPressed() {
       if((times==0)||(times!=0&&checkWin)){
           Intent intent=new Intent();
           if(checkWin){
               intent.putExtra(RESULT,"You win !");
           }else {
               intent.putExtra(RESULT,"You lose !");
           }
           setResult(RESULT_OK,intent);
           super.onBackPressed();
       }else{
           Toast.makeText(this,"Please play during game !",Toast.LENGTH_SHORT).show();
       }
    }
}
