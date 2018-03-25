package com.example.ccc.streetworkoutapp.Model;

/**
 * Created by ccc on 24.03.2018.
 */

public class Favorites {
    private String ExerciseId, ExerciseDescription, ExerciseImage, ExerciseMenuId, ExerciseName, UserEmail;

    public Favorites() {
    }

    public Favorites(String exerciseId, String exerciseDescription, String exerciseImage, String exerciseMenuId, String exerciseName, String userEmail) {
        ExerciseId = exerciseId;
        ExerciseDescription = exerciseDescription;
        ExerciseImage = exerciseImage;
        ExerciseMenuId = exerciseMenuId;
        ExerciseName = exerciseName;
        UserEmail = userEmail;
    }

    public String getExerciseId() {
        return ExerciseId;
    }

    public void setExerciseId(String exerciseId) {
        ExerciseId = exerciseId;
    }

    public String getExerciseDescription() {
        return ExerciseDescription;
    }

    public void setExerciseDescription(String exerciseDescription) {
        ExerciseDescription = exerciseDescription;
    }

    public String getExerciseImage() {
        return ExerciseImage;
    }

    public void setExerciseImage(String exerciseImage) {
        ExerciseImage = exerciseImage;
    }

    public String getExerciseMenuId() {
        return ExerciseMenuId;
    }

    public void setExerciseMenuId(String exerciseMenuId) {
        ExerciseMenuId = exerciseMenuId;
    }

    public String getExerciseName() {
        return ExerciseName;
    }

    public void setExerciseName(String exerciseName) {
        ExerciseName = exerciseName;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }
}