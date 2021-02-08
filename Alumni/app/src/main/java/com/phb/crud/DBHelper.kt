package com.phb.crud

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DBHelper(ctx: Context): ManagedSQLiteOpenHelper(ctx, "Alumni.db", null, 1) {
    companion object{
        private var instance: DBHelper? = null
        @Synchronized
        fun getInstance(ctx: Context) : DBHelper{

                if(instance == null){
                    instance = DBHelper(ctx.applicationContext)
                }
                return instance as DBHelper
            }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(Alumni.TABLE_ALUMNI, true,
        Alumni.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
        Alumni.NAMA to TEXT,
        Alumni.ANGKATAN to TEXT,
        Alumni.JURUSAN to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(Alumni.TABLE_ALUMNI, true)
    }
}

val Context.database : DBHelper
get() = DBHelper.getInstance(applicationContext)