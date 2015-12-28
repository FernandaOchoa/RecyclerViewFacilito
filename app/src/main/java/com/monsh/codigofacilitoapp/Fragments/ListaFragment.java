package com.monsh.codigofacilitoapp.Fragments;

import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.monsh.codigofacilitoapp.Adapters.ListaAdapter;
import com.monsh.codigofacilitoapp.Helpers.DataBaseHelper;
import com.monsh.codigofacilitoapp.R;

import java.io.IOException;


/**
 * Created by monsh on 01/12/2015.
 */
public class ListaFragment extends Fragment {

    RecyclerView recyclerView;
    //Creo mi tag para el recyclerview
    private static final String TAG = "RecyclerViewFragment";
    ListaAdapter adapter;
    //1
    DataBaseHelper myDBHelper;


    //Creo mi arreglo de cadenas
    //  String[] Data = new String[]{"Elemento 1","Elemento 2","Elemento 3","Elemento 4", "Elemento 5,","Elemento 6","Elemento 7","Elemento 8","Elemento 9", "Elemento 10"};


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.lista_fragment, container, false);
        rootView.setTag(TAG);

        //Asi definimos un recyclerview en un fragment
        recyclerView = (RecyclerView) rootView.findViewById(R.id.lista);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        //Llenamos el RecyclerView 1 definimos el databasehelper
        //2 le decimos al dbhelper la ubicacion donde sera usado
        myDBHelper = new DataBaseHelper(getActivity().getApplicationContext());

        //3 verificamos si ya esta creada la base de datos y lo manejamos con un try catch
        try {
            myDBHelper.createDataBase();
        } catch (IOException e) {
            throw new Error("No se puede crear la base de datos");
        }

        // El cursor se llena a partir de lo que consultamos en la base de datos

        //Ahora al cursor le digo que sera llenado de la base de datos

        try {
            //Abrimos la bd *importante
            myDBHelper.openDataBase();
            Cursor cursor = myDBHelper.fetchAllList();
            if (cursor != null) {
                adapter = new ListaAdapter(getActivity().getApplicationContext(), cursor);
                recyclerView.setAdapter(adapter);
            }
        } catch (SQLException e) {

        }

        return rootView;
    }
}
