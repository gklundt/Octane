package edu.uco.schambers4.octane.Models.Workout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import edu.uco.schambers4.octane.R;

public class ExerciseAdapter extends ArrayAdapter<Exercise>{


    public ExerciseAdapter(Context context, ArrayList<Exercise> exercises) {
        super(context, 0, exercises);
        //Collections.sort(exercises, new ExerciseComparator());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Exercise exercise = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_exercise, parent, false);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        tvName.setText(String.format("%s (%d)",exercise.getName(),position));

        return convertView;
    }




}
