package edu.uco.schambers4.octane.Models;

import java.util.Comparator;

public class WorkoutComparator implements Comparator<Workout> {
    @Override
    public int compare(Workout lhs, Workout rhs) {

        return lhs.getName().compareTo(rhs.getName());
    }


}
