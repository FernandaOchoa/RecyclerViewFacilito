package com.monsh.codigofacilitoapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.monsh.codigofacilitoapp.Adapters.ListaAdapter;
import com.monsh.codigofacilitoapp.R;


/**
 * Created by monsh on 01/12/2015.
 */
public class ListaFragment extends Fragment {

    RecyclerView recyclerView;
    //Creo mi tag para el recyclerview
    private static final String TAG = "RecyclerViewFragment";
    ListaAdapter adapter;


    //Creo mi arreglo de cadenas
    String[] Data = new String[]{"Elemento 1","Elemento 2","Elemento 3","Elemento 4", "Elemento 5,","Elemento 6","Elemento 7","Elemento 8","Elemento 9", "Elemento 10"};


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.lista_fragment, container, false);
        rootView.setTag(TAG);

        //Asi definimos un recyclerview en un fragment
        recyclerView = (RecyclerView) rootView.findViewById(R.id.lista);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        //Definir el adaptador, (hacerlo)

        adapter = new ListaAdapter(Data);
        recyclerView.setAdapter(adapter);

        return rootView;
    }
}
