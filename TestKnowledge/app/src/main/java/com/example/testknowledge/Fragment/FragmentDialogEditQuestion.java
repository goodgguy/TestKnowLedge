package com.example.testknowledge.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.testknowledge.DAO.CategoryDAO;
import com.example.testknowledge.DAO.QuestionDAO;
import com.example.testknowledge.DTO.CategoryDTO;
import com.example.testknowledge.DTO.QuestionDTO;
import com.example.testknowledge.R;

import java.util.List;


public class FragmentDialogEditQuestion extends DialogFragment {
    Button Edit;
    EditText edQuestion,edAnswer1,edAnswer2,edAnswer3;
    RadioGroup rdgAddquestion;
    private Spinner spinnerDifficulty;
    private Spinner spinnerCategory;
    QuestionDTO questionDTO;
    ArrayAdapter<String> adapterDifficulty;
    public static FragmentDialogEditQuestion newInstance()
    {
        return new FragmentDialogEditQuestion();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_dialog_edit,container,false);
        Edit=view.findViewById(R.id.btn_edit);
        edQuestion=view.findViewById(R.id.inpt_edit_question);
        edAnswer1=view.findViewById(R.id.edit_answer1);
        edAnswer2=view.findViewById(R.id.edit_answer2);
        edAnswer3=view.findViewById(R.id.edit_answer3);
        rdgAddquestion=view.findViewById(R.id.radiogroup_editquestion);
        spinnerCategory=view.findViewById(R.id.spinner_category_edit);
        spinnerDifficulty=view.findViewById(R.id.spinner_difficulty_edit);
        filldata();
        questionDTO= (QuestionDTO) getArguments().getSerializable("Question");
        edQuestion.setText(questionDTO.getQuestion());
        edAnswer1.setText(questionDTO.getOption1());
        edAnswer2.setText(questionDTO.getOption2());
        edAnswer3.setText(questionDTO.getOption3());
        spinnerCategory.setSelection(questionDTO.getCategoryID()-1);
        int spinnerPosition1 = adapterDifficulty.getPosition(questionDTO.getDifficulty());
        Log.e("spinnerPosition1",String.valueOf(spinnerPosition1));
        spinnerDifficulty.setSelection(spinnerPosition1);
        ((RadioButton)rdgAddquestion.getChildAt(questionDTO.getAnswer()-1)).setChecked(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question=edQuestion.getText().toString().trim();
                String asw1=edAnswer1.getText().toString().trim();
                String asw2=edAnswer2.getText().toString().trim();
                String asw3=edAnswer3.getText().toString().trim();
                CategoryDTO selectedCategory= (CategoryDTO) spinnerCategory.getSelectedItem();
                int categoryID=selectedCategory.getId();
                String difficulty=spinnerDifficulty.getSelectedItem().toString();
                //====RADIOBUTTTON
                int id=rdgAddquestion.getCheckedRadioButtonId();
                View radioButton=rdgAddquestion.findViewById(id);
                int radioId=rdgAddquestion.indexOfChild(radioButton);
                RadioButton btn = (RadioButton) rdgAddquestion.getChildAt(radioId);
                String selection = (String) btn.getText();
                Log.e("TEST TEST",selection);
                int choice=Integer.parseInt(selection);
                //=======================



                QuestionDTO questionDTO1=new QuestionDTO(question,asw1,asw2,asw3,choice,difficulty,categoryID);
                questionDTO1.setId(questionDTO.getId());
                QuestionDAO questionDAO=new QuestionDAO(getActivity());
                questionDAO.open();
                questionDAO.updateQuestion(questionDTO1);
                questionDAO.close();
                dismiss();
            }
        });
    }

    private void filldata() {
        CategoryDAO categoryDAO=new CategoryDAO(getActivity());
        categoryDAO.open();
        List<CategoryDTO> categoryDTOList=categoryDAO.getAllCategories();
        ArrayAdapter<CategoryDTO> categoryDTOArrayAdapter=new ArrayAdapter<>(getActivity(),
                R.layout.support_simple_spinner_dropdown_item,categoryDTOList);
        categoryDTOArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(categoryDTOArrayAdapter);
        categoryDAO.close();

        String[] difficultyLevels= QuestionDTO.getAllDifficultyLevel();
        adapterDifficulty=new ArrayAdapter<String>(getActivity(),
                R.layout.support_simple_spinner_dropdown_item,difficultyLevels);
        adapterDifficulty.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerDifficulty.setAdapter(adapterDifficulty);
    }
}
