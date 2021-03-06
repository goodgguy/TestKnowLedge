package com.example.testknowledge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testknowledge.DAO.PlayerDAO;
import com.example.testknowledge.DAO.QuestionDAO;
import com.example.testknowledge.DTO.PlayerDTO;
import com.example.testknowledge.DTO.QuestionDTO;
import com.example.testknowledge.SQLiteHelper.QuizDbHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class QuizActivity extends AppCompatActivity{
    public static final String EXTRA_SCORE="extraScore";
    private static final long COUNDOWN_IN_MILLIS=30000;

    private static final String KEY_SCORE="keyScore";
    private static final String KEY_QUESTION_COUNT="keyQuestionCount";
    private static final String KEY_MILLIS_LEFT="keyMillisLeft";
    private static final String KEY_ANSWERED="keyAnswer";
    private static final String KEY_QUESTION_LIST="keyQuestionList";

    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewCategory;
    private TextView textViewDifficulty;
    private TextView textViewCountdown;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private Button btnConfirmNext;

    private ColorStateList textCorlorDefaultrb;
    private ColorStateList textColorDefaultCd;

    private CountDownTimer countDownTimer;
    private long timeLeftMillis;

    private int questionCounter;
    private int questionCountTotal;
    private QuestionDTO currentQuestion;

    private int score;
    private boolean answered;
    private long backPressTime;
    private ArrayList<QuestionDTO> questionDTOList;
    QuestionDAO questionDAO;

    //NOTIFICATION
    private String notification_check="";
    private String notification_name="";

    //LANGUAGE-BUTTON
    String strConfirm,strNext,strFinish;
    String strAsw1,strAsw2,strAsw3;
    //QUESTIONCOUNT
    String StrtextviewQuestionCount;
    String StrScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Integer fontRes = this.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("font", R.font.lato);
        FontChangeCrawler fontChanger = new FontChangeCrawler(this, fontRes);
        fontChanger.replaceFonts((ViewGroup) getWindow().getDecorView().getRootView());
        AnhXa();
        StrtextviewQuestionCount=textViewQuestionCount.getText().toString();
        StrScore=textViewScore.getText().toString();
        textCorlorDefaultrb=rb1.getTextColors();
        textColorDefaultCd=textViewCountdown.getTextColors();
        Intent intent=getIntent();
        int categoryID=intent.getIntExtra(MainActivity.EXTRA_CATEGORY_ID,0);
        String categoryName=intent.getStringExtra(MainActivity.EXTRA_CATEGORY_NAME);
        String difficulty=intent.getStringExtra(MainActivity.EXTRA_DIFFICULTY);
        notification_check=intent.getStringExtra(MainActivity.EXTRA_NOTIFICATION);
        notification_name=intent.getStringExtra(MainActivity.EXTRA_NAME_NOTIFICATION);
        Log.e("CHECK NOTIFICATION",notification_check+"     "+notification_name);
        if(textViewCategory.getText().toString().compareTo("Category: ")==0)
        {
            strConfirm="Confirm";
            strFinish="Finish";
            strNext="Next";
            strAsw1="Answer 1 is correct";
            strAsw2="Answer 2 is correct";
            strAsw3="Answer 3 is correct";
        }
        else
        {
            strConfirm="Kiểm Tra";
            strFinish="Kết Thúc";
            strNext="Tiếp Theo";
            strAsw1="Đáp Án là Câu 1";
            strAsw2="Đáp Án là Câu 2";
            strAsw3="Đáp Án là Câu 3";
        }
        textViewCategory.setText(textViewCategory.getText().toString()+categoryName);
        textViewDifficulty.setText(textViewDifficulty.getText().toString()+difficulty);


        if (savedInstanceState == null) {
            questionDAO = new QuestionDAO(this);
            questionDAO.open();
            questionDTOList = questionDAO.getQuestion(categoryID,difficulty);

            questionCountTotal = questionDTOList.size();
            Collections.shuffle(questionDTOList);
            showNextQuestion();
        }else
        {
            questionDTOList=savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST);
            questionCountTotal=questionDTOList.size();
            questionCounter=savedInstanceState.getInt(KEY_QUESTION_COUNT);
            currentQuestion=questionDTOList.get(questionCounter-1);
            score=savedInstanceState.getInt(KEY_SCORE);
            timeLeftMillis=savedInstanceState.getLong(KEY_MILLIS_LEFT);
            answered=savedInstanceState.getBoolean(KEY_ANSWERED);

            if(!answered)
            {
                startCoundown();

            }else
            {
                updateCoundownText();
                showSolution();
            }
        }

        btnConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!answered)
                {
                    if(rb1.isChecked()||rb2.isChecked()||rb3.isChecked())
                    {
                        checkAnswer();
                    }else
                    {
                        Toast.makeText(QuizActivity.this,"Please select an answer",Toast.LENGTH_SHORT).show();
                    }
                }else
                {
                    showNextQuestion();
                }
            }
        });

    }
    private void showNextQuestion()
    {
        rb1.setTextColor(textCorlorDefaultrb);
        rb2.setTextColor(textCorlorDefaultrb);
        rb3.setTextColor(textCorlorDefaultrb);
        rbGroup.clearCheck();
        if(questionCounter!=questionCountTotal)
        {
            textViewQuestionCount.setText(StrtextviewQuestionCount+(questionCounter+1)+"/"+questionCountTotal);
        }
        if(questionCounter<questionCountTotal)
        {
            currentQuestion=questionDTOList.get(questionCounter);
            textViewQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            questionCounter++;
            if(questionCounter!=questionCountTotal)
            {
                textViewQuestionCount.setText(StrtextviewQuestionCount+questionCounter+"/"+questionCountTotal);
            }
            answered=false;
            btnConfirmNext.setText(strConfirm);
            timeLeftMillis=COUNDOWN_IN_MILLIS;
            startCoundown();
        }else
        {
            finishQuiz();
        }
    }
    private void startCoundown()
    {
        countDownTimer=new CountDownTimer(timeLeftMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftMillis=millisUntilFinished;
                updateCoundownText();
            }

            @Override
            public void onFinish() {
                timeLeftMillis=0;
                updateCoundownText();
                checkAnswer();
            }
        }.start();
    }
    private void updateCoundownText()
    {
        int minutes=(int)(timeLeftMillis/1000)/60;
        int second=(int) (timeLeftMillis/1000)%60;
        String timeFormatted=String.format(Locale.getDefault(),"%02d:%02d",minutes,second);
        textViewCountdown.setText(timeFormatted);
        if(timeLeftMillis<10000)
        {
            textViewCountdown.setTextColor(Color.RED);

        }else
        {
            textViewCountdown.setTextColor(textColorDefaultCd);
        }
    }
    private void checkAnswer()
    {
        answered=true;
        countDownTimer.cancel();

        RadioButton rbSelected=findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr=rbGroup.indexOfChild(rbSelected)+1;
        if(answerNr==currentQuestion.getAnswer())
        {
            score++;
            textViewScore.setText(StrScore+score);
        }
        showSolution();
    }
    private void showSolution()
    {
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        Log.e("RADIO BUTTON",String.valueOf(currentQuestion.getAnswer()));
        switch (currentQuestion.getAnswer())
        {
            case 1:
                rb1.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 1 is correct");
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 2 is correct");
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 3 is correct");
                break;
        }
        if(questionCounter<questionCountTotal)
        {
            btnConfirmNext.setText(strNext);
        }else
        {
            btnConfirmNext.setText(strFinish);
        }
    }
    private void AnhXa()
    {
        textViewQuestion=findViewById(R.id.txtQuestion);
        textViewScore=findViewById(R.id.txtViewScore);
        textViewQuestionCount=findViewById(R.id.txtCountQuestion);
        textViewDifficulty=findViewById(R.id.txt_view_difficulty);
        textViewCountdown=findViewById(R.id.txtCountDown);
        rbGroup=findViewById(R.id.radio_group);
        rb1=findViewById(R.id.rb1);
        rb2=findViewById(R.id.rb2);
        rb3=findViewById(R.id.rb3);
        btnConfirmNext=findViewById(R.id.btnConfirm);
        textViewCategory=findViewById(R.id.txt_view_category);
    }
    private void finishQuiz()
    {
        if(notification_check.compareTo("TUANCUTE")==0)
        {
            Log.e("Check Notify","Thành công rồi");
            Random rd=new Random();
            PlayerDTO playerDTO=new PlayerDTO(notification_name,MainActivity.mangavatar[rd.nextInt(5)],score);
            PlayerDAO playerDAO=new PlayerDAO(getApplicationContext());
            playerDAO.open();
            playerDAO.addPlayeṛ̣̣̣(playerDTO);
            playerDAO.close();

        }
        Intent resultIntent=new Intent();
        resultIntent.putExtra(EXTRA_SCORE,score);
        setResult(RESULT_OK,resultIntent);
        questionDAO.close();
        finish();
    }

    @Override
    public void onBackPressed() {
        if(backPressTime+2000>System.currentTimeMillis())
        {
            finishQuiz();
        }else
        {
            Toast.makeText(this,"Press back again to finish",Toast.LENGTH_SHORT).show();
        }
        backPressTime=System.currentTimeMillis();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer!=null)
        {
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SCORE,score);
        outState.putInt(KEY_QUESTION_COUNT,questionCounter);
        outState.putLong(KEY_MILLIS_LEFT,timeLeftMillis);
        outState.putBoolean(KEY_ANSWERED,answered);
        outState.putParcelableArrayList(KEY_QUESTION_LIST,questionDTOList);
    }
}
