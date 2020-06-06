package com.example.testknowledge;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.testknowledge.DAO.PlayerDAO;
import com.example.testknowledge.DTO.PlayerDTO;
import com.example.testknowledge.Fragment.FragmentAddQuestion;
import com.example.testknowledge.Fragment.FragmentClock;
import com.example.testknowledge.Fragment.FragmentlistCategory;
import com.example.testknowledge.Fragment.Fragmentlistquestion;

import java.util.Random;

public class ManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        Integer fontRes = this.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("font", R.font.lato);
        FontChangeCrawler fontChanger = new FontChangeCrawler(this, fontRes);
        fontChanger.replaceFonts((ViewGroup) getWindow().getDecorView().getRootView());

    }
    public void AddFragment(View view)
    {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        Fragment fragment=null;
        switch (view.getId())
        {
            case R.id.btnThemQuestion:
                fragment=new FragmentAddQuestion();
                break;
            case R.id.btnListQuestion:
                fragment=new Fragmentlistquestion();
                break;
            case R.id.btnListCategory:
                fragment=new FragmentlistCategory();
                break;
            case R.id.btnHengio:
                fragment=new FragmentClock();
                break;
        }
        fragmentTransaction.replace(R.id.FrameLayOut,fragment);
        fragmentTransaction.commit();
    }
    public void ThoatFragment(View view)
    {
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
