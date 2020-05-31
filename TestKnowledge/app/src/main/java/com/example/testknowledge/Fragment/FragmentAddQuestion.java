package com.example.testknowledge.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import com.example.testknowledge.DAO.CategoryDAO;
import com.example.testknowledge.DAO.QuestionDAO;
import com.example.testknowledge.DTO.CategoryDTO;
import com.example.testknowledge.DTO.QuestionDTO;
import com.example.testknowledge.R;

import java.util.List;

public class FragmentAddQuestion extends Fragment {
    Button btnAdd;
    TextView txtQuestion,txtAnswer1,txtAnswer2,txtAnswer3;
    RadioGroup rdgAddquestion;
    private Spinner spinnerDifficulty;
    private Spinner spinnerCategory;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_add_question,container,false);
        btnAdd=view.findViewById(R.id.btn_add);
        txtQuestion=view.findViewById(R.id.inpt_add_question);
        txtAnswer1=view.findViewById(R.id.input_answer1);
        txtAnswer2=view.findViewById(R.id.input_answer2);
        txtAnswer3=view.findViewById(R.id.input_answer3);
        rdgAddquestion=view.findViewById(R.id.radiogroup_addquestion);
        spinnerCategory=view.findViewById(R.id.spinner_category_add);
        spinnerDifficulty=view.findViewById(R.id.spinner_difficulty_add);
        loadCategoryandDifficulty();
        return view;
    }

    private void loadCategoryandDifficulty() {
        CategoryDAO categoryDAO=new CategoryDAO(getActivity());
        categoryDAO.open();
        List<CategoryDTO> categoryDTOList=categoryDAO.getAllCategories();
        ArrayAdapter<CategoryDTO> categoryDTOArrayAdapter=new ArrayAdapter<>(getActivity(),
                R.layout.support_simple_spinner_dropdown_item,categoryDTOList);
        categoryDTOArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(categoryDTOArrayAdapter);
        categoryDAO.close();

        String[] difficultyLevels= QuestionDTO.getAllDifficultyLevel();
        ArrayAdapter<String> adapterDifficulty=new ArrayAdapter<String>(getActivity(),
                R.layout.support_simple_spinner_dropdown_item,difficultyLevels);
        adapterDifficulty.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerDifficulty.setAdapter(adapterDifficulty);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=rdgAddquestion.getCheckedRadioButtonId();
                if(id!=-1)
                {
                    View radioButton=rdgAddquestion.findViewById(id);
                    int radioId=rdgAddquestion.indexOfChild(radioButton);
                    RadioButton btn = (RadioButton) rdgAddquestion.getChildAt(radioId);
                    String selection = (String) btn.getText();
                    Log.e("TEST TEST",selection);
                    int choice=Integer.parseInt(selection);
                    String question=txtQuestion.getText().toString().trim();
                    String answer1=txtAnswer1.getText().toString().trim();
                    String answer2=txtAnswer2.getText().toString().trim();
                    String answer3=txtAnswer3.getText().toString().trim();
                    CategoryDTO selectedCategory= (CategoryDTO) spinnerCategory.getSelectedItem();
                    int categoryID=selectedCategory.getId();
                    String difficulty=spinnerDifficulty.getSelectedItem().toString();
                    QuestionDTO questionDTO=new QuestionDTO(question,answer1,answer2,answer3,choice,difficulty,categoryID);
                    QuestionDAO questionDAO=new QuestionDAO(getActivity());
                    questionDAO.open();
                    questionDAO.addQuestion(questionDTO);
                    questionDAO.close();
                    txtQuestion.setText("");
                    txtAnswer1.setText("");
                    txtAnswer2.setText("");
                    txtAnswer3.setText("");
                    Toast.makeText(getActivity(),"Thêm thành công",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
