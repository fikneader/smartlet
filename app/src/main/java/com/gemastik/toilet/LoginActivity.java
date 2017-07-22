package com.gemastik.toilet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private Intent intent;
    private EditText usernameText, passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameText = (EditText) findViewById(R.id.username);
        passwordText = (EditText) findViewById(R.id.password);

        getSupportActionBar().hide();

        Button btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog pd = new ProgressDialog(LoginActivity.this);

                pd.setProgressStyle(R.style.CircularProgress);
                pd.setTitle("Login");
                pd.setMessage("Loading...");
                pd.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String username = usernameText.getText().toString();
                            String password = passwordText.getText().toString();
                            Thread.sleep(3000);
                            pd.dismiss();
                            if(username.equalsIgnoreCase("wawan") && password.equalsIgnoreCase("admin")){
                                intent = new Intent(LoginActivity.this, MenuActivity.class);
                                startActivity(intent);
                                finish();

                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        TextView txtDaftar = (TextView) findViewById(R.id.txtDaftar);

        txtDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
