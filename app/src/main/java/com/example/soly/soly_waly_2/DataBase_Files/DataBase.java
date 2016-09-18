package com.example.soly.soly_waly_2.DataBase_Files;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by Soliman Waly on 16/09/2016.
 */
class DataBase extends SQLiteOpenHelper {
    static final String dataBase_Name = "FavouriteDB";
    static final String table_Name = "Favourite";
    static final int dataBase_Version = 1;
    static final String id = "id";
    static final String URL = "url";


    private static final String Drop_Table = "Drop table if exists " + table_Name;
    private static final String Create_Table = "create table " + table_Name + "(" + id + " Integer, " + URL + " text);";

    private Context context;

    public DataBase(Context context) {
        super(context, dataBase_Name, null, dataBase_Version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(Create_Table);
        } catch (SQLiteException e) {
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(Drop_Table);
            onCreate(db);
    }
}
