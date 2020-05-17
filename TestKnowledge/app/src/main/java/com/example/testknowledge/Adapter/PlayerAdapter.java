package com.example.testknowledge.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testknowledge.DTO.PlayerDTO;
import com.example.testknowledge.R;

import java.util.ArrayList;
import java.util.Random;

public class PlayerAdapter extends BaseAdapter {
    Context context;
    ArrayList<PlayerDTO> playerDTOArrayList;
    int layout;


    public PlayerAdapter(Context context, ArrayList<PlayerDTO> playerDTOArrayList, int layout) {
        this.context = context;
        this.playerDTOArrayList = playerDTOArrayList;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return playerDTOArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return playerDTOArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public static class ViewHolder{
        private TextView txtName,txtPoint;
        private ImageView imgAvatar,imgCup;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewrow=convertView;
        if(viewrow==null)
        {
            viewrow=inflater.inflate(layout,parent,false);
            ViewHolder holder=new ViewHolder();
            holder.txtName=viewrow.findViewById(R.id.txtnamePlayer);
            holder.txtPoint=viewrow.findViewById(R.id.txtpointPlayer);
            holder.imgAvatar=viewrow.findViewById(R.id.imagePlayer);
            holder.imgCup=viewrow.findViewById(R.id.imagecupPlayer);
            viewrow.setTag(holder);
        }
        ViewHolder holder= (ViewHolder) viewrow.getTag();
        holder.txtName.setText(playerDTOArrayList.get(position).getName());
        holder.txtPoint.setText(String.valueOf(playerDTOArrayList.get(position).getPoint()));
        holder.imgAvatar.setImageResource(playerDTOArrayList.get(position).getImage());
        if(position==0)
        {
            holder.imgCup.setImageResource(R.drawable.goldcup);
        }else if (position==1)
        {
            holder.imgCup.setImageResource(R.drawable.silvercup2);
        }
        else
        {
            holder.imgCup.setImageResource(R.drawable.silvercup);
        }


        return viewrow;
    }
}
