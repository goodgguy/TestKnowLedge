package com.example.testknowledge.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.ListFragment;

import com.example.testknowledge.Adapter.CategoryAdapter;
import com.example.testknowledge.DAO.CategoryDAO;
import com.example.testknowledge.DTO.CategoryDTO;
import com.example.testknowledge.R;

import java.util.ArrayList;

public class FragmentlistCategory extends ListFragment {
    ArrayList<CategoryDTO> categoryDTOArrayList;
    CategoryAdapter categoryAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_category_list,container,false);
        categoryDTOArrayList=new ArrayList<>();
        getArrayCategory();
        categoryAdapter =new CategoryAdapter(getActivity(),R.layout.row_category,categoryDTOArrayList);
        setListAdapter(categoryAdapter);
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
                bundle.putSerializable("Category",categoryDTOArrayList.get(position));
                DialogFragment dialogFragment= FragmentDialogEditCategory.newInstance();

                dialogFragment.setArguments(bundle);
                dialogFragment.show(getFragmentManager(),"tag1");
            }
        });
    }
    private void DialogDelete(final int position)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("Thông báo!");
        builder.setMessage("Bạn có muốn xóa Category này");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CategoryDAO categoryDAO=new CategoryDAO(getActivity());
                categoryDAO.open();
                categoryDAO.deleteCategory(categoryDTOArrayList.get(position).getId());
                categoryDTOArrayList.remove(position);
                categoryAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }

    private void getArrayCategory() {
        CategoryDAO categoryDAO=new CategoryDAO(getActivity());
        categoryDAO.open();
        categoryDTOArrayList=categoryDAO.getAllCategories_array();
        categoryDAO.close();
    }
}
