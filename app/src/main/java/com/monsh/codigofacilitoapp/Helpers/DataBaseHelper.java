package com.monsh.codigofacilitoapp.Helpers;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by monsh on 27/12/2015.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    //BD_Path contiene la ruta de la base de datos y dbname el nombre de la base
    private static String DB_PATH = "/data/data/com.monsh.codigofacilitoapp/databases/";
    private static String DB_NAME = "bdlistas.sqlite";
    private final Context myContext;
    private SQLiteDatabase myDataBase;


    public DataBaseHelper(Context context) {
        super(context,DB_NAME,null,1);
        this.myContext = context;

    }

    //Como tengo una bd hecha entonces no lo hago en el OnCreate
    public void createDataBase() throws IOException {
        //Lo que me arroge checkdatabase

        //vamos al CheckDB y revisamos si existe
        boolean dbExist = checkDataBase();
        SQLiteDatabase db_Read = null;

        //Si existe no quiero hacer nada
        if (dbExist){

        }else { //Si no existe entonces la copiamos

            //Obtenemos la bd a copiar en modo lectura
            db_Read = this.getReadableDatabase();
            db_Read.close();

            try {
                //Copiamos la Base de Datos
                copyDataBase();
            } catch (IOException e){
                throw  new Error("Error copiando base de datos");
            }
        }
    }

    public boolean checkDataBase() {
        //Reviso si existe la bd
        SQLiteDatabase checkDB = null;

        //Abro la bd para ver que hay, como checkDB es un acceso a un archivo hay que controlarlo
        //con un try y catch por que podria arrojar errores
        try {

            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (Exception e) {
            //En la variable file guardo lo que encuentre, si existe la base y como se llama
            File dbFile = new File(DB_PATH + DB_NAME);
            return dbFile.exists();
        }
        if (checkDB != null)
            //Si es diferente a nulo entonces cerramos la BD
            checkDB.close();
            //Esa madre es un if mas pro
            return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException{
        //Copiamos la bd igual que un archivo

        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH+DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);

        //Para leer los archivos usamos un tipo byte por que nos permite tener un buffer
        //buffer bolsa temporal donde guardamos los datos con una medida

        byte[] buffer = new byte[1024];
        int lenght;

        //Leemos el archivo de la BD y lo traemos a traves de lenght
        //-1 quiere decir que ya no hay nada osea el fin
        //Con el while hacemos la copia
        while ((lenght = myInput.read(buffer)) != -1){
            if (lenght>0){
                myOutput.write(buffer,0,lenght);
            }
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDataBase()throws SQLException{
        String myPath = DB_PATH+DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READONLY);
    }
    public synchronized void close(){
        if (myDataBase !=null)
            myDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Colocar codigo SQL para Crear BD
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Codigo de la actualizacion
        //Para reescribir la app...
        try{
            createDataBase();
        }catch (IOException e){
          e.printStackTrace();
        }
    }

    //Este metodo nos da como resultado un cursor, cursor= resultado del query= consulta a la bd
    public Cursor fetchAllList() throws SQLException{

        //RawQuery = Metodo que nos permite ejecutar un query
        Cursor cursor = myDataBase.rawQuery("SELECT * FROM listas",null);

        //Si tiene algo el cursor que se mueva al primero
        if (cursor != null)
            cursor.moveToFirst();

        return cursor;
    }
}
