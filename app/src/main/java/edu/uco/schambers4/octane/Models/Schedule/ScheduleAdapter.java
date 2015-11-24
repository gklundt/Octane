package edu.uco.schambers4.octane.Models.Schedule;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.uco.schambers4.octane.Models.GeneralInterfaces.INameable;
import edu.uco.schambers4.octane.R;

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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ViewHolder();
            holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);

            row.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)row.getTag();
        }

        Schedule<INameable> schedule = data.get(position);
        holder.txtTitle.setText(schedule.getItem().getName());

        return row;
    }

    static class ViewHolder
    {
        TextView txtTitle;
    }
}
