package com.example.ccc.streetworkoutapp.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.ccc.streetworkoutapp.Model.Favorites;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ccc on 19.03.2018.
 */

    public class Database extends SQLiteAssetHelper {
        private static final String DB_NAME = "Workout.db";
        private static final int DB_VER = 1;
        public Database(Context context) {
            super(context, DB_NAME, null, DB_VER);
        }

        //Favorites
    public void addToFavorities(Favorites exercise)
    {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO Favorites(" +
                "ExerciseId,ExerciseDescription,ExerciseImage,ExerciseMenuId,ExerciseName,UserEmail) " +
                "VALUES('%s','%s','%s','%s','%s','%s');",
        exercise.getExerciseId(),
        exercise.getExerciseDescription(),
        exercise.getExerciseImage(),
        exercise.getExerciseMenuId(),
        exercise.getExerciseName(),
        exercise.getUserEmail());

        db.execSQL(query);
    }

    public void removeFromFavorities(String exerciseId, String userEmail)
    {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM Favorites WHERE exerciseId='%s' and UserEmail='%s';",exerciseId,userEmail);
        db.execSQL(query);
    }

    public boolean isFavorite(String exerciseId, String userEmail)
    {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("SELECT * FROM Favorites WHERE exerciseId='%s' and UserEmail='%s';",exerciseId,userEmail);
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount() <= 0)
        {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public List<Favorites> getAllFavorites(String userEmail) {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"ExerciseId,ExerciseDescription,ExerciseImage,ExerciseMenuId,ExerciseName,UserEmail"};
        String sqlTable = "Favorites";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);

        final List<Favorites> result = new ArrayList<>();
        if (c.moveToFirst())
        {
            do {
                result.add(new Favorites(
                        c.getString(c.getColumnIndex("ExerciseId")),
                        c.getString(c.getColumnIndex("ExerciseDescription")),
                        c.getString(c.getColumnIndex("ExerciseImage")),
                        c.getString(c.getColumnIndex("ExerciseMenuId")),
                        c.getString(c.getColumnIndex("ExerciseName")),
                        c.getString(c.getColumnIndex("UserEmail"))

                ));


            } while (c.moveToNext());
        }
        return result;
    }


}
