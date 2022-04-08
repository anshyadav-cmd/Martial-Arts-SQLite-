package com.example.martialartsclub.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "martialArtsDatabase";
    private static final int DATABASE_VERSION = 1;
    private static final String MARTIAL_ARTS_TABLE = "MartialArts";
    private static final String ID_KEY = "id";
    private static final String NAME_KEY = "name";
    private static final String PRICE_KEY = "price" ;
    private static final String COLOR_KEY = "color";

    /**
     * compulsory Constructor
     * @param context
     */
    public DatabaseHandler(Context context) {
        super(context ,DATABASE_NAME, null, DATABASE_VERSION);
    }


    /**
     * first method to be called and creating database
     * @param db
     */
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



    /**
     *    called when you upgrade your database;
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MARTIAL_ARTS_TABLE);
        onCreate(db);
    }


    /**
     * Adding new data in database
     *
     * @param martialArt
     */
    public void addMartialArt(MartialArt martialArt) {

        SQLiteDatabase database = getWritableDatabase();   // this.getWritableDatabase(), just because we are
        // extending from SQLiteHelper class that's why we don't have to write it like this

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

    /**
     * Modifing existing data in the database
     *
     * @param martialArtID
     * @param martialArtName
     * @param martialArtPrice
     * @param martialArtColor
     */
    public void modifyMartialArtTuple(int martialArtID , String martialArtName, double martialArtPrice , String martialArtColor ) {
        SQLiteDatabase database = getWritableDatabase();

        String modifyMartialArtSQLCommand = "UPDATE " + MARTIAL_ARTS_TABLE  + " SET " +
                NAME_KEY + " = '" + martialArtName + "' , " +
                PRICE_KEY + " = '" + martialArtPrice + "' , " +
                COLOR_KEY + " = '" + martialArtColor + "' " + "WHERE" +
                ID_KEY + " = '" + martialArtID + "' "  ;

        database.execSQL(modifyMartialArtSQLCommand);
        database.close();
    }


    /**
     * Retrieving All  data from the database (SQLite)
     *
     * @return List of martial arts
     */
    public ArrayList<MartialArt> returnAllMartialArtObject() {
        SQLiteDatabase database = getWritableDatabase();
        String sqlQueryCmd= "SELECT * FROM " + MARTIAL_ARTS_TABLE ;


        // TO PROCESS THIS SQL QUERY COMMAND WE A OBJECT OF AN CURSOR
        Cursor cursor = database.rawQuery(sqlQueryCmd , null);

        ArrayList<MartialArt> martialArts = new ArrayList<>();

        while(cursor.moveToNext()) {
            MartialArt currentMartialArt = new MartialArt(
                    Integer.getInteger(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getDouble(2),
                    cursor.getString(3)
            );
            martialArts.add(currentMartialArt);
        }
        database.close();
        return martialArts;
    }

    /**
     * Retrieving Single data (tuple) from database by id
     *
     * @param id
     * @return martial art object
     */
    public MartialArt returnMartialArtObjectById(int id) {
        SQLiteDatabase database = getWritableDatabase();
        String sqlQueryCode = "SELECT * FROM " + MARTIAL_ARTS_TABLE + " WHERE " + ID_KEY + " = " + id ;

        Cursor cursor = database.rawQuery(sqlQueryCode, null);

        MartialArt martialArtObject = null;

        if(cursor.moveToFirst()) {
            martialArtObject = new MartialArt(
                    Integer.getInteger(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getDouble(2),
                    cursor.getString(3)
            );
        }
        return  martialArtObject;
    }
}
