package edu.uco.schambers4.octane.Models;

import java.util.Comparator;

public class ExerciseComparator implements Comparator<Exercise> {

    @Override
    public int compare(Exercise lhs, Exercise rhs) {

        return lhs.getName().compareTo(rhs.getName());
    }

}
