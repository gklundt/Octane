package edu.uco.schambers4.octane.DataAccessObjects.Schedules;

import java.util.ArrayList;
import java.util.Date;

import edu.uco.schambers4.octane.Models.Schedule.Schedule;

public interface ScheduleRepository<T> {
    ArrayList<Schedule<T>> getAllSchedules();

    ArrayList<Schedule<T>> getSchedulesForDate(Date date);

    void saveSchedule(Schedule<T> schedule);

    void deleteSchedule(Schedule<T> schedule);
}
