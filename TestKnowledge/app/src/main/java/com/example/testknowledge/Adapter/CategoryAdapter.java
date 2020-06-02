package com.example.testknowledge.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.testknowledge.DTO.CategoryDTO;
import com.example.testknowledge.R;

import java.util.ArrayList;

public class CategoryAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<CategoryDTO> categoryDTOArrayList;

    public CategoryAdapter(Context context, int layout, ArrayList<CategoryDTO> categoryDTOArrayList) {
        this.context = context;
        this.layout = layout;
        this.categoryDTOArrayList = categoryDTOArrayList;
    }

    @Override
    public int getCount() {
        return categoryDTOArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryDTOArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private class ViewHolder
    {
        TextView txtCategory;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null)
        {
            holder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(layout,null);
            holder.txtCategory=convertView.findViewById(R.id.textviewCategory);
            convertView.setTag(holder);
        }
        else
        {
            holder= (ViewHolder) convertView.getTag();
        }
        CategoryDTO categoryDTO=categoryDTOArrayList.get(position);
        holder.txtCategory.setText(categoryDTO.getName());
        return convertView;
    }
}
