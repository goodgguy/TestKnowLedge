package com.example.testknowledge.AsynTask;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.example.testknowledge.Covid19Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

public class sumaryCovid19  extends AsyncTask <Void,Void,Void>{
    String data="";
    int id;
    String url="https://disease.sh/v2/all";
    TextView txtbinhiem,txttuvong,txtbinhphuc;
    JSONObject jsonObject;
    String binhiem="";
    String tuvong="";
    String binhphuc="";

    public sumaryCovid19(TextView txtbinhiem,TextView txttuvong,TextView txtbinhphuc)
    {
        this.txtbinhiem=txtbinhiem;
        this.txtbinhphuc=txtbinhphuc;
        this.txttuvong=txttuvong;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url=new URL(this.url);
            HttpsURLConnection httpsURLConnection= (HttpsURLConnection) url.openConnection();
            InputStream inputStream=httpsURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            String line="";
            while (line!=null)
            {
                line=bufferedReader.readLine();
                data=data+line;
            }
            Log.e("DATA",data);
            jsonObject=new JSONObject(data);
            binhiem=jsonObject.getString("cases");
            tuvong=jsonObject.getString("deaths");
            binhphuc=jsonObject.getString("recovered");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        txtbinhiem.setText(binhiem);
        txttuvong.setText(tuvong);
        txtbinhphuc.setText(binhphuc);
    }
}
