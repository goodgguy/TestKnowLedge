package com.example.testknowledge.Fragment;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.example.testknowledge.DAO.CategoryDAO;
import com.example.testknowledge.DTO.CategoryDTO;
import com.example.testknowledge.DTO.QuestionDTO;
import com.example.testknowledge.FontChangeCrawler;
import com.example.testknowledge.Notification.AlarmReceiver;
import com.example.testknowledge.R;

import java.util.Calendar;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class FragmentClock extends Fragment {
    private Spinner spinnerDifficulty;
    private Spinner spinnerCategory;
    private TextView txtShowName,txtShowTime;
    private EditText edName;
    Button btnHenGio,btnDunglai;
    //TIMER====
    TimePicker timePicker;
    Calendar calendar;
    AlarmManager alarmManager;
    Intent intent;
    PendingIntent pendingIntent;
    String strdate="";
    String strname="";
    //===========================
    //NOTIFICATION=====================
    //=================================
    public static final String SHARE_PREFES_STATE_BUTTON_CLOCK="statebuttonclock";
    public static final String BTN_HENGIO="btnhengio";
    public static final String BTN_DUNGLAT="btndunglai";
    public static final String ED_NAME="ed_name";
    public static final String TIME_PICKER="time_picker";
    public static final String DIFFICULTY="difficulty";
    public static final String CATEGORY="category";
    public static final String NAME_EXAM="nameexam";
    public static final String DATA_EXAM="dateexam";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_clock,container,false);
        Integer fontRes = getActivity().getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("font", R.font.lato);
        FontChangeCrawler fontChanger = new FontChangeCrawler(getActivity(), fontRes);
        fontChanger.replaceFonts((ViewGroup) view.getRootView());
        timePicker=view.findViewById(R.id.timepicker);
        btnHenGio=view.findViewById(R.id.hengioClock);
        btnDunglai=view.findViewById(R.id.dunglaiClock);
        edName=view.findViewById(R.id.edName_clock);
        txtShowName=view.findViewById(R.id.txtName_Clock);
        txtShowTime=view.findViewById(R.id.txtTime_Clock);
        spinnerCategory=view.findViewById(R.id.spinner_category_clock);
        spinnerDifficulty=view.findViewById(R.id.spinner_difficulty_clock);
        alarmManager= (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        calendar=Calendar.getInstance();
        intent=new Intent(getActivity(), AlarmReceiver.class);
        loadCategory();
        loadDifficultyLevel();
        //BUTTON_SATE
        /*btnHenGio.setEnabled(true);
        btnDunglai.setEnabled(false);
        edName.setEnabled(true);
        timePicker.setEnabled(true);
        spinnerCategory.setEnabled(true);
        spinnerDifficulty.setEnabled(true);*/
        LoadData();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btnHenGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.set(Calendar.HOUR_OF_DAY,timePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE,timePicker.getCurrentMinute());
                int gio=timePicker.getCurrentHour();
                int phut=timePicker.getCurrentMinute();
                String str_gio=String.valueOf(gio);
                String str_phut=String.valueOf(phut);
                if(gio>=12)
                {
                    str_gio=String.valueOf(gio-12);
                }else
                {
                    str_phut="0"+String.valueOf(phut);
                }
                //GET DATA
                String name=edName.getText().toString();
                CategoryDTO selectedCategory= (CategoryDTO) spinnerCategory.getSelectedItem();
                int categoryID=selectedCategory.getId();
                String categoryName=selectedCategory.getName();
                String difficulty=spinnerDifficulty.getSelectedItem().toString();
                //=============================================
                //INTENT DATA=====
                intent.putExtra("NameClock",name);
                intent.putExtra("idCate_Clock",String.valueOf(categoryID));
                intent.putExtra("idDifficulty",difficulty);
                intent.putExtra("Category_Name",categoryName);

                //================
                pendingIntent=PendingIntent.getBroadcast(
                        getActivity(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT
                );

                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
                //==============================================
                txtShowName.setText(name);
                txtShowTime.setText(str_gio+":"+str_phut);
                strdate=str_gio+":"+str_phut;
                strname=name;
                //DISABLE==========
                btnHenGio.setEnabled(false);
                btnDunglai.setEnabled(true);
                edName.setEnabled(false);
                spinnerCategory.setEnabled(false);
                spinnerDifficulty.setEnabled(false);
                timePicker.setEnabled(false);
                SaveData(false,true,false,false,false,false);
            }
        });
        btnDunglai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pendingIntent!=null)
                {
                    pendingIntent.cancel();
                }

                btnHenGio.setEnabled(true);
                btnDunglai.setEnabled(false);
                edName.setEnabled(true);
                spinnerCategory.setEnabled(true);
                spinnerDifficulty.setEnabled(true);
                timePicker.setEnabled(true);
                txtShowName.setText("");
                txtShowTime.setText("");
                SaveData(true,false,true,true,true,true);
            }
        });
    }
    private void loadCategory()
    {
        CategoryDAO categoryDAO=new CategoryDAO(getActivity());
        categoryDAO.open();
        List<CategoryDTO> categoryDTOList=categoryDAO.getAllCategories();
        ArrayAdapter<CategoryDTO> categoryDTOArrayAdapter=new ArrayAdapter<>(getActivity(),
                R.layout.support_simple_spinner_dropdown_item,categoryDTOList);
        categoryDTOArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(categoryDTOArrayAdapter);
        categoryDAO.close();
    }
    private void loadDifficultyLevel()
    {
        String[] difficultyLevels= QuestionDTO.getAllDifficultyLevel();
        ArrayAdapter<String> adapterDifficulty=new ArrayAdapter<String>(getActivity(),
                R.layout.support_simple_spinner_dropdown_item,difficultyLevels);
        adapterDifficulty.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerDifficulty.setAdapter(adapterDifficulty);
    }
    private void SaveData(boolean btnhengio,boolean btndunglai,boolean ed_name,boolean category,boolean difficulty,boolean time_picker)
    {
        SharedPreferences sharedPreferences=getContext().getSharedPreferences(SHARE_PREFES_STATE_BUTTON_CLOCK,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean(BTN_HENGIO,btnhengio);
        editor.putBoolean(BTN_DUNGLAT,btndunglai);
        editor.putBoolean(ED_NAME,ed_name);
        editor.putBoolean(CATEGORY,category);
        editor.putBoolean(DIFFICULTY,difficulty);
        editor.putBoolean(TIME_PICKER,time_picker);
        editor.putString(DATA_EXAM,strdate);
        editor.putString(NAME_EXAM,strname);
        editor.apply();
    }
    private void LoadData()
    {
        SharedPreferences sharedPreferences=getContext().getSharedPreferences(SHARE_PREFES_STATE_BUTTON_CLOCK,MODE_PRIVATE);
        btnHenGio.setEnabled(sharedPreferences.getBoolean(BTN_HENGIO,true));
        btnDunglai.setEnabled(sharedPreferences.getBoolean(BTN_DUNGLAT,false));
        edName.setEnabled(sharedPreferences.getBoolean(ED_NAME,true));
        timePicker.setEnabled(sharedPreferences.getBoolean(TIME_PICKER,true));
        spinnerCategory.setEnabled(sharedPreferences.getBoolean(CATEGORY,true));
        spinnerDifficulty.setEnabled(sharedPreferences.getBoolean(DIFFICULTY,true));
        txtShowName.setText(sharedPreferences.getString(NAME_EXAM,""));
        txtShowTime.setText(sharedPreferences.getString(DATA_EXAM,""));

    }
}
