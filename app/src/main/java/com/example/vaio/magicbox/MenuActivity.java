package com.example.vaio.magicbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by vaio on 9/20/2016.
 */
public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String NAME = "name";
    private EditText edtInput;
    private Button btnStart;
    private TextView txtvWinLose;
    private static int REQUEST_CODE = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        init();

    }

    private void init() {
        edtInput = (EditText) findViewById(R.id.activity_menu_edt_input);
        btnStart = (Button) findViewById(R.id.activity_menu_btn_start);
        txtvWinLose = (TextView) findViewById(R.id.activity_menu_txtv_win_lose);
        btnStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String string = edtInput.getText().toString();

        if (string.isEmpty()) {
            Toast.makeText(this, "Please enter the name !", Toast.LENGTH_SHORT).show();
        } else {
            switch (v.getId()) {
                case R.id.activity_menu_btn_start:
                    Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                    intent.putExtra(NAME, string);
                    startActivityForResult(intent, REQUEST_CODE);
                    break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_CODE == requestCode) {
            if (resultCode == RESULT_OK) {
                String win = data.getExtras().getString(MainActivity.RESULT);
                txtvWinLose.setText(win);
            }
        }
    }
}
