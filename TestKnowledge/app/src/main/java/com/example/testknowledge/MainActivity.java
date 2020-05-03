package com.example.testknowledge;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.testknowledge.DAO.CategoryDAO;
import com.example.testknowledge.DAO.QuestionDAO;
import com.example.testknowledge.DTO.CategoryDTO;
import com.example.testknowledge.DTO.QuestionDTO;
import com.example.testknowledge.SQLiteHelper.QuizDbHelper;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnStart;
    private static final int REQUEST_CODE_QUIZ=1;

    public static final String EXTRA_CATEGORY_ID="extraCategoryID";
    public static final String EXTRA_CATEGORY_NAME="extraCategoryName";
    public static final String EXTRA_DIFFICULTY="extraDifficulty";

    public static final String SHARED_PREFS="sharedPrefs";
    public static final String KEY_HIGHSCORE="keyHighscore";
    private TextView textViewHighScore;
    private int highScore;
    private Spinner spinnerDifficulty;
    private Spinner spinnerCategory;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        if(!doesDatabaseExist(this, QuizDbHelper.DATABASE_NAME))
        {
            fillData();
        }
        loadCategory();
        loadDifficultyLevel();
        loadHighScore();
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
    }
    private void AnhXa()
    {
        btnStart=findViewById(R.id.btnStart);
        textViewHighScore=findViewById(R.id.txtHighscore);
        spinnerDifficulty=findViewById(R.id.spinner_difficulty);
        spinnerCategory=findViewById(R.id.spinner_category);
    }
    private void start()
    {
        CategoryDTO selectedCategory= (CategoryDTO) spinnerCategory.getSelectedItem();
        int categoryID=selectedCategory.getId();
        String categoryName=selectedCategory.getName();
        String difficulty=spinnerDifficulty.getSelectedItem().toString();
        Intent intent=new Intent(MainActivity.this,QuizActivity.class);
        intent.putExtra(EXTRA_CATEGORY_ID,categoryID);
        intent.putExtra(EXTRA_CATEGORY_NAME,categoryName);
        intent.putExtra(EXTRA_DIFFICULTY,difficulty);
        startActivityForResult(intent,REQUEST_CODE_QUIZ);
    }
    public void fillData()
    {
        //CATEGORY
        CategoryDAO categoryDAO=new CategoryDAO(this);
        categoryDAO.open();
        categoryDAO.fillCategoryTable();
        categoryDAO.close();
        //QUESTION
        QuestionDAO questionDAO=new QuestionDAO(this);
        questionDAO.open();
        questionDAO.fillQuestionTable();
        questionDAO.close();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE_QUIZ)
        {
            if(resultCode==RESULT_OK)
            {
                int score=data.getIntExtra(QuizActivity.EXTRA_SCORE,0);
                if(score>highScore)
                {
                    updateHighScore(score);
                }
            }
        }
    }
    private void loadCategory()
    {
        CategoryDAO categoryDAO=new CategoryDAO(this);
        categoryDAO.open();
        List<CategoryDTO> categoryDTOList=categoryDAO.getAllCategories();
        ArrayAdapter<CategoryDTO> categoryDTOArrayAdapter=new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item,categoryDTOList);
        categoryDTOArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(categoryDTOArrayAdapter);
        categoryDAO.close();
    }
    private void loadDifficultyLevel()
    {
        String[] difficultyLevels= QuestionDTO.getAllDifficultyLevel();
        ArrayAdapter<String> adapterDifficulty=new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item,difficultyLevels);
        adapterDifficulty.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerDifficulty.setAdapter(adapterDifficulty);
    }
    private void loadHighScore()
    {
        SharedPreferences prefs=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        highScore=prefs.getInt(KEY_HIGHSCORE,0);
        textViewHighScore.setText("Highscore: "+highScore);
    }
    private void updateHighScore(int highscoreNew)
    {
        highScore=highscoreNew;
        textViewHighScore.setText("Highscore: "+highScore);
        SharedPreferences prefs=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        editor.putInt(KEY_HIGHSCORE,highScore);
        editor.apply();
    }
    private static boolean doesDatabaseExist(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }
}
