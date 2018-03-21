package com.example.ccc.streetworkoutapp.Model;

/**
 * Created by ccc on 19.03.2018.
 */

public class Plan {
    private String ExerciseId;
    private String ExerciseName;
    private String Series;
    private String Reps;

    public Plan() {
    }

    public Plan(String exerciseId, String exerciseName, String series, String reps) {
        ExerciseId = exerciseId;
        ExerciseName = exerciseName;
        Series = series;
        Reps = reps;
    }

    public String getExerciseId() {
        return ExerciseId;
    }

    public void setExerciseId(String exerciseId) {
        ExerciseId = exerciseId;
    }

    public String getExerciseName() {
        return ExerciseName;
    }

    public void setExerciseName(String exerciseName) {
        ExerciseName = exerciseName;
    }

    public String getSeries() {
        return Series;
    }

    public void setSeries(String series) {
        Series = series;
    }

    public String getReps() {
        return Reps;
    }

    public void setReps(String reps) {
        Reps = reps;
    }
}
