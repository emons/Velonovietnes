package com.velonovietnes.velonovietnes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {

    private static final String TAG = "DBAdapter"; //used for logging database version changes

    // Field Names:
    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_ADRESS = "adress";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_WEBSITE = "website";

    public static final String KEY_ROWID2 = "available_service_id";
    public static final String KEY_ROWID22 = "service_id";
    public static final String KEY_NAME2 = "available_service_name";
    public static final String KEY_DIFF = "difficulty";
    public static final String KEY_DESCRIPTION2 = "description";

    public static final String[] ALL_KEYS = new String[] {KEY_ROWID, KEY_NAME, KEY_ADRESS, KEY_DESCRIPTION, KEY_WEBSITE};

    // Column Numbers for each Field Name:
    public static final int COL_ROWID = 0;
    public static final int COL_NAME = 1;
    public static final int COL_ADRESS = 2;
    public static final int COL_DESCRIPTION = 3;


    // DataBase info:
    public static final String DATABASE_NAME = "dbVelo";
    public static final String DATABASE_TABLE = "mainService";
    public static final int DATABASE_VERSION = 1; // The version number must be incremented each time a change to DB structure occurs.

    //SQL statement to create database
    private static final String DATABASE_CREATE_SQL =
            "CREATE TABLE " + DATABASE_TABLE
                    + " (" + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KEY_NAME + " TEXT NOT NULL, "
                    + KEY_ADRESS + " TEXT, "
                    + KEY_DESCRIPTION + " TEXT, "
                    + KEY_WEBSITE + " TEXT"
                    + ");";

    private final Context context;
    private DatabaseHelper myDBHelper;
    private SQLiteDatabase db;


    public DBAdapter(Context ctx) {
        this.context = ctx;
        myDBHelper = new DatabaseHelper(context);
    }

    // Open the database connection.
    public DBAdapter open() {
        db = myDBHelper.getWritableDatabase();
        return this;
    }
    // Checks if the database is empty, if yes, populates it
    public void populateDB() {
        String count = "SELECT count(*) FROM mainService";
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if (icount > 0)
        return;
        else{
            String sql = "INSERT INTO mainService (name, adress, description) VALUES('Fans','A.Deglava 50','Lielakais veloservisu centrs riigaa!');";
            String sql2 = "INSERT INTO mainService (name, adress, description) VALUES('Gandrs','Kalnciema 28','Lielakais veloservisu centrs riigaa!');";
            String sql3 = "INSERT INTO mainService (name, adress, description) VALUES('ZZK','Ulmaņa gatve 201','Lielakais veloservisu centrs riigaa!');";
            String sql4 = "INSERT INTO mainService (name, adress, description) VALUES('XSports','Džutas iela 8','Lielakais veloservisu centrs riigaa!');";
            String sql5 = "INSERT INTO mainService (name, adress, description) VALUES('Hawaii Express','Biķernieku 11','Lielakais veloservisu centrs riigaa!');";
            String sql6 = "INSERT INTO mainService (name, adress, description) VALUES('RigaBike','Matīsa 8','Lielakais veloservisu centrs riigaa!');";
            String sql7 = "INSERT INTO mainService (name, adress, description) VALUES('Primum Bike','Brīvības gatve 390','Lielakais veloservisu centrs riigaa!');";
            db.execSQL(sql);
            db.execSQL(sql2);
            db.execSQL(sql3);
            db.execSQL(sql4);
            db.execSQL(sql5);
            db.execSQL(sql6);
            db.execSQL(sql7);
        }
    }

    // Close the database connection.
    public void close() {
        myDBHelper.close();
    }

    // Add a new set of values to be inserted into the database.
    public long insertRow(String name, String adress, String description, String website) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_ADRESS, adress);
        initialValues.put(KEY_DESCRIPTION, description);
        initialValues.put(KEY_WEBSITE, website);

        // Insert the data into the database.
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    // Delete a row from the database, by rowId (primary key)
    public boolean deleteRow(long rowId) {
        String where = KEY_ROWID + "=" + rowId;
        return db.delete(DATABASE_TABLE, where, null) != 0;
    }

    public void deleteAll() {
        Cursor c = getAllRows();
        long rowId = c.getColumnIndexOrThrow(KEY_ROWID);
        if (c.moveToFirst()) {
            do {
                deleteRow(c.getLong((int) rowId));
            } while (c.moveToNext());
        }
        c.close();
    }

    // Return all data in the database.
    public Cursor getAllRows() {
        String where = null;
        Cursor c = 	db.query(true, DATABASE_TABLE, ALL_KEYS, where, null, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    // Get a specific row (by rowId)
    public Cursor getRow(long rowId) {
        String where = KEY_ROWID + "=" + rowId;
        Cursor c = 	db.query(true, DATABASE_TABLE, ALL_KEYS,
                where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }


    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase _db) {
            _db.execSQL(DATABASE_CREATE_SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading application's database from version " + oldVersion
                    + " to " + newVersion + ", which will destroy all old data!");

            // Destroy old database:
            _db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);

            // Recreate new database:
            onCreate(_db);
        }
    }


}

