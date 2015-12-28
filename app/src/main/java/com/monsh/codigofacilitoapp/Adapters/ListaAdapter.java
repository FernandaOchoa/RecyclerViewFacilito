package com.monsh.codigofacilitoapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.monsh.codigofacilitoapp.R;

//Adapter que recibe cadenas
/**
 * Created by monsh on 27/12/2015.
 */
public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.ViewHolder> {
    Context context;

    private String[]myData;

    public ListaAdapter(String[]Data){
        myData = Data;
    }

    //Aqui se crea el ViewHolder

    //Iniciamos la vista con los elementos que tenemos
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()
        ).inflate(R.layout.item,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    //
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    holder.textView.setText(myData[position]);
    }

    //Nos regresa que numero de elemento es nuestro item
    @Override
    public int getItemCount() {
        return myData.length;
    }

    //Aqui creamos nuestra clase viewholder, la necesitamos para
    //manipular, seleccionar y mantener los datos para que no se pierdan.
    //Tambien para que siempre esten disponibles.
    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textView,listanombre;

        public ViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.titulo);
            listanombre = (TextView)itemView.findViewById(R.id.listanombre);


        }
    }
}
