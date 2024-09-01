package com.manmohan7.sqltestapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseClass(context: Context?) : SQLiteOpenHelper(context, "login1.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE user(ID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS user")
    }

    fun Insert(username: String?, password: String?): Boolean {
        val sqLiteDatabase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("username", username)
        contentValues.put("password", password)
        val result = sqLiteDatabase.insert("user", null, contentValues)
        return result != -1L
    }

    fun CheckUsername(username: String): Boolean {
        val sqLiteDatabase = this.writableDatabase
        val cursor = sqLiteDatabase.rawQuery("SELECT * FROM user WHERE username=?", arrayOf(username))
        return cursor.count <= 0
    }

    fun CheckLogin(username: String, password: String): Boolean {
        val sqLiteDatabase = this.readableDatabase
        val cursor = sqLiteDatabase.rawQuery(
            "SELECT * FROM user WHERE username=? AND password=?",
            arrayOf(username, password)
        )
        return (cursor.count > 0)
    }
}