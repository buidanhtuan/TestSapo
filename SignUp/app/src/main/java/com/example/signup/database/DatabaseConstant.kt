package com.example.signup.database

class DatabaseConstant {
    companion object {
        const val DATABASE_NAME = "DB_NAME"
        const val DATABASE_VERSION = 1
        const val DATABASE_TABEL = "DB_TABEL"
        const val ROW_NAME = "name"
        const val ROW_EMAIL = "email"
        const val ROW_PASSWORD = "password"
        const val ROW_CITY = "city"
        const val ROW_DISTRICT = "district"
        const val ROW_AGE = "age"
        const val ROW_SEX = "sex"
        const val QUERY_CREATE = "CREATE TABLE IF NOT EXISTS $DATABASE_TABEL ($ROW_NAME TEXT PRIMARY KEY, $ROW_EMAIL TEXT , $ROW_PASSWORD TEXT,$ROW_CITY TEXT,$ROW_DISTRICT TEXT,$ROW_AGE TEXT,$ROW_SEX TEXT)"
        const val QUERY_UPGRADE = "DROP TABLE IF EXISTS $DATABASE_TABEL"
    }
}