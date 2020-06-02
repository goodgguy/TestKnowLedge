package com.example.testknowledge.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.testknowledge.DTO.CategoryDTO;
import com.example.testknowledge.QuizContract.*;
import com.example.testknowledge.SQLiteHelper.QuizDbHelper;

import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    QuizDbHelper quizDbHelper;
    SQLiteDatabase database;
    public CategoryDAO(Context context)
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
    public void fillCategoryTable()
    {
        CategoryDTO c1=new CategoryDTO("Programming");
        addCategory(c1);
        CategoryDTO c2=new CategoryDTO("English");
        addCategory(c2);
        CategoryDTO c3=new CategoryDTO("History");
        addCategory(c3);
    }
    public void addCategory(CategoryDTO categoryDTO)
    {
        ContentValues cv=new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME,categoryDTO.getName());
        database.insert(CategoriesTable.TABLE_NAME,null,cv);
    }
    public List<CategoryDTO>getAllCategories()
    {
        List<CategoryDTO> categoryDTOList=new ArrayList<>();
        Cursor cursor=database.rawQuery("SELECT * FROM "+CategoriesTable.TABLE_NAME,null);
        if(cursor.moveToFirst())
        {
            do{
                CategoryDTO categoryDTO=new CategoryDTO();
                categoryDTO.setId(cursor.getInt(cursor.getColumnIndex(CategoriesTable._ID)));
                categoryDTO.setName(cursor.getString(cursor.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryDTOList.add(categoryDTO);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return categoryDTOList;
    }
    public ArrayList<CategoryDTO>getAllCategories_array()
    {
        ArrayList<CategoryDTO> categoryDTOList=new ArrayList<>();
        Cursor cursor=database.rawQuery("SELECT * FROM "+CategoriesTable.TABLE_NAME,null);
        if(cursor.moveToFirst())
        {
            do{
                CategoryDTO categoryDTO=new CategoryDTO();
                categoryDTO.setId(cursor.getInt(cursor.getColumnIndex(CategoriesTable._ID)));
                categoryDTO.setName(cursor.getString(cursor.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryDTOList.add(categoryDTO);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return categoryDTOList;
    }
    public boolean deleteCategory(int id)
    {
        return database.delete(CategoriesTable.TABLE_NAME, CategoriesTable._ID + "=" + id, null) > 0;
    }
    public void updateCategory(CategoryDTO categoryDTO)
    {
        String sql="UPDATE "+CategoriesTable.TABLE_NAME+" SET "+
                CategoriesTable.COLUMN_NAME+"='"+categoryDTO.getName()+"' "
                +" WHERE "+CategoriesTable._ID+"="+categoryDTO.getId();
        Log.e("SQL category",sql);
        database.execSQL(sql);
    }
}
