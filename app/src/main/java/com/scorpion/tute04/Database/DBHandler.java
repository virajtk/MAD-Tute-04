package com.scorpion.tute04.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "userInfo.db";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String SQL_DB_CREATE_ENTRIES =
                "CREATE TABLE "+ UsersMaster.Users.TABLE_Name
                    +" (" + UsersMaster.Users._ID + " INTEGER PRIMARY KEY,"
                    + UsersMaster.Users.COLUMN_1 + " TEXT,"
                    + UsersMaster.Users.COLUMN_2 + " TEXT )";

        sqLiteDatabase.execSQL(SQL_DB_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long insertUser (String username, String password){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(UsersMaster.Users.COLUMN_1,username);
        contentValues.put(UsersMaster.Users.COLUMN_2,password);

        long newRowID = db.insert(UsersMaster.Users.TABLE_Name,null,contentValues);

        return newRowID;
    }

    public int updateUser (String username, String newPassword){

        SQLiteDatabase db = getWritableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(UsersMaster.Users.COLUMN_2, newPassword);

        // Which row to update, based on the title
        String selection = UsersMaster.Users.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = { username };

        int count = db.update(
                UsersMaster.Users.TABLE_Name,
                values,
                selection,
                selectionArgs);

        return count;

    }

    public int deleteUser (String username){
        SQLiteDatabase db = getWritableDatabase();

        // Define 'where' part of query.
        String selection = UsersMaster.Users.COLUMN_1 + " LIKE ?";

        // Specify arguments in placeholder order.
        String[] selectionArgs = { username };

        // Issue SQL statement.
        int deletedRows = db.delete(UsersMaster.Users.TABLE_Name, selection, selectionArgs);

        return deletedRows;
    }


    public ArrayList showAllUsers (){


        SQLiteDatabase db = getReadableDatabase();

    // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                UsersMaster.Users._ID,
                UsersMaster.Users.COLUMN_1,
                UsersMaster.Users.COLUMN_2
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = UsersMaster.Users.COLUMN_1 + " = ?";
        String[] selectionArgs = { "Viru" };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                UsersMaster.Users.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                UsersMaster.Users.TABLE_Name,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        ArrayList usernames = new ArrayList<>();
        ArrayList passwords = new ArrayList<>();
        while(cursor.moveToNext()) {
            //ong userID = cursor.getLong(cursor.getColumnIndexOrThrow(UsersMaster.Users._ID));
            String username = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.COLUMN_1));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.COLUMN_2));
            usernames.add(username);
            passwords.add(password);
        }
        cursor.close();
        return usernames;
    }



    public ArrayList signin (String username, String password){

        SQLiteDatabase db = getReadableDatabase();

        // Which row to update, based on the title
        String selection = UsersMaster.Users.COLUMN_1 +" LIKE '"+username+"' AND "+ UsersMaster.Users.COLUMN_2 +"LIKE '"+password+"'";


        Cursor cursor = db.query(
                UsersMaster.Users.TABLE_Name,
                null,
                selection,
                null,
                null,
                null,
                null);

        ArrayList usernames = new ArrayList<>();
        while(cursor.moveToNext()) {
            //ong userID = cursor.getLong(cursor.getColumnIndexOrThrow(UsersMaster.Users._ID));
            String user = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.COLUMN_1));
            usernames.add(user);
        }
        cursor.close();
        return usernames;

    }

}
