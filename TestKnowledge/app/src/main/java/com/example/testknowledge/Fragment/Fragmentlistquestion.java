package com.example.testknowledge.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.ListFragment;

import com.example.testknowledge.Adapter.QuestionAdapter;
import com.example.testknowledge.DAO.QuestionDAO;
import com.example.testknowledge.DTO.QuestionDTO;
import com.example.testknowledge.FontChangeCrawler;
import com.example.testknowledge.R;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class Fragmentlistquestion extends ListFragment {
    ArrayList<QuestionDTO> questionDTOArrayList;
    QuestionAdapter questionAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_question_list,container,false);
        Integer fontRes = getActivity().getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("font", R.font.lato);
        FontChangeCrawler fontChanger = new FontChangeCrawler(getActivity(), fontRes);
        fontChanger.replaceFonts((ViewGroup) view.getRootView());
        questionDTOArrayList=new ArrayList<>();
        getArrayQuestion();
        questionAdapter=new QuestionAdapter(getActivity(),R.layout.row_question,questionDTOArrayList);
        setListAdapter(questionAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DialogDelete(position);
                return false;
            }
        });
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle=new Bundle();
                bundle.putSerializable("Question",questionDTOArrayList.get(position));
                DialogFragment dialogFragment= FragmentDialogEditQuestion.newInstance();

                dialogFragment.setArguments(bundle);
                dialogFragment.show(getFragmentManager(),"tag");
            }
        });
    }

    private void DialogDelete(final int position)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("Thông báo!");
        builder.setMessage("Bạn có muốn xóa Question này");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                QuestionDAO questionDAO=new QuestionDAO(getActivity());
                questionDAO.open();
                questionDAO.deleteQuestion(questionDTOArrayList.get(position).getId());
                questionDTOArrayList.remove(position);
                questionAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }
    private void getArrayQuestion()
    {
        QuestionDAO questionDAO=new QuestionDAO(getActivity());
        questionDAO.open();
        questionDTOArrayList=questionDAO.getAllQuestion();
        questionDAO.close();
    }
}
