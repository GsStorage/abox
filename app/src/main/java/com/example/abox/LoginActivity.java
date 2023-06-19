package com.example.abox;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sp;

    private EditText etUsername;
    private EditText etPassword;
    private CheckBox cbRemeberpwd;
    private Button btnLogin;
    private Button btnRegister;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sp = getSharedPreferences("account", Context.MODE_PRIVATE);


        initView();

        //第二次打开的时候从SP获取数据进行画面同步
        boolean remeberpwd = sp.getBoolean("remeberpwd",false);  // 如果获取是空，就返回默认值
        //记住密码
        if(remeberpwd){
            //获取sp里面的name和pwd 并保存到EditText
            String username = sp.getString("username","");
            String password = sp.getString("password","");

            etUsername.setText(username);
            etPassword.setText(password);
            cbRemeberpwd.setChecked(true);
        }

    }

    //初始化
    private void initView() {
        //找到控件
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_Register);
        cbRemeberpwd = findViewById(R.id.cb_remeberpwd);



        btnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, Register.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                SharedPreferences sp = getSharedPreferences("account", Context.MODE_PRIVATE);
                if(username.equals(sp.getString("username", "默认值")) && password.equals(sp.getString("password", "默认值"))){
                    //记录密码 打勾没有
                    if(cbRemeberpwd.isChecked()){
                        //用户名和密码都需要保存  同时记录密码的状态也要保存
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("username",username);
                        editor.putString("password",password);
                        editor.putBoolean("remeberpwd",true);
                        editor.apply();
                    }
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);


                }else {
                    // 登录失败，弹出提示信息
                    Toast.makeText(LoginActivity.this, "用户名或者密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}