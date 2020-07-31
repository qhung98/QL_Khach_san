package com.example.ql_khach_san;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ql_khach_san.model.Staff;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);

        final EditText edUsername, edPassword;
        Button btnLogin;

        edUsername = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Staff staff = new Staff(edUsername.getText().toString(), edPassword.getText().toString());

                boolean boo = db.checkLogin(staff);

                if(boo){
                    Toast.makeText(getBaseContext(), "DANG NHAP THANH CONG", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getBaseContext(), Home.class));
                }
                else {
                    Toast.makeText(getBaseContext(), "SAI USERNAME HOAC PASSWORD", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button btnAddStaff = findViewById(R.id.btnAddStaff);
        btnAddStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Staff staff = new Staff("a", "a");
                db.addStaff(staff);
                Toast.makeText(getBaseContext(), "THEM NHAN VIEN THANH CONG", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
