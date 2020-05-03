package com.example.testknowledge.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.testknowledge.DTO.CategoryDTO;
import com.example.testknowledge.DTO.QuestionDTO;
import com.example.testknowledge.QuizContract.*;
import com.example.testknowledge.SQLiteHelper.QuizDbHelper;

import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {
    QuizDbHelper quizDbHelper;
    SQLiteDatabase database;
    public QuestionDAO (Context context)
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


    public void fillQuestionTable()
    {
        QuestionDTO q1=new QuestionDTO("Programming: A is correct","A","B","C",1,QuestionDTO.DIFFICULTY_EASY, CategoryDTO.PROGRAMING);
        addQuestion(q1);
        QuestionDTO q4=new QuestionDTO("Programming:1 A is correct","A","B","C",1,QuestionDTO.DIFFICULTY_EASY, CategoryDTO.PROGRAMING);
        addQuestion(q4);
        QuestionDTO q5=new QuestionDTO("Programming:2 A is correct","A","B","C",1,QuestionDTO.DIFFICULTY_EASY, CategoryDTO.PROGRAMING);
        addQuestion(q5);
        QuestionDTO q6=new QuestionDTO("Programming:3 A is correct","A","B","C",1,QuestionDTO.DIFFICULTY_EASY, CategoryDTO.PROGRAMING);
        addQuestion(q6);
        QuestionDTO q7=new QuestionDTO("Programming:4 A is correct","A","B","C",1,QuestionDTO.DIFFICULTY_EASY, CategoryDTO.PROGRAMING);
        addQuestion(q7);


        QuestionDTO q2=new QuestionDTO("English: is correct","A","B","C",1,QuestionDTO.DIFFICULTY_EASY, CategoryDTO.ENGLISH);
        addQuestion(q2);
        QuestionDTO q3=new QuestionDTO("History: A is correct","A","B","C",1,QuestionDTO.DIFFICULTY_EASY, CategoryDTO.HISTORY);
        addQuestion(q3);
    }
    private boolean addQuestion(QuestionDTO questionDTO)
    {
        ContentValues cv=new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, questionDTO.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, questionDTO.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, questionDTO.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, questionDTO.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, questionDTO.getAnswer());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, questionDTO.getDifficulty());
        cv.put(QuestionsTable.COLUMN_CATEGORY_ID,questionDTO.getCategoryID());

        long id_question=database.insert(QuestionsTable.TABLE_NAME,null,cv);
        if(id_question!=0)
        {
            return true;
        }
        return false;
    }
    public ArrayList<QuestionDTO> getAllQuestion()
    {
        ArrayList<QuestionDTO> questionDTOList = new ArrayList<>();
        Cursor cursor=database.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME,null);
        if(cursor.moveToFirst())
        {
            do{
                QuestionDTO questionDTO=new QuestionDTO();
                questionDTO.setId(cursor.getInt(cursor.getColumnIndex(QuestionsTable._ID)));
                questionDTO.setQuestion(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                questionDTO.setOption1(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                questionDTO.setOption2(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                questionDTO.setOption3(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                questionDTO.setAnswer(cursor.getInt(cursor.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                questionDTO.setDifficulty(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                questionDTO.setCategoryID(cursor.getInt(cursor.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionDTOList.add(questionDTO);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return questionDTOList;
    }
    public ArrayList<QuestionDTO> getQuestion(int categoryID,String difficulty)
    {
        ArrayList<QuestionDTO> questionDTOList = new ArrayList<>();

        String selection=QuestionsTable.COLUMN_CATEGORY_ID+" = ? "+
                " AND "+ QuestionsTable.COLUMN_DIFFICULTY+" = ? ";
        String[] selectionArgs=new String[]{String.valueOf(categoryID),difficulty};

        Cursor cursor=database.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        if(cursor.moveToFirst())
        {
            do{
                QuestionDTO questionDTO=new QuestionDTO();
                questionDTO.setId(cursor.getInt(cursor.getColumnIndex(QuestionsTable._ID)));
                questionDTO.setQuestion(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                questionDTO.setOption1(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                questionDTO.setOption2(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                questionDTO.setOption3(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                questionDTO.setAnswer(cursor.getInt(cursor.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                questionDTO.setDifficulty(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                questionDTO.setCategoryID(cursor.getInt(cursor.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionDTOList.add(questionDTO);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return questionDTOList;
    }
}
