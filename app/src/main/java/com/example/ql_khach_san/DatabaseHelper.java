package com.example.ql_khach_san;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.ql_khach_san.model.Booking;
import com.example.ql_khach_san.model.Floor;
import com.example.ql_khach_san.model.Room;
import com.example.ql_khach_san.model.Staff;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    Context context;

    private static final String DB_NAME = "QLKS";
    private static final String STAFF_TABLE = "staff";
    private static final String FLOOR_TABLE = "floor";
    private static final String ROOM_TABLE = "room";
    private static final String BOOKING_TABLE = "booking";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query1 = "CREATE TABLE " + STAFF_TABLE + "(id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)";
        String query2 = "CREATE TABLE " + FLOOR_TABLE + "(id INTEGER PRIMARY KEY AUTOINCREMENT, name INTEGER, rooms INTEGER)";
        String query3 = "CREATE TABLE " + ROOM_TABLE + "(id INTEGER PRIMARY KEY AUTOINCREMENT, name INTEGER, type INTEGER, empty INTEGER, floor_id INTEGER)";
        String query4 = "CREATE TABLE " + BOOKING_TABLE + "(id INTEGER PRIMARY KEY AUTOINCREMENT, customerName TEXT, timeIn TEXT, timeOut TEXT, type INTEGER, SUM INTEGER)";

        sqLiteDatabase.execSQL(query1);
        sqLiteDatabase.execSQL(query2);
        sqLiteDatabase.execSQL(query3);
        sqLiteDatabase.execSQL(query4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + STAFF_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FLOOR_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + STAFF_TABLE);
    }

    public void addStaff(Staff staff){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("username", staff.getUsername());
        contentValues.put("password", staff.getPassword());

        db.insert(STAFF_TABLE, null, contentValues);
    }

    public boolean checkLogin(Staff staff){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + STAFF_TABLE + " WHERE username=? AND password=?", new String[]{staff.getUsername(), staff.getPassword()});

        if(cursor.getCount() > 0)
            return true;

        return false;
    }

    public void addFloor(Floor floor){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", Integer.toString(floor.getName()));
        contentValues.put("rooms", Integer.toString(floor.getRooms()));

        db.insert(FLOOR_TABLE, null, contentValues);
    }

    public ArrayList<Floor> getFloor(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Floor> list = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + FLOOR_TABLE, null);
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            Floor floor = new Floor(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2));
            list.add(floor);
        }

        cursor.close();
        return list;
    }

    public int getFloorId(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + FLOOR_TABLE + " WHERE name=?", new String[]{name});

        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    public void addRoom(Room room){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", Integer.toString(room.getName()));
        contentValues.put("type", Integer.toString(room.getType()));
        contentValues.put("empty", Integer.toString(room.getEmpty()));
        contentValues.put("floor_id", Integer.toString(room.getFloor_id()));

        db.insert(ROOM_TABLE, null, contentValues);
    }

    public ArrayList<Room> getRoom(int floor_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Room> list = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + ROOM_TABLE + " WHERE floor_id=?", new String[]{String.valueOf(floor_id)});
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            Room room = new Room(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4));
            list.add(room);
        }

        cursor.close();
        return list;
    }

    public void editRoom(Room room){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", String.valueOf(room.getName()));
        contentValues.put("type", String.valueOf(room.getType()));
        contentValues.put("empty", String.valueOf(room.getType()));
        contentValues.put("floor_id", String.valueOf(room.getFloor_id()));

        db.update(ROOM_TABLE, contentValues, "id=?",new String[]{String.valueOf(room.getId())});
    }

    public void addBooking(Booking booking){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("customerName", booking.getCustomerName());
        contentValues.put("timeIn", booking.getTimeIn());
        contentValues.put("timeOut", booking.getTimeOut());
        contentValues.put("type", Integer.toString(booking.getType()));
        contentValues.put("sum", Integer.toString(booking.getSum()));

        db.insert(BOOKING_TABLE, null, contentValues);
    }


}
