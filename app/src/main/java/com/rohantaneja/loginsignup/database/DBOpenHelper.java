package com.rohantaneja.loginsignup.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rohantaneja.loginsignup.model.UserModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rohantaneja on 08/08/17.
 */

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "users_db";
    private static final String TABLE_USERS = "table_users";
    private static final String _ID = "column_id";
    private static final String COLUMN_EMAIL = "column_email";
    private static final String COLUMN_PASSWORD = "column_password";

    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_EMAIL + " TEXT, "
                + COLUMN_PASSWORD + " TEXT" +
                ")";

        sqLiteDatabase.execSQL(CREATE_USERS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
    }

    public void addUser(UserModel user) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_EMAIL, user.getUserEmail());
        contentValues.put(COLUMN_PASSWORD, user.getUserPassword());

        db.insert(TABLE_USERS, null, contentValues);
        db.close();
    }

    public boolean checkIfUserExists(UserModel currentUser) {

        List<UserModel> usersList = getAllUsers();

        String passwordToBeChecked = null;

        for (UserModel user : usersList) {
            if (currentUser.getUserEmail().equals(user.getUserEmail())) {
                passwordToBeChecked = user.getUserPassword();
                break;
            }
        }

        if (passwordToBeChecked != null && passwordToBeChecked.equals(currentUser.getUserPassword
                ()))
            return true;

        return false;

    }

    private List<UserModel> getAllUsers() {
        List<UserModel> usersList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS, null, null, null,
                null, null, null);

        String email = null;
        String password = null;

        if (cursor.moveToFirst()) {
            do {
                email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));
                UserModel user = new UserModel(email, password);
                usersList.add(user);
            } while (cursor.moveToNext());
        }

        db.close();

        return usersList;
    }

}

