package com.example.ql_khach_san;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ql_khach_san.model.Room;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<Room> {
    Context context;
    ArrayList<Room> list;
    DatabaseHelper db;

    public Adapter(@NonNull Context context, ArrayList<Room> list) {
        super(context, R.layout.row_layout, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);

        TextView tvName, tvStatus, tvType;

        tvName = view.findViewById(R.id.tvName);
        tvStatus = view.findViewById(R.id.tvStatus);
        tvType = view.findViewById(R.id.tvType);

        tvName.setText("PHONG " + list.get(position).getName());

        if(list.get(position).getEmpty()==0)
            tvStatus.setText("TRONG");
        else
            tvStatus.setText("DANG THUE");

        if(list.get(position).getType()==0)
            tvType.setText("PHONG THUONG");
        else
            tvType.setText("PHONG VIP");

        db = new DatabaseHelper(context);

        Button btnChange = view.findViewById(R.id.btnChange);
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("TRANG THAI")
                        .setPositiveButton("BAN", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Room room = list.get(position);
                                room.setEmpty(1);
                                db.editRoom(room);
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("TRONG", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Room room = list.get(position);
                                room.setEmpty(0);
                                db.editRoom(room);
                                notifyDataSetChanged();
                            }
                        })
                        .create().show();
            }
        });

        return view;
    }
}
