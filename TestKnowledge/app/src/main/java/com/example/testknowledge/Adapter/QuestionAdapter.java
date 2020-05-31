package com.example.testknowledge.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.testknowledge.DTO.QuestionDTO;
import com.example.testknowledge.R;

import java.util.ArrayList;

public class QuestionAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<QuestionDTO> questionDTOArrayList;

    public QuestionAdapter(Context context, int layout, ArrayList<QuestionDTO> questionDTOArrayList) {
        this.context = context;
        this.layout = layout;
        this.questionDTOArrayList = questionDTOArrayList;
    }

    @Override
    public int getCount() {
        return questionDTOArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return questionDTOArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private class ViewHolder
    {
        TextView txtQuestion;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null)
        {
            holder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(layout,null);
            holder.txtQuestion=convertView.findViewById(R.id.textviewQuestion);
            convertView.setTag(holder);
        }else
        {
            holder= (ViewHolder) convertView.getTag();
        }
        QuestionDTO questionDTO=questionDTOArrayList.get(position);
        holder.txtQuestion.setText(questionDTO.getQuestion());

        return convertView;
    }
}
