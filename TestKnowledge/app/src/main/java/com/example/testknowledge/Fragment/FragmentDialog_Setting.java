package com.example.testknowledge.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.testknowledge.FontChangeCrawler;
import com.example.testknowledge.MainActivity;
import com.example.testknowledge.R;

import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class FragmentDialog_Setting extends DialogFragment {
    RadioGroup rgNgonNgu;
    RadioGroup rgFontChu;
    Button btnLuu;
    RadioButton rdbEn,rdbVi,rdbMD,rdbBM;
    public static FragmentDialog_Setting newInstance()
    {
        return new FragmentDialog_Setting();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL,R.style.FullScreenDialogTheme);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_dialog_setting,container,false);
        AnhXa(view);
        Integer fontRes = getActivity().getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("font", R.font.lato);
        FontChangeCrawler fontChanger = new FontChangeCrawler(getActivity(), fontRes);
        fontChanger.replaceFonts((ViewGroup) view.getRootView());
        rdbMD.setTypeface(ResourcesCompat.getFont(getActivity(), R.font.lato));
        rdbBM.setTypeface(ResourcesCompat.getFont(getActivity(), R.font.amatic));
        String language = getActivity().getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("language", Locale.getDefault().getLanguage());
        String country = getActivity().getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("country", Locale.getDefault().getCountry());
        if(language.equals("en")){
            rdbEn.setChecked(true);
        }
        if(language.equals("vi")){
            rdbVi.setChecked(true);
        }
        if(fontRes == R.font.lato){
            rdbMD.setChecked(true);
        }
        if(fontRes == R.font.amatic){
            rdbBM.setChecked(true);
        }
        setEvent();
        return view;
    }

    private void setEvent() {
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rdbEn.isChecked()){
                    onChangeLanguage(getActivity(), new Locale("en", "US"));
                }
                if(rdbVi.isChecked()){
                    onChangeLanguage(getActivity(), new Locale("vi", "VN"));
                }
                if(rdbMD.isChecked()){
                    onChangeFont(R.font.lato);
                }
                if(rdbBM.isChecked()){
                    onChangeFont(R.font.amatic);
                }
                dismiss();
                Intent refresh = new Intent(getActivity(), MainActivity.class);
                startActivity(refresh);
            }
        });
    }

    private void AnhXa(View view)
    {
        rgNgonNgu=view.findViewById(R.id.radioGroup_NgonNgu);
        rgFontChu=view.findViewById(R.id.radioGroup_FontChu);
        btnLuu=view.findViewById(R.id.btnLuu);
        rdbEn=view.findViewById(R.id.rdbEn);
        rdbVi=view.findViewById(R.id.rdbVi);
        rdbMD=view.findViewById(R.id.rdbMD);
        rdbBM=view.findViewById(R.id.rdbBM);
    }



    private void onChangeLanguage(Context context, Locale locale){
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

        Configuration configuration = new Configuration();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            configuration.setLocale(locale);
        }else{
            configuration.locale = locale;
        }

        getResources().updateConfiguration(configuration, displayMetrics);
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .edit()
                .putString("language", locale.getLanguage())
                .apply();
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .edit()
                .putString("country", locale.getCountry())
                .apply();
    }
    private void onChangeFont(Integer fontRes) {
        getActivity().getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .edit()
                .putInt("font", fontRes)
                .commit();
    }
}
