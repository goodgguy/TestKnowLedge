package com.example.testknowledge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testknowledge.AsynTask.Covid19AsynTask;
import com.example.testknowledge.AsynTask.sumaryCovid19;

import org.json.JSONObject;

public class Covid19Activity extends AppCompatActivity  {
    TextView txtbinhiem,txttuvong,txtbinhphuc;
    TextView txtbinhiemVN,txttuvongVN,txtbinhphucVN;
    TextView txtbinhiemUSA,txttuvongUSA,txtbinhphucUSA;
    TextView txtbinhiemBrazil,txttuvongBrazil,txtbinhphucBrazil;
    TextView txtbinhiemRussia,txttuvongRussia,txtbinhphucRussia;
    TextView txtbinhiemSpain,txttuvongSpain,txtbinhphucSpain;

    String urlVN="https://disease.sh/v2/countries/vietnam";
    String urlUSA="https://disease.sh/v2/countries/usa";
    String urlBrazil="https://disease.sh/v2/countries/brazil";
    String urlRussia="https://disease.sh/v2/countries/russia";
    String urlSpain="https://disease.sh/v2/countries/spain";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid19);
        Integer fontRes = this.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("font", R.font.lato);
        FontChangeCrawler fontChanger = new FontChangeCrawler(this, fontRes);
        fontChanger.replaceFonts((ViewGroup) getWindow().getDecorView().getRootView());
        AnhXa();
        runAsynTask();
    }
    public void runAsynTask()
    {
        sumaryCovid19 sumaryCovid19=new sumaryCovid19(txtbinhiem,txttuvong,txtbinhphuc);
        sumaryCovid19.execute();
        Covid19AsynTask covid19AsynTaskVN=new Covid19AsynTask(txtbinhiemVN,txttuvongVN,txtbinhphucVN);
        covid19AsynTaskVN.execute(urlVN);
        Covid19AsynTask covid19AsynTaskUSA=new Covid19AsynTask(txtbinhiemUSA,txttuvongUSA,txtbinhphucUSA);
        covid19AsynTaskUSA.execute(urlUSA);
        Covid19AsynTask covid19AsynTaskBrazil=new Covid19AsynTask(txtbinhiemBrazil,txttuvongBrazil,txtbinhphucBrazil);
        covid19AsynTaskBrazil.execute(urlBrazil);
        Covid19AsynTask covid19AsynTaskRussia=new Covid19AsynTask(txtbinhiemRussia,txttuvongRussia,txtbinhphucRussia);
        covid19AsynTaskRussia.execute(urlRussia);
        Covid19AsynTask covid19AsynTaskSpain=new Covid19AsynTask(txtbinhiemSpain,txttuvongSpain,txtbinhphucSpain);
        covid19AsynTaskSpain.execute(urlSpain);
    }

    private void AnhXa() {
        //WORLD
        txtbinhiem=findViewById(R.id.txtbinhiem);
        txtbinhphuc=findViewById(R.id.txtbinhphuc);
        txttuvong=findViewById(R.id.txttuvong);
        //VIETNAM
        txtbinhiemVN=findViewById(R.id.txtbinhiemVN);
        txttuvongVN=findViewById(R.id.txttuvongVN);
        txtbinhphucVN=findViewById(R.id.txtbinhphucVN);
        //USA
        txtbinhiemUSA=findViewById(R.id.txtbinhiemUSA);
        txttuvongUSA=findViewById(R.id.txttuvongUSA);
        txtbinhphucUSA=findViewById(R.id.txtbinhphucUSA);
        //Brazil
        txtbinhiemBrazil=findViewById(R.id.txtbinhiemBrazil);
        txttuvongBrazil=findViewById(R.id.txttuvongBrazil);
        txtbinhphucBrazil=findViewById(R.id.txtbinhphucBrazil);
        //Russia
        txtbinhiemRussia=findViewById(R.id.txtbinhiemRussia);
        txttuvongRussia=findViewById(R.id.txttuvongRussia);
        txtbinhphucRussia=findViewById(R.id.txtbinhphucRussia);
        //Spain
        txtbinhiemSpain=findViewById(R.id.txtbinhiemSpain);
        txttuvongSpain=findViewById(R.id.txttuvongSpain);
        txtbinhphucSpain=findViewById(R.id.txtbinhphucSpain);
    }


}
