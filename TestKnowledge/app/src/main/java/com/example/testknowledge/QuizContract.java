package com.example.testknowledge;

import android.provider.BaseColumns;

public final class QuizContract {
    private QuizContract(){}
    public static class CategoriesTable implements BaseColumns{
        public static final String TABLE_NAME="quiz_categories";
        public static final String COLUMN_NAME="name";

    }
    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME="quiz_questions";
        public static final String COLUMN_QUESTION="question";
        public static final String COLUMN_OPTION1="option1";
        public static final String COLUMN_OPTION2="option2";
        public static final String COLUMN_OPTION3="option3";
        public static final String COLUMN_ANSWER_NR="answer_nr";
        public static final String COLUMN_DIFFICULTY="difficulty";
        public static final String COLUMN_CATEGORY_ID="category_id";
    }
    public static class PlayerTable implements BaseColumns{
        public static final String TABLE_NAME="quiz_player";
        public static final String COLUMN_NAME="name";
        public static final String COLUMN_IMAGE="image";
        public static final String COLUMN_POINT="point";
    }
    public static class UserTable implements BaseColumns{
        public static final String TABLE_NAME="quiz_user";
        public static final String COLUMN_USERNAME="username";
        public static final String COLUMN_PASSWORD="password";
    }
}
