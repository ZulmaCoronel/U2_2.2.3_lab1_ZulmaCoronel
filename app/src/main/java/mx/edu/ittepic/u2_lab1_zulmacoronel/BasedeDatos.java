package mx.edu.ittepic.u2_lab1_zulmacoronel;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Zulma on 13/03/2018.
 */

public class BasedeDatos extends SQLiteOpenHelper {
    public BasedeDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE CLIENTE" +
                "(" +
                "IDCLIENTE INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "NOMBRE VARCHAR(200)," +
                "DOMICILIO VARCHAR(400)," +
                "COLONIA VARCHAR(100)" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}


