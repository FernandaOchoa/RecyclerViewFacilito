package com.monsh.codigofacilitoapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.monsh.codigofacilitoapp.Fragments.ItemsFragment;
import com.monsh.codigofacilitoapp.Fragments.ListaFragment;
import com.monsh.codigofacilitoapp.R;

public class MainActivity extends AppCompatActivity implements ListaFragment.CallBacks {
    //Nos avisa cuando existen dos fragments
    public boolean mTwoPane;
    private static final String ELEMENTS_TAG = "ELEMENTS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Si R.id.listitem es igual a null entonces el panel se activa

        // a) Existe en la interfaz un elemento que tiene el id de list item??
        if (findViewById(R.id.list_item) != null) {
            mTwoPane = true;

            //FragmentManager se encarga de agregar o quitar fragments
            //Aqui voy a agregar un fragment solamente cuando exista espacio para hacerlo
            //Los fragment deben tener el mismo id, el frame es un fragment dinamico ->a

            //si el savedinstancestate esta vacio, entonces agregamos el fragment
            //de listitem, el tag se coloca para tener una memoria de lo que vamos haciendo

            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.list_item, new ItemsFragment(), ELEMENTS_TAG)
                        .commit();
            } else {
                //Si no hay dos paneles entonces estamos en un celular
                mTwoPane = false;
            }
        }
    }

    //Metodo para preguntar si hay dos fragments
    public boolean getTwoPane() {
        return this.mTwoPane;
    }

    @Override
    public void onItemSelected(String nombrelista, String lista) {
        if (mTwoPane){ //Cuando tenemos dos panel osea tablet
            Bundle bundle = new Bundle();
            bundle.putString("nombrelista",nombrelista);
            bundle.putString("lista",lista);

            ItemsFragment itemsFragment = new ItemsFragment();
            itemsFragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.list_item,itemsFragment)
                    .commit();

        }else {
            //En caso de que no haya dos panel entonces es un celular y nos vamos a otra activity
            Intent intent = new Intent(this,ItemList.class);
            intent.putExtra("nombrelista",nombrelista);
            intent.putExtra("lista",lista);
            startActivity(intent);
        }
    }
}
