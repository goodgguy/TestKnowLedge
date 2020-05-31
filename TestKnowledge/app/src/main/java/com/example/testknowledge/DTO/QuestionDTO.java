package com.example.testknowledge.DTO;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class QuestionDTO implements Parcelable, Serializable {
    public static final String DIFFICULTY_EASY="Easy";
    public static final String DIFFICULTY_MEDIUM="Medium";
    public static final String DIFFICULTY_HARD="Hard";

    private int id;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private int answer;
    private String difficulty;
    private int categoryID;

    public QuestionDTO()
    {

    }
    public QuestionDTO(String question, String option1, String option2, String option3, int answer,String difficulty,int categoryID) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.answer = answer;
        this.difficulty=difficulty;
        this.categoryID=categoryID;
    }

    protected QuestionDTO(Parcel in) {
        id=in.readInt();
        question = in.readString();
        option1 = in.readString();
        option2 = in.readString();
        option3 = in.readString();
        answer = in.readInt();
        difficulty=in.readString();
        categoryID=in.readInt();
    }

    public static final Creator<QuestionDTO> CREATOR = new Creator<QuestionDTO>() {
        @Override
        public QuestionDTO createFromParcel(Parcel in) {
            return new QuestionDTO(in);
        }

        @Override
        public QuestionDTO[] newArray(int size) {
            return new QuestionDTO[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public static String[] getAllDifficultyLevel()
    {
        return new String[]
                {
                        DIFFICULTY_EASY,DIFFICULTY_MEDIUM,DIFFICULTY_HARD
                };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(question);
        dest.writeString(option1);
        dest.writeString(option2);
        dest.writeString(option3);
        dest.writeInt(answer);
        dest.writeString(difficulty);
        dest.writeInt(categoryID);
    }
}
