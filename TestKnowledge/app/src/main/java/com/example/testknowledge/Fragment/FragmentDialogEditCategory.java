package com.example.testknowledge.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.testknowledge.DAO.CategoryDAO;
import com.example.testknowledge.DTO.CategoryDTO;
import com.example.testknowledge.FontChangeCrawler;
import com.example.testknowledge.R;

import static android.content.Context.MODE_PRIVATE;

public class FragmentDialogEditCategory extends DialogFragment {
    Button btnEditCategory;
    EditText edEditCategory;
    CategoryDTO categoryDTO;
    public static FragmentDialogEditCategory newInstance()
    {
        return new FragmentDialogEditCategory();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_dialog_edit_category,container,false);
        Integer fontRes = getActivity().getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("font", R.font.lato);
        FontChangeCrawler fontChanger = new FontChangeCrawler(getActivity(), fontRes);
        fontChanger.replaceFonts((ViewGroup) view.getRootView());
        edEditCategory=view.findViewById(R.id.inpt_edit_category);
        btnEditCategory=view.findViewById(R.id.btn_edit_category);
        categoryDTO= (CategoryDTO) getArguments().getSerializable("Category");
        Log.e("CATEGORY",categoryDTO.getName());
        edEditCategory.setText(categoryDTO.getName());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btnEditCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CategoryDTO categoryDTO1=new CategoryDTO();
                categoryDTO1.setName(edEditCategory.getText().toString());
                categoryDTO1.setId(categoryDTO.getId());
                CategoryDAO categoryDAO=new CategoryDAO(getActivity());
                categoryDAO.open();
                categoryDAO.updateCategory(categoryDTO1);
                categoryDAO.close();
                dismiss();
            }
        });
    }
}
