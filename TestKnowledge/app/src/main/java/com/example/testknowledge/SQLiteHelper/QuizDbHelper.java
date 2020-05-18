package com.example.testknowledge.SQLiteHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.testknowledge.DAO.QuestionDAO;
import com.example.testknowledge.DTO.QuestionDTO;
import com.example.testknowledge.QuizContract.*;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="TESTKNOWLEDGE.db";
    private static final int DATABASE_VERSION=1;
    private static QuizDbHelper instance;
    public QuizDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_CATEGORY_TABLE="CREATE TABLE "+
                CategoriesTable.TABLE_NAME+ "( "+
                CategoriesTable._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                CategoriesTable.COLUMN_NAME+" TEXT "+
                ")";
        final String SQL_CREATE_QUESTION_TABLE="CREATE TABLE "+
                QuestionsTable.TABLE_NAME+" ( "+
                QuestionsTable._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                QuestionsTable.COLUMN_QUESTION+" TEXT, "+
                QuestionsTable.COLUMN_OPTION1+" TEXT, "+
                QuestionsTable.COLUMN_OPTION2+" TEXT, "+
                QuestionsTable.COLUMN_OPTION3+" TEXT, "+
                QuestionsTable.COLUMN_ANSWER_NR+" INTEGER, "+
                QuestionsTable.COLUMN_DIFFICULTY+ " TEXT,"+
                QuestionsTable.COLUMN_CATEGORY_ID+" INTEGER,"+
                "FOREIGN KEY("+QuestionsTable.COLUMN_CATEGORY_ID+ ") REFERENCES " +
                CategoriesTable.TABLE_NAME+ "("+ CategoriesTable._ID+")"+"ON DELETE CASCADE"+
                ")";
        final String SQL_CREATE_PLAYER_TABLE="CREATE TABLE "+
                PlayerTable.TABLE_NAME+" ( "+
                PlayerTable._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                PlayerTable.COLUMN_NAME+" TEXT, "+
                PlayerTable.COLUMN_IMAGE+" INTEGER, "+
                PlayerTable.COLUMN_POINT+" TEXT "+
                ")";
        final String SQL_CREATE_USER_TABLE="CREATE TABLE "+
                UserTable.TABLE_NAME+" ( "+
                UserTable._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                UserTable.COLUMN_USERNAME+" TEXT, "+
                UserTable.COLUMN_PASSWORD+" TEXT "+
                ")";
        db.execSQL(SQL_CREATE_CATEGORY_TABLE);
        db.execSQL(SQL_CREATE_QUESTION_TABLE);
        db.execSQL(SQL_CREATE_PLAYER_TABLE);
        db.execSQL(SQL_CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ QuestionsTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ PlayerTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ UserTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }
}
