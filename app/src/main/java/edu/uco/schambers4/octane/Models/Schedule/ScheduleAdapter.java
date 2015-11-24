package edu.uco.schambers4.octane.Models.Schedule;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import edu.uco.schambers4.octane.Models.GeneralInterfaces.INameable;

public class ScheduleAdapter extends ArrayAdapter<Schedule<INameable>> {

    Context context;
    int layoutResourceId;
    ArrayList<Schedule<INameable>> data = null;

    public ScheduleAdapter(Context context, int layoutResourceId, ArrayList<Schedule<INameable>> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }
}
