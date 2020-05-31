package com.example.testknowledge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.testknowledge.Fragment.FragmentAddQuestion;
import com.example.testknowledge.Fragment.Fragmentlistquestion;

public class ManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

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
        }
        fragmentTransaction.replace(R.id.FrameLayOut,fragment);
        fragmentTransaction.commit();
    }
    public void ThoatFragment(View view)
    {
        finish();
    }
}
