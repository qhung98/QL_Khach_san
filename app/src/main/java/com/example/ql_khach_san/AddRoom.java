package com.example.ql_khach_san;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ql_khach_san.model.Floor;
import com.example.ql_khach_san.model.Room;

import java.util.ArrayList;

public class AddRoom extends AppCompatActivity {
    DatabaseHelper db;
    int floor;
    int type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_room);

        final EditText edName;
        Spinner spinnerType, spinnerFloor;
        Button btnAdd;

        spinnerFloor = findViewById(R.id.spinnerFloor);
        edName = findViewById(R.id.edName);
        spinnerType = findViewById(R.id.spinnerType);
        btnAdd =  findViewById(R.id.btnAdd);

        db = new DatabaseHelper(this);

        final ArrayList<String> listName = new ArrayList<>();
        ArrayList<Floor> listFloor = db.getFloor();
        for(int i=0; i<listFloor.size(); i++){
            listName.add(Integer.toString(listFloor.get(i).getName()));
        }

        ArrayAdapter floorAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listName);
        spinnerFloor.setAdapter(floorAdapter);

        spinnerFloor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String name = listName.get(i);
                floor = db.getFloorId(name);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        String[] listType = {"PHONG THUONG", "PHONG VIP"};
        ArrayAdapter typeAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listType);
        spinnerType.setAdapter(typeAdapter);

        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0)
                    type=0;
                else
                    type=1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Room room = new Room(Integer.parseInt(edName.getText().toString()), type, 0, floor);
                db.addRoom(room);
                Toast.makeText(getBaseContext(), "THEM PHONG THANH CONG", Toast.LENGTH_SHORT).show();

                setResult(RESULT_OK, null);
                finish();
            }
        });

    }
}
