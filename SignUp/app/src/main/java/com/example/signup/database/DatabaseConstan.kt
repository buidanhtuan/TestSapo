package com.example.signup.database

class DatabaseConstan {
    companion object {
        val DATABASE_NAME = "DB_NAME"
        val DATABASE_VERSION = 1
        val DATABASE_TABEL = "DB_TABEL"
        val ROW_NAME = "name"
        val ROW_EMAIL = "email"
        val ROW_PASSWORD = "password"
        val ROW_CITY = "city"
        val ROW_DISTRICT = "district"
        val ROW_AGE = "age"
        val ROW_SEX = "sex"
        val QUERY_CREATE = "CREATE TABLE IF NOT EXISTS $DATABASE_TABEL ($ROW_NAME TEXT PRIMARY KEY, $ROW_EMAIL TEXT , $ROW_PASSWORD TEXT,$ROW_CITY TEXT,$ROW_DISTRICT TEXT,$ROW_AGE TEXT,$ROW_SEX TEXT)"
        val QUERY_UPGRADE = "DROP TABLE IF EXISTS $DATABASE_TABEL"
    }
}