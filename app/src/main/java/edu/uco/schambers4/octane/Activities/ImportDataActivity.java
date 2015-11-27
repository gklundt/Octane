package edu.uco.schambers4.octane.Activities;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import edu.uco.schambers4.octane.Models.Workout.Exercise;
import edu.uco.schambers4.octane.Models.Workout.ExerciseContainer;
import edu.uco.schambers4.octane.Models.Workout.Workout;
import edu.uco.schambers4.octane.Models.Workout.WorkoutContainer;

public class ImportDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Uri data = getIntent().getData();
        if (data != null) {
            getIntent().setData(null);
            try {
                importData(data);
            } catch (Exception e) {
                finish();
                return;
            }
        }

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);

    }

    private void importData(Uri data) {

        final String scheme = data.getScheme();
        if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            try {
                ContentResolver cr = getContentResolver();

                InputStream is = cr.openInputStream(data);
                if (is == null) return;

                StringBuffer buf = new StringBuffer();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                Gson gson = new Gson();
                Workout workout = gson.fromJson(reader, Workout.class);
                is.close();

                ExerciseContainer exerciseContainer = ExerciseContainer.getInstance();
                for (Map.Entry<String, Integer> entry : workout.getExerciseSets().entrySet()) {
                    Exercise exercise;
                    exercise = exerciseContainer.getExerciseByName(getApplicationContext(), entry.getKey());
                    if (exercise == null) {
                        exercise = exerciseContainer.getDefaultExercise();
                        exercise.setName(entry.getKey());
                        exercise.setDescription("Imported");
                        exerciseContainer.save(getApplicationContext(),exercise);
                    }
                }
                WorkoutContainer workoutContainer = WorkoutContainer.getInstance();
                workoutContainer.save(getApplicationContext(), workout);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}