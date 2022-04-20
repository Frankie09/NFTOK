package com.frank.trpam;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.ListFragment;


import android.view.View;

import android.widget.ListView;

import java.util.ArrayList;


public class ListFrag extends ListFragment {

    private JudulListener judulListener;

    ArrayList<Shoplist> shoplist;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        setListAdapter(new ListAdapter(getActivity(), R.layout.item,Ipsum.listData));

    }

    public interface JudulListener{
        public void onJudulSelected(int index);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            judulListener = (JudulListener) context;
        }catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnJudulListener");
        }
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        judulListener.onJudulSelected(position);
    }



}