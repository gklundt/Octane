package edu.uco.schambers4.octane.Models.Workout;

import java.util.Comparator;

public class FitnessTestComparator implements Comparator<FitnessTest> {
    @Override
    public int compare(FitnessTest lhs, FitnessTest rhs) {

        return lhs.getDate().compareTo(rhs.getDate());
    }


}
