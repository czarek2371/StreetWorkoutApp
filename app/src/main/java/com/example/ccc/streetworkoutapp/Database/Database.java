package com.example.ccc.streetworkoutapp.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.ccc.streetworkoutapp.Model.Plan;
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

        public List<Plan> getMyPlanList() {
            SQLiteDatabase db = getReadableDatabase();
            SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

            String[] sqlSelect = {"ExerciseId", "ExerciseName", "Reps", "Series"};
            String sqlTable = "MyPlan";

            qb.setTables(sqlTable);
            Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);

            final List<Plan> result = new ArrayList<>();
            if (c.moveToFirst())
            {
                do {
                    result.add(new Plan(c.getString(c.getColumnIndex("ExerciseId")),
                            c.getString(c.getColumnIndex("ExerciseName")),
                            c.getString(c.getColumnIndex("Reps")),
                            c.getString(c.getColumnIndex("Series"))


                    ));

                } while (c.moveToNext());
            }
            return result;
        }


        public void addToPlan(Plan plan) {
            SQLiteDatabase db = getReadableDatabase();
            String query = String.format("INSERT INTO MyPlan(ExerciseId,ExerciseName,Reps,Series) VALUES('%s','%s','%s','%s');",
                    plan.getExerciseId(),
                    plan.getExerciseName(),
                    plan.getSeries(),
                    plan.getReps());

            db.execSQL(query);

        }

        public void cleanFromMyPlan() {
            SQLiteDatabase db = getReadableDatabase();
            String query = String.format("DELETE FROM MyPlan");
            db.execSQL(query);

        }
    }
