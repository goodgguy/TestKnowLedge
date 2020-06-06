package com.example.testknowledge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.testknowledge.Fragment.FragmentAddQuestion;
import com.example.testknowledge.Fragment.FragmentClock;
import com.example.testknowledge.Fragment.FragmentlistCategory;
import com.example.testknowledge.Fragment.Fragmentlistquestion;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ManagerActivity_Navigation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager__navigation);
        BottomNavigationView bottomNav=findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navLister);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_nav,
                new Fragmentlistquestion()).commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navLister=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment SelectedFragment=null;
                    switch (menuItem.getItemId())
                    {
                        case R.id.nav_questionlist:
                            SelectedFragment=new Fragmentlistquestion();
                            break;
                        case R.id.nav_categorylist:
                            SelectedFragment=new FragmentlistCategory();
                            break;
                        case R.id.nav_add:
                            SelectedFragment=new FragmentAddQuestion();
                            break;
                        case R.id.nav_clock:
                            SelectedFragment=new FragmentClock();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_nav,
                            SelectedFragment).commit();
                    return true;
                }
            };
}
