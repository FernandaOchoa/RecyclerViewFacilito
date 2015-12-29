package com.monsh.codigofacilitoapp.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.monsh.codigofacilitoapp.R;


//Adapter que recibe cadenas

/**
 * Created by monsh on 27/12/2015.
 */
public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.ViewHolder> {
    Context myContext;
    //Los cursores necesitan un adaptador
    //Creamos un Adaptador para el RecyclerView y el cursor necesita uno propio
    //Declaramos el cursor del adaptador y uno del recyclerview

    CursorAdapter myCursorAdapter;
    // private String[]myData;

    public ListaAdapter(Context context, Cursor cursor) {
        myContext = context;
        myCursorAdapter = new CursorAdapter(myContext,cursor,0) {

            //
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {

                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                View retView = inflater.inflate(R.layout.item, parent, false);

                return retView;
            }

            //
            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TextView textView = (TextView) view.findViewById(R.id.titulo);
                TextView listanombre = (TextView) view.findViewById(R.id.listanombre);

                textView.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))));
                listanombre.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2))));
            }
        };
    }

    //Aqui se crea el ViewHolder

    //Iniciamos la vista con los elementos que tenemos
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    //
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //holder.textView.setText(myData[position]);

        myCursorAdapter.getCursor().moveToPosition(position);
        myCursorAdapter.bindView(holder.itemView,myContext,myCursorAdapter.getCursor());

    }

    //Nos regresa que numero de elemento es nuestro item
    @Override
    public int getItemCount() {
        //return myData.length;
        return myCursorAdapter.getCount();
    }

    //Aqui creamos nuestra clase viewholder, la necesitamos para
    //manipular, seleccionar y mantener los datos para que no se pierdan.
    //Tambien para que siempre esten disponibles.
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView, listanombre;

        public ViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.titulo);
            listanombre = (TextView) itemView.findViewById(R.id.listanombre);


        }
    }
}
