package com.example.abox;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.BreakIterator;

public class Register extends AppCompatActivity {

    private Button btnRegister;
    private EditText registerUsername;
    private EditText registerPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegister = findViewById(R.id.btn_register);
        registerUsername = findViewById(R.id.register_username);
        registerPassword = findViewById(R.id.register_password);
        btnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String username = registerUsername.getText().toString().trim();
                String password = registerPassword.getText().toString().trim();
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    // 登录失败，弹出提示信息
                    Toast.makeText(Register.this, "用户名或者密码为空", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences sp = getSharedPreferences("account", Context.MODE_PRIVATE);
                    sp.edit().putString("username",username).apply(); //apply才会写到xml配置文件里面去
                    sp.edit().putString("password",password).apply(); //apply才会写到xml配置文件里面去

                }
                Intent intent = new Intent(Register.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }

    //保存到SP
//    public void saveToSP(View view) {
//        SharedPreferences sp = getSharedPreferences("SPGSNAME", Context.MODE_PRIVATE);
//        sp.edit().putString("GSKey","111").apply(); //applyy才会写到xml配置文件里面去
//
//    }
//
//    //获取SP
//    public void getSpData(View view) {
//        SharedPreferences sp = getSharedPreferences("SPGSNAME", Context.MODE_PRIVATE);
//        String value = sp.getString("GSKey", "默认值");//假设GSKey空 那么就会使用默认值
//
//        Toast.makeText(this, "" + value, Toast.LENGTH_SHORT).show();
//
//    }


}