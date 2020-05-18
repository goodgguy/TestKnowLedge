package com.example.testknowledge.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.testknowledge.DTO.UserDTO;
import com.example.testknowledge.QuizContract.*;
import com.example.testknowledge.SQLiteHelper.QuizDbHelper;

public class UserDAO {
    QuizDbHelper quizDbHelper;
    SQLiteDatabase database;
    public UserDAO(Context context)
    {
        quizDbHelper=new QuizDbHelper(context);
    }
    public void open()
    {
        database=quizDbHelper.getWritableDatabase();
    }
    public void close()
    {
        database.close();
    }
    public boolean addUser(UserDTO userDTO)
    {
        ContentValues cv=new ContentValues();
        cv.put(UserTable.COLUMN_USERNAME,userDTO.getUsername());
        cv.put(UserTable.COLUMN_PASSWORD,userDTO.getPassword());
        long id_user=database.insert(UserTable.TABLE_NAME,null,cv);
        if(id_user!=0)
        {
            return true;
        }
        return false;
    }
    public boolean checkUser(UserDTO userDTO)
    {
        Cursor cursor=database.rawQuery("SELECT * FROM "+ UserTable.TABLE_NAME+" WHERE "+ UserTable.COLUMN_USERNAME +"=? AND "+UserTable.COLUMN_PASSWORD+"=?",new String[]{userDTO.getUsername(),userDTO.getPassword()});
        if(cursor.getCount() <= 0) {
            cursor.close();
            return false;
        } else {
            cursor.close();
            return true;
        }
    }
    public void load()
    {
        addUser(new UserDTO("admin","123456"));
    }

}
