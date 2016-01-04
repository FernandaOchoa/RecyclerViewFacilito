package com.monsh.codigofacilitoapp.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.monsh.codigofacilitoapp.Fragments.ItemsFragment;
import com.monsh.codigofacilitoapp.R;

/**
 * Created by monsh on 28/12/2015.
 */
public class ItemList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemlist);

        //Recibimos un bundle (nos trae desde la activity anterior los datos)
        Bundle intent = getIntent().getExtras();

        if (savedInstanceState == null) {

            ItemsFragment fragment = new ItemsFragment();
            fragment.setArguments(intent);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.list_item, fragment)
                    .commit();
        }
    }
}
