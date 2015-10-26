package edu.uco.schambers4.octane.Domain.Data;

import java.io.Serializable;
import java.util.Date;

public class FitnessTest implements Serializable {
    private String mExerciseName;
    private ExerciseMeasure mExerciseMeasure;
    private Date mDate;

    public FitnessTest(String name, ExerciseMeasure measure, Date date) {
        mExerciseName = name;
        mExerciseMeasure = measure;
        mDate = date;
    }

    public Date getDate() {
        return mDate;
    }

    public ExerciseMeasure getExerciseMeasure() {
        return mExerciseMeasure;
    }

    public String getExerciseName() {
        return mExerciseName;
    }
}
