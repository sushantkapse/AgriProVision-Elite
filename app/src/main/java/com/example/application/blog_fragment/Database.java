package com.example.application.blog_fragment;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.application.Model;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    Context context;
    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] imageInBytes;
    public Database(@Nullable Context context) {
        super(context, "Application", null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE blogDatabase (" +
                "u_id INTEGER PRIMARY KEY AUTOINCREMENT," +

                "title TEXT ," +
                "description TEXT," +
                "author TEXT," +
                "date TEXT," +
                "img BLOB," +
                "share_count INTEGER)" );

    }

    public  boolean insertBlogData (Model model){

        SQLiteDatabase db =this.getWritableDatabase();
        Bitmap imageToStoreBitmap = model.getImg();

        byteArrayOutputStream = new ByteArrayOutputStream();
        imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        imageInBytes = byteArrayOutputStream.toByteArray();


        ContentValues cv =new ContentValues();
        cv.put("u_id",model.getU_id());
        cv.put("title",model.getTitle());
        cv.put("description",model.getDescription());
        cv.put("author",model.getAuthor());
        cv.put("date", model.getDate());
        cv.put("share_count",model.getShare_count());
        cv.put("img" , imageInBytes);
        long res=db.insert("blogDatabase",null,cv);
        if(res==-1){
            return false;
        }
        else {

            return true;
        }


    }




        // Define your database name, version, table name, and column names here

        // Constructor and other methods

        // Method to read data from the database

//    public ArrayList<Model> readDataFromDatabase() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        ArrayList<Model> list = new ArrayList<>();
//
//        Cursor cursor = db.rawQuery("SELECT * FROM blogDatabase", null);
//        if (cursor != null && cursor.moveToFirst()) {
//            do {
//                @SuppressLint("Range") int u_id = cursor.getInt(cursor.getColumnIndex("u_id"));
//                @SuppressLint("Range")String title = cursor.getString(cursor.getColumnIndex("title"));
//                @SuppressLint("Range")String description = cursor.getString(cursor.getColumnIndex("description"));
//                @SuppressLint("Range")String author = cursor.getString(cursor.getColumnIndex("author"));
//                @SuppressLint("Range")String date = cursor.getString(cursor.getColumnIndex("date"));
//                @SuppressLint("Range")int shareCount = cursor.getInt(cursor.getColumnIndex("share_count"));
//                @SuppressLint("Range") Bitmap image = cursor.getBlob(cursor.getColumnIndex("img"));
//
//                // Populate your model object
//                Model model = new Model();
//                model.setU_id(u_id);
//                model.setTitle(title);
//                model.setDescription(description);
//                model.setAuthor(author);
//                model.setDate(date);
//                model.setShare_count(shareCount);
//                model.setImg(image);
//                list.add(model);
//            } while (cursor.moveToNext());
//            cursor.close();
//        }
//        return list;
//    }







    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
