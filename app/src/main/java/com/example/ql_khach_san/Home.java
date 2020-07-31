package com.example.ql_khach_san;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ql_khach_san.model.Floor;
import com.example.ql_khach_san.model.Room;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    DatabaseHelper db;
    ArrayList<Floor> listFloor;
    ArrayList<Room> listRoom;
    ArrayList<String> listSpin = new ArrayList<>();
    int floor_id;
    Adapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        db = new DatabaseHelper(this);

        Button btnAddFloor, btnAddRoom, btnBookRoom;
        btnAddFloor = findViewById(R.id.btnAddFloor);
        btnAddRoom = findViewById(R.id.btnAddRoom);
        btnBookRoom = findViewById(R.id.btnBookRoom);
        listView = findViewById(R.id.listView);
        Spinner spinner = findViewById(R.id.spinner);

        btnAddFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=1;i<4;i++){
                    Floor floor = new Floor(i,0);
                    db.addFloor(floor);
                }

                Toast.makeText(getBaseContext(), "THEM TANG THANH CONG", Toast.LENGTH_SHORT).show();
            }
        });

        btnAddRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getBaseContext(), AddRoom.class), 1);
            }
        });

        listFloor = db.getFloor();
        for(int i=0; i<listFloor.size(); i++){
            listSpin.add(Integer.toString(listFloor.get(i).getName()));
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listSpin);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                floor_id = db.getFloorId(listSpin.get(i));
                listRoom = db.getRoom(floor_id);
                adapter = new Adapter(Home.this, listRoom);
                listView.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnBookRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getBaseContext(), BookRoom.class), 2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode==RESULT_OK){
            startActivity(new Intent(getBaseContext(), Home.class));
            this.finish();
        }

        if(requestCode==2 && resultCode==RESULT_OK){
            startActivity(new Intent(getBaseContext(), Home.class));
            this.finish();
        }
    }
}
