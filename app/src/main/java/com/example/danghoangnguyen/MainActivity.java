package com.example.danghoangnguyen;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

    public class MainActivity extends AppCompatActivity {
        Food_HNguyen dbHelper;

        EditText edtUsername_HNguyen, edtPassword_HNguyen;
        Button btnLogin_HNguyen;
        TextView txtRegister_HNguyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_HNguyen), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbHelper = new Food_HNguyen(this);
        Log.d("DB_PATH", getDatabasePath("Food_HNguyen.db").getAbsolutePath());
        dbHelper.getWritableDatabase();

        edtUsername_HNguyen = findViewById(R.id.edtUsername_HNguyen);
        edtPassword_HNguyen = findViewById(R.id.edtPassword_HNguyen);
        btnLogin_HNguyen = findViewById(R.id.btnLogin_HNguyen);
        txtRegister_HNguyen = findViewById(R.id.txtRegister_HNguyen);

        btnLogin_HNguyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername_HNguyen.getText().toString().trim();
                String password = edtPassword_HNguyen.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else if (checkUser_HNguyen(username, password)) {
                    Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, Home_HNguyen.class));
                } else {
                    Toast.makeText(MainActivity.this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txtRegister_HNguyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Register_HNguyen.class));
            }
        });
    }

    private boolean checkUser_HNguyen(String username, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM User_HNguyen WHERE username = ? AND password = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{username, password});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
}
