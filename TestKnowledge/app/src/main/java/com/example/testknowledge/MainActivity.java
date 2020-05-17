package com.example.testknowledge;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.testknowledge.DAO.CategoryDAO;
import com.example.testknowledge.DAO.PlayerDAO;
import com.example.testknowledge.DAO.QuestionDAO;
import com.example.testknowledge.DTO.CategoryDTO;
import com.example.testknowledge.DTO.PlayerDTO;
import com.example.testknowledge.DTO.QuestionDTO;
import com.example.testknowledge.SQLiteHelper.QuizDbHelper;
import com.example.testknowledge.Service.MusicService;
import com.varunest.sparkbutton.SparkButton;
import com.varunest.sparkbutton.SparkEventListener;

import java.io.File;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity{
    Button btnStart;
    SparkButton btnStartSpark;
    SparkButton btnLaderboardSpark;
    ToggleButton toggleMusic;
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

    private String playername="";
    private EditText edplayername;

    int[] mangavatar=new int[]{R.drawable.avatar,R.drawable.avatar1,R.drawable.avatar2,R.drawable.avatar3,R.drawable.avatar4};


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
        buttonListener();
/*        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });*/

    }
    private void buttonListener()
    {
        btnStartSpark.setEventListener(new SparkEventListener(){
            @Override
            public void onEvent(ImageView button, boolean buttonState) {
                if (buttonState) {
                    start();
                } else {
                    start();
                }
            }

            @Override
            public void onEventAnimationEnd(ImageView button, boolean buttonState) {

            }

            @Override
            public void onEventAnimationStart(ImageView button, boolean buttonState) {

            }
        });
        btnLaderboardSpark.setEventListener(new SparkEventListener() {
            @Override
            public void onEvent(ImageView button, boolean buttonState) {
                Intent intent=new Intent(MainActivity.this,LaderboardActivity.class);
                startActivity(intent);

            }

            @Override
            public void onEventAnimationEnd(ImageView button, boolean buttonState) {

            }

            @Override
            public void onEventAnimationStart(ImageView button, boolean buttonState) {

            }
        });
        toggleMusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            Intent intentMusic=new Intent(MainActivity.this, MusicService.class);
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    stopService(intentMusic);
                }
                else
                {
                    startService(intentMusic);
                }
            }
        });
        toggleMusic.setChecked(true);
        toggleMusic.setChecked(false);
    }
    private void AnhXa()
    {
        //btnStart=findViewById(R.id.spark_button);
        btnStartSpark=findViewById(R.id.spark_button);
        btnLaderboardSpark=findViewById(R.id.spark_button_laderboard);
        textViewHighScore=findViewById(R.id.txtHighscore);
        spinnerDifficulty=findViewById(R.id.spinner_difficulty);
        spinnerCategory=findViewById(R.id.spinner_category);
        toggleMusic=findViewById(R.id.togvolume);
    }
    private void start()
    {
        OpenDiaLogName();
        /*CategoryDTO selectedCategory= (CategoryDTO) spinnerCategory.getSelectedItem();
        int categoryID=selectedCategory.getId();
        String categoryName=selectedCategory.getName();
        String difficulty=spinnerDifficulty.getSelectedItem().toString();
        Intent intent=new Intent(MainActivity.this,QuizActivity.class);
        intent.putExtra(EXTRA_CATEGORY_ID,categoryID);
        intent.putExtra(EXTRA_CATEGORY_NAME,categoryName);
        intent.putExtra(EXTRA_DIFFICULTY,difficulty);
        startActivityForResult(intent,REQUEST_CODE_QUIZ);*/
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
                Random rd=new Random();
                PlayerDTO playerDTO=new PlayerDTO(playername,mangavatar[rd.nextInt(5)],score);
                PlayerDAO playerDAO=new PlayerDAO(getApplicationContext());
                playerDAO.open();
                playerDAO.addPlayeṛ̣̣̣(playerDTO);
                playerDAO.close();
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
    public void OpenDiaLogName()
    {
        AlertDialog.Builder mBuilder=new AlertDialog.Builder(MainActivity.this);
        View mview=getLayoutInflater().inflate(R.layout.layout_dialog,null);
        edplayername=mview.findViewById(R.id.edName);
        mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        mBuilder.setView(mview);
        final AlertDialog dialog=mBuilder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edplayername.getText().toString().isEmpty())
                {
                    playername=edplayername.getText().toString();
                    dialog.dismiss();
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
                else {
                    Toast.makeText(getApplicationContext(),"Tên còn trống",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
