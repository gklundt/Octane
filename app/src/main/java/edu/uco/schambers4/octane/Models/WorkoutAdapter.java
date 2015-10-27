package edu.uco.schambers4.octane.Models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import edu.uco.schambers4.octane.R;

public class WorkoutAdapter extends ArrayAdapter<Workout>{


    public WorkoutAdapter(Context context, ArrayList<Workout> workouts) {
        super(context, 0, workouts);
        Collections.sort(workouts, new WorkoutComparator());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Workout workout = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_workout, parent, false);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        tvName.setText(workout.getName());

        return convertView;
    }




}
