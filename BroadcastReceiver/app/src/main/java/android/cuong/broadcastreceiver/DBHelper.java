package android.cuong.broadcastreceiver;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by QUOC CUONG on 07/11/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "numberDb";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE = "create table " + DBContract.TABLE_NAME +
                "(id integer primary key autoincrement, " + DBContract.INCOMING_NUMBER + " text);";
    private static final String DROP_TABLE = "drop table if exists " + DBContract.TABLE_NAME;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void saveNumber(String number, SQLiteDatabase database) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBContract.INCOMING_NUMBER, number);
        database.insert(DBContract.TABLE_NAME, null, contentValues);
    }

    public Cursor readNumber(SQLiteDatabase database) {
        String[] projection = {"id", DBContract.INCOMING_NUMBER};

        return database.query(DBContract.TABLE_NAME, projection, null, null, null, null, null);
    }
}
