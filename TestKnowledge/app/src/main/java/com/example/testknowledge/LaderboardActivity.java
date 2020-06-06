package com.example.testknowledge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.testknowledge.Adapter.PlayerAdapter;
import com.example.testknowledge.DAO.PlayerDAO;
import com.example.testknowledge.DTO.PlayerDTO;

import java.util.ArrayList;

public class LaderboardActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<PlayerDTO> playerDTOArrayList;
    PlayerAdapter playerAdapter;
    PlayerDAO playerDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laderboard);
        Integer fontRes = this.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("font", R.font.lato);
        FontChangeCrawler fontChanger = new FontChangeCrawler(this, fontRes);
        fontChanger.replaceFonts((ViewGroup) getWindow().getDecorView().getRootView());
        AnhXa();
    }

    private void AnhXa() {
        playerDAO=new PlayerDAO(this);
        listView=findViewById(R.id.listviewLaderboard);
        playerDTOArrayList=new ArrayList<>();
        playerDAO.open();
        playerDTOArrayList=playerDAO.getAllplayer();
        playerAdapter=new PlayerAdapter(getApplicationContext(),playerDTOArrayList,R.layout.custom_listview_player);
        listView.setAdapter(playerAdapter);
    }
}
