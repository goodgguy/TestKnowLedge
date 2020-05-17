package com.example.testknowledge.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.testknowledge.DTO.PlayerDTO;
import com.example.testknowledge.QuizContract.*;
import com.example.testknowledge.SQLiteHelper.QuizDbHelper;

import java.util.ArrayList;

public class PlayerDAO {
    QuizDbHelper quizDbHelper;
    SQLiteDatabase database;
    public String[] mangImage=new String[]{};
    public PlayerDAO (Context context)
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
    public boolean addPlayeṛ̣̣̣(PlayerDTO playerDTO)
    {
        ContentValues cv=new ContentValues();
        cv.put(PlayerTable.COLUMN_NAME,playerDTO.getName());
        cv.put(PlayerTable.COLUMN_IMAGE,playerDTO.getImage());
        cv.put(PlayerTable.COLUMN_POINT,playerDTO.getPoint());
        long id_player=database.insert(PlayerTable.TABLE_NAME,null,cv);
        if(id_player!=0)
        {
            return true;
        }
        return false;
    }
    public ArrayList<PlayerDTO> getAllplayer()
    {
        ArrayList<PlayerDTO> playerDTOArrayList=new ArrayList<>();
        Cursor cursor=database.rawQuery("SELECT * FROM " + PlayerTable.TABLE_NAME+" ORDER BY "+PlayerTable.COLUMN_POINT+" DESC;",null);
        if(cursor.moveToFirst())
        {
            do {
                PlayerDTO playerDTO=new PlayerDTO();
                playerDTO.setId(cursor.getInt(cursor.getColumnIndex(PlayerTable._ID)));
                playerDTO.setName(cursor.getString(cursor.getColumnIndex(PlayerTable.COLUMN_NAME)));
                playerDTO.setImage(cursor.getInt(cursor.getColumnIndex(PlayerTable.COLUMN_IMAGE)));
                playerDTO.setPoint(cursor.getInt(cursor.getColumnIndex(PlayerTable.COLUMN_POINT)));
                playerDTOArrayList.add(playerDTO);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return playerDTOArrayList;
    }
}
