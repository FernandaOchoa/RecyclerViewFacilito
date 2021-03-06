package com.monsh.codigofacilitoapp.Fragments;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.monsh.codigofacilitoapp.Helpers.DataBaseHelper;
import com.monsh.codigofacilitoapp.R;

import java.io.IOException;

/**
 * Created by monsh on 28/12/2015.
 */
public class ItemsFragment extends Fragment {

    ListView listView;
    TextView additem;
    String lista;

    SimpleCursorAdapter cursorAdapter;
    Cursor myCursor;

    String [] from;
    int [] to;

    DataBaseHelper myDBHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.items_fragment,container,false);

        listView = (ListView) rootView.findViewById(R.id.list_item);
        additem = (TextView) rootView.findViewById(R.id.additem);

        Bundle bundle = getArguments();

        if (bundle !=null){

            lista = (String) bundle.get("lista");

            //Creamos la base de datos y la ponemos a funcionar
            DataBaseHelper myDbHelper = new DataBaseHelper(getActivity());

            try {
                myDbHelper.createDataBase();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                //Siempre que usemos la bd hay que abrirla
                myDbHelper.openDataBase();

                myCursor = myDbHelper.fetchItemsList(lista);

                if (myCursor != null){
                    //Utilizamos el listview, le damos un adaptador
                    from = new String[]{"_id","item"};
                    to = new int[]{R.id.id,R.id.titulo_item};

                    cursorAdapter = new SimpleCursorAdapter(getActivity().getApplicationContext(),R.layout.item_list, myCursor, from, to);
                    listView.setAdapter(cursorAdapter);
                    //myDbHelper.close(); Despues de la consulta siempre hay que cerrar
                }

                } catch (SQLException e){
                e.printStackTrace();
            }
        }

        additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"Click 1",Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Agregar un Item");
                final EditText input = new EditText(getActivity());
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);
                //Boton Positivo
                builder.setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Aqui insertamos a la BD
                        DataBaseHelper myDbHelper = new DataBaseHelper(getActivity().getApplicationContext());
                        SQLiteDatabase db = myDbHelper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        //al content values le agregamos lo que queremos insetar
                        values.put("item",input.getText().toString());

                        db.insert(lista,null,values);
                        db.close();

                        //Para actualizar el listview de la bd, lo hacemos despues de insertar y cerrar la consulta
                        try{
                            // este chorizote se puede encapsular y usar ocasionalmente
                            myDbHelper.openDataBase();
                            Cursor cursor = myDbHelper.fetchItemsList(lista);
                            from = new String[]{"_id","item"};
                            to = new int[]{R.id.id,R.id.titulo_item};

                            cursorAdapter = new SimpleCursorAdapter(getActivity().getApplicationContext(),R.layout.item_list,cursor,from,to);
                            listView.setAdapter(cursorAdapter);

                        }catch (SQLException e){
                            e.printStackTrace();
                        }

                    }
                });
                //Boton Negativo
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.show();
            }

        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity(),"Click 2",Toast.LENGTH_SHORT).show();

                final TextView ID = (TextView) view.findViewById(R.id.id);

                AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
                adb.setTitle("Borrar");
                adb.setMessage("¿Seguro deseas eliminar el item?");
                adb.setNegativeButton("Cancelar",null);
                //El null es del listener no quiero que haga nada entonces no lo pongo, puedo poner un cancel aqui

                adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getActivity(),"Si si si", Toast.LENGTH_SHORT).show();
                        myDBHelper = new DataBaseHelper(getActivity().getApplicationContext());
                        SQLiteDatabase db = myDBHelper.getWritableDatabase();

                        //Otra forma de eliminar en base al id
                        String where = "_id = '"+ID.getText().toString()+"'";
                        db.delete(lista,where,null);
                        db.close();

                        try{
                            // este chorizote se puede encapsular y usar ocasionalmente
                            myDBHelper.openDataBase();
                            Cursor cursor = myDBHelper.fetchItemsList(lista);
                            from = new String[]{"_id","item"};
                            to = new int[]{R.id.id,R.id.titulo_item};

                            cursorAdapter = new SimpleCursorAdapter(getActivity().getApplicationContext(),R.layout.item_list,cursor,from,to);
                            listView.setAdapter(cursorAdapter);

                        }catch (SQLException e){
                            e.printStackTrace();
                        }
                    }
                });
                adb.show();

            }
        });

        return rootView;

    }
}
