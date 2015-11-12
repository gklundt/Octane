package edu.uco.schambers4.octane.DataAccessObjects.Schedules;

import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import edu.uco.schambers4.octane.InternalStorageSerialization.InternalStorage;
import edu.uco.schambers4.octane.Models.Schedule.Schedule;

public class InternalStorageScheduleRepository<T> implements ScheduleRepository<T>{

    private Context context;
    private String schemaName;

    ArrayList<Schedule<T>> mSchedules;

    public InternalStorageScheduleRepository(Context context, String schemaName){
        this.context = context;
        this.schemaName = schemaName;
    }

    @Override
    public ArrayList<Schedule<T>> getAllSchedules() {

        loadCheck();

        return mSchedules;
    }

    @Override
    public ArrayList<Schedule<T>> getSchedulesForDate(Date date) {

        loadCheck();

        ArrayList<Schedule<T>> schedulesForDate = new ArrayList<>();

        for(Schedule<T> schedule : mSchedules){
            if(schedule.getDate().equals(date)) {
                schedulesForDate.add(schedule);
            }
        }

        return schedulesForDate;
    }

    @Override
    public void saveSchedule(Schedule<T> scheduleToSave) {
        loadCheck();

        boolean found = false;
        int pos = 0;
        for (Schedule<T> schedule : mSchedules) {
            if (scheduleToSave.getId().equals(schedule.getId())) {
                found = true;
                break;
            }
            pos++;
        }

        if (found) {
            mSchedules.remove(pos);
        }
        mSchedules.add(pos, scheduleToSave);
        try {
            flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSchedule(Schedule<T> scheduleToDelete) {
        loadCheck();
        boolean found = false;
        int pos = 0;
        for (Schedule<T> schedule : mSchedules) {
            if (scheduleToDelete.getId().equals(schedule.getId())) {
                found = true;
                break;
            }
            pos++;
        }

        if (found) {
            mSchedules.remove(pos);
        }
        try {
            flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void flush() throws IOException {
        loadCheck();
        String compoundKey = String.format(InternalStorage.STORAGE_KEY_SCHEDULES, schemaName);
        InternalStorage.writeObject(context, compoundKey, mSchedules);
    }

    private ArrayList<Schedule<T>> load() throws IOException, ClassNotFoundException {
        String lol = String.format(InternalStorage.STORAGE_KEY_SCHEDULES, schemaName);

        ArrayList<Schedule<T>> items = (ArrayList<Schedule<T>>)
                InternalStorage.readObject(context, lol);

        return items;
    }

    private void loadCheck() {
        if(mSchedules == null){
            try {
                mSchedules = load();
                if(mSchedules == null){
                    mSchedules = new ArrayList<>();
                }
            } catch (IOException e) {
                mSchedules = new ArrayList<>();
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                mSchedules = new ArrayList<>();
                e.printStackTrace();
            }
        }
    }
}
