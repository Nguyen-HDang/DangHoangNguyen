package com.example.danghoangnguyen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Food_HNguyen extends SQLiteOpenHelper {

    private static final String DB_NAME = "Food_HNguyen.db";
    private static final int DB_VERSION = 2;
    public Food_HNguyen(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String userTable = "CREATE TABLE User_HNguyen (" +
                "UserID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Name TEXT, " +
                "Gender TEXT, " +
                "Date_of_birth TEXT, " +
                "Phone TEXT, " +
                "Username TEXT UNIQUE, " +
                "Password TEXT" +
                ");";

        String restaurantTable = "CREATE TABLE Restaurant_HNguyen (" +
                "ResID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Name TEXT, " +
                "Address TEXT, " +
                "Phone TEXT, " +
                "Image TEXT" +
                ");";

        String foodTable = "CREATE TABLE Food_HNguyen (" +
                "FoodID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Name TEXT, " +
                "Type TEXT, " +
                "Description TEXT, " +
                "Image TEXT, " +
                "ResID INTEGER, " +
                "FOREIGN KEY (ResID) REFERENCES Restaurant_HNguyen(ResID)" +
                ");";

        String orderTable = "CREATE TABLE Order_HNguyen (" +
                "OrderID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Address TEXT, " +
                "Date_order TEXT, " +
                "Total_value REAL, " +
                "Status TEXT, " +
                "UserID INTEGER, " +
                "FOREIGN KEY (UserID) REFERENCES User_HNguyen(UserID)" +
                ");";

        String orderDetailTable = "CREATE TABLE OrderDetail_HNguyen (" +
                "OrderID INTEGER, " +
                "FoodID INTEGER, " +
                "Size TEXT, " +
                "Quantity INTEGER, " +
                "PRIMARY KEY (OrderID, FoodID), " +
                "FOREIGN KEY (OrderID) REFERENCES Order_HNguyen(OrderID), " +
                "FOREIGN KEY (FoodID) REFERENCES Food_HNguyen(FoodID)" +
                ");";

        db.execSQL(userTable);
        db.execSQL(restaurantTable);
        db.execSQL(foodTable);
        db.execSQL(orderTable);
        db.execSQL(orderDetailTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS OrderDetail_HNguyen");
        db.execSQL("DROP TABLE IF EXISTS Order_HNguyen");
        db.execSQL("DROP TABLE IF EXISTS Food_HNguyen");
        db.execSQL("DROP TABLE IF EXISTS Restaurant_HNguyen");
        db.execSQL("DROP TABLE IF EXISTS User_HNguyen");

        onCreate(db);
    }
}
