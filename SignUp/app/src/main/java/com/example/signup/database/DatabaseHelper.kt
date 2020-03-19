package com.example.signup.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.signup.model.Account

class DatabaseHelper(ctx: Context) : SQLiteOpenHelper(ctx, DatabaseConstan.DATABASE_NAME, null, DatabaseConstan.DATABASE_VERSION) {
    companion object {
        private lateinit var INSTANCE: DatabaseHelper
        private lateinit var database: SQLiteDatabase
        private var databaseOpen: Boolean = false
        fun closeDatabase() {
            if (database.isOpen && databaseOpen) {
                database.close()
                databaseOpen = false
                Log.i("Database", "Database close")
            }
        }

        fun initDatabaseInstance(ctx: Context): DatabaseHelper {
            INSTANCE = DatabaseHelper(ctx)
            return INSTANCE
        }

        fun insertData(account: Account): Long {
            if (!databaseOpen) {
                database = INSTANCE.writableDatabase
                databaseOpen = true
                Log.i("Database", "Database Open")
            }
            val values = ContentValues()
            values.put(DatabaseConstan.ROW_NAME, account.name)
            values.put(DatabaseConstan.ROW_EMAIL, account.email)
            values.put(DatabaseConstan.ROW_PASSWORD, account.password)
            values.put(DatabaseConstan.ROW_CITY, account.city)
            values.put(DatabaseConstan.ROW_DISTRICT, account.district)
            values.put(DatabaseConstan.ROW_AGE, account.age)
            values.put(DatabaseConstan.ROW_SEX, account.sex)
            return database.insert(DatabaseConstan.DATABASE_TABEL, null, values)
        }

        fun updateData(account: Account): Int {
            if (!databaseOpen) {
                database = INSTANCE.writableDatabase
                databaseOpen = true

                Log.i("Database", "Database Open")
            }

            val values = ContentValues()
            values.put(DatabaseConstan.ROW_EMAIL, account.email)
            values.put(DatabaseConstan.ROW_PASSWORD, account.password)
            values.put(DatabaseConstan.ROW_CITY, account.city)
            values.put(DatabaseConstan.ROW_DISTRICT, account.district)
            values.put(DatabaseConstan.ROW_AGE, account.age)
            values.put(DatabaseConstan.ROW_SEX, account.sex)
            return database.update(
                DatabaseConstan.DATABASE_TABEL,
                values,
                "${DatabaseConstan.ROW_NAME} = ${account.name}",
                null
            )
        }
        fun update(account: Account,st : String): Int {
            if (!databaseOpen) {
                database = INSTANCE.writableDatabase
                databaseOpen = true

                Log.i("Database", "Database Open")
            }

            val values = ContentValues()
            if (st.contains("user")) values.put(DatabaseConstan.ROW_NAME, account.name)
            if (st.contains("email")) values.put(DatabaseConstan.ROW_EMAIL, account.email)
            if (st.contains("password")) values.put(DatabaseConstan.ROW_PASSWORD, account.password)
            if (st.contains("city")) values.put(DatabaseConstan.ROW_CITY, account.city)
            if (st.contains("district")) values.put(DatabaseConstan.ROW_DISTRICT, account.district)
            if (st.contains("age")) values.put(DatabaseConstan.ROW_AGE, account.age)
            if (st.contains("sex")) values.put(DatabaseConstan.ROW_SEX, account.sex)
            return database.update(
                DatabaseConstan.DATABASE_TABEL,
                values,
                "${DatabaseConstan.ROW_NAME} = ${account.name}",
                null
            )
        }

        fun getAllData(): MutableList<Account> {
            if (!databaseOpen) {
                database = INSTANCE.writableDatabase
                databaseOpen = true

                Log.i("Database", "Database Open")
            }

            val data: MutableList<Account> = ArrayList()
            val cursor = database.rawQuery("SELECT * FROM ${DatabaseConstan.DATABASE_TABEL}", null)
            cursor.use { cur ->
                if (cursor.moveToFirst()) {
                    do {

                        val account = Account()
                        account.name = cur.getString(cur.getColumnIndex(DatabaseConstan.ROW_NAME))
                        account.email = cur.getString(cur.getColumnIndex(DatabaseConstan.ROW_EMAIL))
                        account.password = cur.getString(cur.getColumnIndex(DatabaseConstan.ROW_PASSWORD))
                        account.sex = cur.getString(cur.getColumnIndex(DatabaseConstan.ROW_SEX))
                        account.city = cur.getString(cur.getColumnIndex(DatabaseConstan.ROW_CITY))
                        account.district = cur.getString(cur.getColumnIndex(DatabaseConstan.ROW_DISTRICT))
                        account.age = cur.getInt(cur.getColumnIndex(DatabaseConstan.ROW_AGE))
                        data.add(account)

                    } while (cursor.moveToNext())
                }
            }
            return data
        }

        fun deleteData(name: String): Int {
            if (!databaseOpen) {
                database = INSTANCE.writableDatabase
                databaseOpen = true

                Log.i("Database", "Database Open")
            }
            return database.delete(
                DatabaseConstan.DATABASE_TABEL,
                "${DatabaseConstan.ROW_NAME} = $name",
                null
            )
        }
        fun deleteAllData() {
            if (!databaseOpen) {
                database = INSTANCE.writableDatabase
                databaseOpen = true

                Log.i("Database", "Database Open")
            }
            database.delete(DatabaseConstan.DATABASE_TABEL,null,null)
        }
        fun getData(name : String) : Account{
            var account : Account = Account()
            var data: MutableList<Account> = ArrayList()
            if (!databaseOpen) {
                database = INSTANCE.writableDatabase
                databaseOpen = true
                Log.i("Database", "Database Open")
            }
            data = getAllData()
            for(i in 0 until data.size){
                if(data[i].name == name){
                    account = data[i]
                }
            }
            return account
        }
    }
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(DatabaseConstan.QUERY_CREATE)
        Log.i("DATABASE", "DATABASE CREATED")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DatabaseConstan.QUERY_UPGRADE)
        Log.i("DATABASE", "DATABASE UPDATED")
    }
}