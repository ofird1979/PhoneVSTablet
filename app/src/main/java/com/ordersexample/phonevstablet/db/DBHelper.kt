package com.ordersexample.phonevstablet.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.ordersexample.mytabkotlinapp.model.Movie

class DBHelper(ctx: Context) : SQLiteOpenHelper(ctx, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object Constants : BaseColumns {
        const val DATABASE_NAME = "MYTABKOTLIN.db"
        const val DATABASE_VERSION = 2
        const val TABLE_NAME = "Movie"
        const val COL_TMDBID = "TmdbID"
        const val COL_NAME = "Name"
        const val COL_RATE = "Rating"
        const val COL_URL = "ImageUrl"
        const val COL_DESC = "Overview"
        const val COL_RELEASE_DATE = "Release_date"
        const val COL_ID = "_Id"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY =
            ("CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,$COL_TMDBID INTEGER NOT NULL,$COL_NAME TEXT NOT NULL,$COL_RELEASE_DATE DATE,$COL_RATE DOUBLE,$COL_URL TEXT,$COL_DESC TEXT)")
        db!!.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun deleteAll(){
        val db=this.writableDatabase
        db.execSQL("DELETE FROM $TABLE_NAME")
        db.close()
    }

    val getAllMovies: List<Movie>
        get() {
            val listMovie = ArrayList<Movie>()
            val selectQuery = "SELECT * FROM $TABLE_NAME"
            val db = this.writableDatabase
            val cursor = db.rawQuery(selectQuery, null)
            if (cursor.moveToFirst()) {
                do {
                    val movie = Movie()
                    movie.id = cursor.getInt(cursor.getColumnIndex(COL_TMDBID))
                    movie.title = cursor.getString(cursor.getColumnIndex(COL_NAME))
                    movie.vote_average = cursor.getDouble(cursor.getColumnIndex(COL_RATE))
                    movie.poster_path = cursor.getString(cursor.getColumnIndex(COL_URL))
                    movie.release_date = cursor.getString(cursor.getColumnIndex(COL_RELEASE_DATE))
                    movie.overview = cursor.getString(cursor.getColumnIndex(COL_DESC))
                    listMovie.add(movie)
                } while (cursor.moveToNext())
            }
            db.close()
            return listMovie

        }

    fun addMovie(movie: Movie?) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_TMDBID, movie!!.id)
        values.put(COL_NAME, movie!!.title)
        values.put(COL_RATE, movie!!.vote_average)
        values.put(COL_RELEASE_DATE, movie.release_date)
        values.put(COL_URL, movie.poster_path)
        values.put(COL_DESC, movie.overview)
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun updateMovie(movie: Movie?): Int {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_TMDBID, movie!!.id)
        values.put(COL_NAME, movie.title)
        values.put(COL_RATE, movie.vote_average)
        values.put(COL_RELEASE_DATE, movie.release_date)
        values.put(COL_URL, movie.poster_path)
        values.put(COL_DESC, movie.overview)
        return db.update(TABLE_NAME, values, "$COL_TMDBID=?", arrayOf(movie.id.toString()))

    }

    fun deleteMovie(movie: Movie?) {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "$COL_TMDBID=?", arrayOf(movie!!.id.toString()))
        db.close()
    }

    fun isMovieExist(movie: Movie?): Boolean {
        val selectQuery = "SELECT * FROM $TABLE_NAME WHERE $COL_TMDBID=" + movie!!.id.toString()
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery,null)
        if (cursor.moveToNext()) {
            cursor.close()
            return true
        }
        cursor.close()
        return false
    }

}