package com.example.pregoing;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginPage extends AppCompatActivity {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    EditText edtLoginID, edtLoginPW;
    Button btnSignUp, btnLogin;
    String strid, strpw;
    private Context context;
    public static final int sub = 1001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        preferences = this.getSharedPreferences("pref", Context.MODE_PRIVATE);
        edtLoginID = findViewById(R.id.edtLoginID);
        edtLoginPW = findViewById(R.id.edtLoginPW);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin = findViewById(R.id.btnLogin);

        context = getApplicationContext();


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strid = edtLoginID.getText().toString();
                strpw = edtLoginPW.getText().toString();
                if(preferences.getString(strid, "").equals(null)) {
                    editor = preferences.edit();
                    editor.putString(strid, strpw);
                    editor.commit();
                    Toast.makeText(context, "회원가입 성공", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(context, "이미 존재하는 계정입니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strid = edtLoginID.getText().toString();
                strpw = edtLoginPW.getText().toString();
                String strCheck;
                String pwCheck = preferences.getString(strid, "");
                if(strpw.equals(pwCheck)) {
                    strCheck = "로그인 성공";
                    Intent intent = new Intent(loginPage.this, MainActivity.class);
                    startActivityForResult(intent,sub);
                }
                else {
                    strCheck = "로그인 실패";
                    edtLoginID.setText("");
                    edtLoginPW.setText("");
                }
                Toast.makeText(context, strCheck, Toast.LENGTH_SHORT).show();
            }

        });
    }
}