package com.example.testknowledge;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.testknowledge.R;

public class PlayerDialog extends AppCompatDialogFragment {
    private EditText edPlayername;
    private PlayerDialogListener listener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable final Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.layout_dialog,null);
        edPlayername=view.findViewById(R.id.edName);

        builder.setView(view)
                .setTitle("Nhập tên")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name=edPlayername.getText().toString();
                        listener.applyTexts(name);
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener= (PlayerDialogListener) context;
        }catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString()+"must implement playerdialog listener");
        }
    }

    public interface PlayerDialogListener
    {
        void applyTexts(String name);
    }
}
