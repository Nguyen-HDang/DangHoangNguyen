package com.example.danghoangnguyen;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class Register_HNguyen extends AppCompatActivity {

    EditText username_HNguyen, password_HNguyen, repassword_HNguyen;
    Button dangki_HNguyen;
    TextView login_HNguyen;
    Food_HNguyen dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerhoangnguyen);

        username_HNguyen = findViewById(R.id.username_HNguyen);
        password_HNguyen = findViewById(R.id.password_HNguyen);
        repassword_HNguyen = findViewById(R.id.repassword_HNguyen);
        dangki_HNguyen = findViewById(R.id.dangki_HNguyen);
        login_HNguyen = findViewById(R.id.login_HNguyen);

        dbHelper = new Food_HNguyen(this);

        dangki_HNguyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username_HNguyen.getText().toString().trim();
                String pass = password_HNguyen.getText().toString().trim();
                String repass = repassword_HNguyen.getText().toString().trim();

                if (user.isEmpty() || pass.isEmpty() || repass.isEmpty()) {
                    Toast.makeText(Register_HNguyen.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else if (!pass.equals(repass)) {
                    Toast.makeText(Register_HNguyen.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                } else {
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("username", user);
                    values.put("password", pass);

                    long result = db.insert("User_HNguyen", null, values);
                    if (result != -1) {
                        Toast.makeText(Register_HNguyen.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Register_HNguyen.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(Register_HNguyen.this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        login_HNguyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register_HNguyen.this, MainActivity.class));
                finish();
            }
        });
    }
}
