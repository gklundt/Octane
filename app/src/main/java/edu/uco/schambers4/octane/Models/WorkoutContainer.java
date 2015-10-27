package edu.uco.schambers4.octane.Models;

/**
 * Created by gordon on 10/25/15.
 */
public class WorkoutContainer {
    private static WorkoutContainer ourInstance = new WorkoutContainer();

    public static WorkoutContainer getInstance() {
        return ourInstance;
    }

    private WorkoutContainer() {
    }
}
