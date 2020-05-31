package com.example.testknowledge.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.testknowledge.DTO.CategoryDTO;
import com.example.testknowledge.DTO.QuestionDTO;
import com.example.testknowledge.QuizContract;
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
        /*QuestionDTO English_Easy_05=new QuestionDTO("","","","",3,QuestionDTO.DIFFICULTY_EASY, CategoryDTO.ENGLISH);
        addQuestion(English_Easy_05);*/
        //PROGRAMMING_EASY================
        QuestionDTO Programing_Easy_01=new QuestionDTO("Ưu điểm của OPP","Dễ mô tả các quan hệ phân cấp trong thế giới thực","Có tính bảo mật cao","A,B Đúng",3,QuestionDTO.DIFFICULTY_EASY, CategoryDTO.PROGRAMING);
        addQuestion(Programing_Easy_01);
        QuestionDTO Programing_Easy_02=new QuestionDTO("Ưu điểm của classfile trong java là","Java classfile có thể được dùng ở bất k2i platform nào","Tính module hóa cao,dùng bộ nhớ tốt hơn","Cả A,B đều đúng",3,QuestionDTO.DIFFICULTY_EASY, CategoryDTO.PROGRAMING);
        addQuestion(Programing_Easy_02);
        QuestionDTO Programing_Easy_03=new QuestionDTO("JDK bao gồm các thành phần chính","Classes,Complier,Debugger,JRV","Classes,Complier,Debugger","Complier,Debugger,JRV",1,QuestionDTO.DIFFICULTY_EASY, CategoryDTO.PROGRAMING);
        addQuestion(Programing_Easy_03);
        QuestionDTO Programing_Easy_04=new QuestionDTO("Bao đóng là đặc tính OPP dùng để","Che dấu dữ liệu","Bên ngoài chỉ giao tiếp được với đối tượng thông qua một số phương thức","A,B đều đúng",2,QuestionDTO.DIFFICULTY_EASY, CategoryDTO.PROGRAMING);
        addQuestion(Programing_Easy_04);
        QuestionDTO Programing_Easy_05=new QuestionDTO("Các từ cấu trúc rẽ nhánh java gồm","if,else,switch,case,default,break","if,else,switch,case,default,break","Cả 2 câu đều sai",1,QuestionDTO.DIFFICULTY_EASY, CategoryDTO.PROGRAMING);
        addQuestion(Programing_Easy_05);

        //PROGRAMING_MEDIUM
        QuestionDTO Programing_Medium_01=new QuestionDTO("Các hằng trong java bao gồm","True,False,Null","TRUE,FALSE,NULL","true,false,null",3,QuestionDTO.DIFFICULTY_MEDIUM, CategoryDTO.PROGRAMING);
        addQuestion(Programing_Medium_01);
        QuestionDTO Programing_Medium_02=new QuestionDTO("Chọn đáp án đúng khi tính trị tuyệt đối của x","Math.abs(x)","math.abs(x)","x.abs()",1,QuestionDTO.DIFFICULTY_MEDIUM, CategoryDTO.PROGRAMING);
        addQuestion(Programing_Medium_02);
        QuestionDTO Programing_Medium_03=new QuestionDTO("System.in là","Đối tượng xuất mặc định","Đối tượng nhập mặc định","Cả 2 câu đều sai",2,QuestionDTO.DIFFICULTY_MEDIUM, CategoryDTO.PROGRAMING);
        addQuestion(Programing_Medium_03);
        QuestionDTO Programing_Medium_04=new QuestionDTO("Một thành phần của class có modifier nào thì chỉ giới hạn các đối tượng khác gói,lớp,không có cha con mà không được truy cập","public","private","protected",3,QuestionDTO.DIFFICULTY_MEDIUM, CategoryDTO.PROGRAMING);
        addQuestion(Programing_Medium_04);
        QuestionDTO Programing_Medium_05=new QuestionDTO("Kỹ thuật overload cho phép","Khai báo các hàm trùng tên,trùng kiểu tham số nhưng khác kiểu trả trị của hàm","Khai báo các hàm khác tên,khác kiểu tham số,khác kiểu trả trị của hàm","Khai báo các hàm trùng tên,nhưng khác kiểu tham số hoặc số lượng tham số",3,QuestionDTO.DIFFICULTY_MEDIUM, CategoryDTO.PROGRAMING);
        addQuestion(Programing_Medium_05);

        //PROGRAMMING_HARD
        QuestionDTO Programing_Hard_01=new QuestionDTO("Khi một thành phần của class được khai báo modifier là frendly thì thành phần đó","Không cho phép các đối tượng thuộc các class cùng packet truy cập","Cho phép các đối tượng thuộc các class cùng packet truy cập","Tất cả đều dúng",2,QuestionDTO.DIFFICULTY_HARD, CategoryDTO.PROGRAMING);
        addQuestion(Programing_Hard_01);
       /* QuestionDTO Programing_Hard_02=new QuestionDTO("History: A is correct","A","B","C",1,QuestionDTO.DIFFICULTY_HARD, CategoryDTO.PROGRAMING);
        addQuestion(Programing_Hard_02);
        QuestionDTO Programing_Hard_03=new QuestionDTO("History: A is correct","A","B","C",1,QuestionDTO.DIFFICULTY_HARD, CategoryDTO.PROGRAMING);
        addQuestion(Programing_Hard_03);
        QuestionDTO Programing_Hard_04=new QuestionDTO("History: A is correct","A","B","C",1,QuestionDTO.DIFFICULTY_HARD, CategoryDTO.PROGRAMING);
        addQuestion(Programing_Hard_04);
        QuestionDTO Programing_Hard_05=new QuestionDTO("History: A is correct","A","B","C",1,QuestionDTO.DIFFICULTY_HARD, CategoryDTO.PROGRAMING);
        addQuestion(Programing_Hard_05);*/


       //ENGLISH_EASY
        QuestionDTO English_Easy_01=new QuestionDTO("“How many pages............... so far?”","have you studied","did you study","do you study",1,QuestionDTO.DIFFICULTY_EASY, CategoryDTO.ENGLISH);
        addQuestion(English_Easy_01);
        QuestionDTO English_Easy_02=new QuestionDTO("We are too late. The plane ............... off ten minutes ago.","has taken","took","was taken",3,QuestionDTO.DIFFICULTY_EASY, CategoryDTO.ENGLISH);
        addQuestion(English_Easy_02);
        QuestionDTO English_Easy_03=new QuestionDTO("What a lovely boat!” “I ...............half a year building this boat.","spend","spent","spending",2,QuestionDTO.DIFFICULTY_EASY, CategoryDTO.ENGLISH);
        addQuestion(English_Easy_03);
        QuestionDTO English_Easy_04=new QuestionDTO("How ............since we ............school?","are you/ left","b. have you been/ have left","c. were you/ left",1,QuestionDTO.DIFFICULTY_EASY, CategoryDTO.ENGLISH);
        addQuestion(English_Easy_04);
        QuestionDTO English_Easy_05=new QuestionDTO("I think John ..............tomorrow.","would come","come","will come",3,QuestionDTO.DIFFICULTY_EASY, CategoryDTO.ENGLISH);
        addQuestion(English_Easy_05);
        //ENGLISH_MEDIUM
        QuestionDTO English_Medium_01=new QuestionDTO("After he ------------ his driving test he bought a car.","had passed","pass","passing",1,QuestionDTO.DIFFICULTY_MEDIUM, CategoryDTO.ENGLISH);
        addQuestion(English_Medium_01);
        QuestionDTO English_Medium_02=new QuestionDTO("When I got to the office, I ----------- that I had forgot to lock the door.","realized","had realized","realize",1,QuestionDTO.DIFFICULTY_MEDIUM, CategoryDTO.ENGLISH);
        addQuestion(English_Medium_02);
        QuestionDTO English_Medium_03=new QuestionDTO("When she was 21 she -------------- across the United States.","drove","drive","driven",1,QuestionDTO.DIFFICULTY_MEDIUM, CategoryDTO.ENGLISH);
        addQuestion(English_Medium_03);
        QuestionDTO English_Medium_04=new QuestionDTO("He often ------------ so nervous before his exams.","feels","felt","was feeling",1,QuestionDTO.DIFFICULTY_MEDIUM, CategoryDTO.ENGLISH);
        addQuestion(English_Medium_04);
        QuestionDTO English_Medium_05=new QuestionDTO("How long have you ------------- he was a liar.","been knowing","knew","known",3,QuestionDTO.DIFFICULTY_MEDIUM, CategoryDTO.ENGLISH);
        addQuestion(English_Medium_05);
        //ENGLISH_HARD
        QuestionDTO English_Hard_01=new QuestionDTO("I’m sorry. I don’t understand what -----------------.","you say","you're saying","you will say",2,QuestionDTO.DIFFICULTY_HARD, CategoryDTO.ENGLISH);
        addQuestion(English_Hard_01);
        QuestionDTO English_Hard_02=new QuestionDTO(". ------------- to the radio when you get up everyday?","Do you listen","Are you listening","Was you listening",1,QuestionDTO.DIFFICULTY_HARD, CategoryDTO.ENGLISH);
        addQuestion(English_Hard_02);
        QuestionDTO English_Hard_03=new QuestionDTO("My sweetheart --------------- smoking next week.","will give up","is going to give up","gives up",2,QuestionDTO.DIFFICULTY_HARD, CategoryDTO.ENGLISH);
        addQuestion(English_Hard_03);
        QuestionDTO English_Hard_04=new QuestionDTO("There is no red wine? I -------------- white, then.","am going to have","have","will have",3,QuestionDTO.DIFFICULTY_HARD, CategoryDTO.ENGLISH);
        addQuestion(English_Hard_04);
        QuestionDTO English_Hard_05=new QuestionDTO("When the phone rang she ------------ a letter.","writes","will write","was writing",3,QuestionDTO.DIFFICULTY_HARD, CategoryDTO.ENGLISH);
        addQuestion(English_Hard_05);
        //HISTORY_EASY
        QuestionDTO History_Easy_01=new QuestionDTO("Những nước nào tham gia Hội nghị Ianta ?","Anh - Pháp - Mĩ.","Anh - Mĩ - Liên Xô.","Anh - Pháp - Đức.",2,QuestionDTO.DIFFICULTY_EASY, CategoryDTO.HISTORY);
        addQuestion(History_Easy_01);
        QuestionDTO History_Easy_02=new QuestionDTO("Một trong những nội dung quan trọng của Hội nghị Ianta là:","Đàm phán, kí kết các hiệp ước với các nước phát xít bại trận."," Các nước thắng trận thoả thuận việc phân chia Đức thành hai nước Đông Đức và Tây Đức.","Ba nước phe Đồng minh bàn bạc, thoả thuận khu vực đóng quân tại các nước nhằm giải giáp quân đội phát xít; phân chia phạm vi ảnh hưởng ở châu Âu và châu Á.",3,QuestionDTO.DIFFICULTY_EASY, CategoryDTO.HISTORY);
        addQuestion(History_Easy_02);
        QuestionDTO History_Easy_03=new QuestionDTO("Hội nghị Ianta diễn ra từ:","Ngày 4 đến 11/2/1945","Ngày 2 đến 14/2/1945.","Ngày 2 đến 12/4/1945.",1,QuestionDTO.DIFFICULTY_EASY, CategoryDTO.HISTORY);
        addQuestion(History_Easy_03);
        //HISTORY_MEDIUM
        QuestionDTO History_Medium_01=new QuestionDTO("Thành viên nào đã rút khỏi tổ chức Hội đồng tương trợ kinh tế trong năm 1961 ?","An-ban-ni.","Hung-ga-ri.","Tiệp Khắc.",1,QuestionDTO.DIFFICULTY_MEDIUM, CategoryDTO.HISTORY);
        addQuestion(History_Medium_01);
        QuestionDTO History_Medium_02=new QuestionDTO("Đường lối cải tổ đất nước ờ Liên Xô được thực hiện từ khi nào ? Do ai đề xướng ?","Tháng 5/1983, do B. Ensin đề xướng","Tháng 3/1984, do Anđrôpốp đề xướng.","Tháng 3/1985, do M. Goócbachốp đề xướng",3,QuestionDTO.DIFFICULTY_MEDIUM, CategoryDTO.HISTORY);
        addQuestion(History_Medium_02);
        QuestionDTO History_Medium_03=new QuestionDTO("Tình hình Liên Xô sau 6 năm tiến hành đường lối cải tổ là:","Tuy kinh tế dần ổn định, nhưng chính trị ngày càng rối loạn.","Chính trị dần ổn định, tuy nhiên kinh tế tiếp tục sa sút không thể vực dậy.","Lâm vào cuộc khủng hoảng toàn diện.",3,QuestionDTO.DIFFICULTY_MEDIUM, CategoryDTO.HISTORY);
        addQuestion(History_Medium_03);
        //HISTORY_HARD
        QuestionDTO History_Hard_01=new QuestionDTO("Đảng và Nhà nước Trung Quốc xác định trọng tâm của Đường lối chung là:","Lấy cải cách kinh tế làm trung tâm.","Lấy đổi mới chính trị làm trung tâm.","Đổi mới kinh tế và đổi mới chính trị được tiến hành đồng thời.",1,QuestionDTO.DIFFICULTY_HARD, CategoryDTO.HISTORY);
        addQuestion(History_Hard_01);
        QuestionDTO History_Hard_02=new QuestionDTO("Điểm nổi bật của nền kinh tế Trung Quốc trong thời kì đổi mới (1978 - 2000)","Xây dựng nền kinh tế kế hoạch hoá tập trung.","Xây dựng nền kinh tế thị trường tự do.","Xây dựng nền kinh tế thị trường xã hội chủ nghĩa.",3,QuestionDTO.DIFFICULTY_HARD, CategoryDTO.HISTORY);
        addQuestion(History_Hard_02);
        QuestionDTO History_Hard_03=new QuestionDTO("Trung Quốc chế tạo thành công bom nguyên tử vào năm :","1964","1965.","1959.",1,QuestionDTO.DIFFICULTY_HARD, CategoryDTO.HISTORY);
        addQuestion(History_Hard_03);



    }
    public boolean addQuestion(QuestionDTO questionDTO)
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
    public boolean deleteQuestion(int id)
    {
        return database.delete(QuestionsTable.TABLE_NAME, QuestionsTable._ID + "=" + id, null) > 0;
    }
    public void updateQuestion(QuestionDTO questionDTO)
    {
        String sql="UPDATE "+QuestionsTable.TABLE_NAME+" SET "+
                QuestionsTable.COLUMN_QUESTION+"='"+questionDTO.getQuestion()+"', "
                +QuestionsTable.COLUMN_OPTION1+"='"+questionDTO.getOption1()+"', "
                +QuestionsTable.COLUMN_OPTION2+"='"+questionDTO.getOption2()+"', "
                +QuestionsTable.COLUMN_OPTION3+"='"+questionDTO.getOption3()+"', "
                +QuestionsTable.COLUMN_ANSWER_NR+"='"+questionDTO.getAnswer()+"', "
                +QuestionsTable.COLUMN_CATEGORY_ID+"='"+questionDTO.getCategoryID()+"', "
                +QuestionsTable.COLUMN_DIFFICULTY+"='"+questionDTO.getDifficulty()
                +"' WHERE "+QuestionsTable._ID+"="+questionDTO.getId();
        database.execSQL(sql);
    }
}
