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
    public static final String KEY_PHONE = "phone";
    public static final String KEY_WEBSITE = "website";

    public static final String KEY_ROWID2 = "available_service_id";
    public static final String KEY_FORROWID = "service_id";
    public static final String KEY_NAME2 = "available_service_name";
    public static final String KEY_DIFF = "difficulty";
    public static final String KEY_DESC = "description";

    public static final String[] ALL_KEYS = new String[] {KEY_ROWID, KEY_NAME, KEY_ADRESS, KEY_PHONE, KEY_WEBSITE};
    public static final String[] ALL_KEYS2 = new String[] {KEY_ROWID2, KEY_FORROWID, KEY_NAME2, KEY_DIFF, KEY_DESC};

    // Column Numbers for each Field Name:
    public static final int COL_ROWID = 0;
    public static final int COL_NAME = 1;
    public static final int COL_ADRESS = 2;
    public static final int COL_DESCRIPTION = 3;


    // DataBase info:
    public static final String DATABASE_NAME = "dbVelo";
    public static final String DATABASE_TABLE = "Service";
    public static final String DATABASE2_TABLE = "AvailableService";
    public static final int DATABASE_VERSION = 1; // The version number must be incremented each time a change to DB structure occurs.

    //SQL statement to create database
    private static final String DATABASE_CREATE_SQL =
            "CREATE TABLE " + DATABASE_TABLE
                    + " (" + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KEY_NAME + " TEXT NOT NULL, "
                    + KEY_ADRESS + " TEXT, "
                    + KEY_PHONE + " TEXT, "
                    + KEY_WEBSITE + " TEXT"
                    + ");";

    private static final String DATABASE2_CREATE_SQL =
            "CREATE TABLE " + DATABASE2_TABLE
                    + " (" + KEY_ROWID2 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KEY_FORROWID + " INTEGER, "
                    + KEY_NAME2 + " TEXT, "
                    + KEY_DIFF + " TEXT, "
                    + KEY_DESC + " TEXT, "
                    + " FOREIGN KEY ("+KEY_FORROWID+") REFERENCES "+DATABASE_TABLE+" ("+KEY_ROWID+"));";


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
    public void populateDB2() {
        String count = "SELECT count(*) FROM AvailableService";
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if (icount > 0)
        return;
        else{
            String sql1 = "INSERT INTO AvailableService (service_id, available_service_name, difficulty) VALUES('1','Kameras maiņa','5','Nomaina velosipēdam plīsušo kameru.');";
            db.execSQL(sql1);
        }
    }

    public void populateDB() {
        String count = "SELECT count(*) FROM Service";
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if (icount > 0)
            return;
        else{
            String sql = "INSERT INTO Service (_id, name, adress, phone, website) VALUES('1','Fans','A.Deglava 50','67 802 056','www.fans.lv');";
            String sql2 = "INSERT INTO Service (_id, name, adress, phone, website) VALUES('2','Gandrs','Kalnciema 28','67 614 775','www.gandrs.lv');";
            String sql3 = "INSERT INTO Service (_id, name, adress, phone, website) VALUES('3','ZZK','Ulmaņa gatve 201','67 810 342','www.zzk.lv');";
            String sql4 = "INSERT INTO Service (_id, name, adress, phone, website) VALUES('4','XSports','Džutas iela 8','26 111 666','www.xsports.lv');";
            String sql5 = "INSERT INTO Service (_id, name, adress, phone, website) VALUES('5','Hawaii Express','Biķernieku 11','67 543 721','www.hawaiiexpress.lv');";
            String sql6 = "INSERT INTO Service (_id, name, adress, phone, website) VALUES('6','RigaBike','Matīsa 8','29 443 535','www.rigabike.lv');";
            String sql7 = "INSERT INTO Service (_id, name, adress, phone, website) VALUES('7','Primum Bike','Brīvības gatve 390','20208484','www.primumbike.lv');";
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
    public Cursor getRow(String rowId) {
        int foo = Integer.parseInt(rowId);
        String where = KEY_FORROWID + " =" + foo;
        Cursor c = 	db.query(true, DATABASE2_TABLE, ALL_KEYS2, where, null, null, null, null, null, null);
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
            _db.execSQL(DATABASE2_CREATE_SQL);
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

