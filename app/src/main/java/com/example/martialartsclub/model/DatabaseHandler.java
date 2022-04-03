package com.example.martialartsclub.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "martialArtsDatabase";
    private static final int DATABASE_VERSION = 1;
    private static final String MARTIAL_ARTS_TABLE = "MartialArts";
    private static final String ID_KEY = "id";
    private static final String NAME_KEY = "name";
    private static final String PRICE_KEY = "price" ;
    private static final String COLOR_KEY = "color";

    public DatabaseHandler(Context context) {
        super(context ,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDatabaseSQL = "CREATE TABLE " + MARTIAL_ARTS_TABLE + " ( "+
                ID_KEY + " INTEGER  PRIMARY KEY  AUTOINCREMENT" +
                " , " + NAME_KEY + " TEXT " +
                " , " + PRICE_KEY + " REAL " +
                " , " + COLOR_KEY + " TEXT " + " ) " ;

        // use to execute the sql cmmd
        db.execSQL(createDatabaseSQL);
    }


    // called when you upgrade your database;

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MARTIAL_ARTS_TABLE);
        onCreate(db);
    }

    // user methods

    public void addMartialArt(MartialArt martialArt) {
        SQLiteDatabase database = getWritableDatabase();
        String addMarticalArtSQLCommand = "INSERT INTO " + MARTIAL_ARTS_TABLE + " ( " +
                NAME_KEY + " , " + PRICE_KEY + " , " + COLOR_KEY + " ) " +
                " VALUES " + " ( '" + martialArt.getMartialArtName() + "' , '" +
                martialArt.getMartialArtPrice() + "' , '" +
                martialArt.getMartialArtColor() + "' ) ";
        database.execSQL(addMarticalArtSQLCommand);

        // then we must close database
        database.close();
    }

    public void deleteMartialArtTupleFromDatabaseByID(int id) {
        SQLiteDatabase database = getWritableDatabase();
        String deleteMartialArtSQLCommand = "DELETE FROM " + MARTIAL_ARTS_TABLE +
                " WHERE " + ID_KEY + " = " + id ;

        database.execSQL(deleteMartialArtSQLCommand);
        database.close();
    }

    public void modifyMartialArtTuple(int martialArtID , String martialArtName) {

    }
}
