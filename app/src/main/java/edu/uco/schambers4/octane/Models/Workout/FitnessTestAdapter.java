package edu.uco.schambers4.octane.Models.Workout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import edu.uco.schambers4.octane.R;

public class FitnessTestAdapter extends ArrayAdapter<FitnessTest> {


    public FitnessTestAdapter(Context context, ArrayList<FitnessTest> fitnessTests) {
        super(context, 0, fitnessTests);
        //Collections.sort(exercises, new ExerciseComparator());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        FitnessTest fitnessTest = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_fitnesstest, parent, false);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        tvName.setText(String.format("%s", fitnessTest.getExerciseName()));

        TextView tvDate = (TextView) convertView.findViewById(R.id.tvDate);
        Date d = fitnessTest.getDate();
        if (fitnessTest.getDate() != null) {
            tvDate.setText(String.format("%tm/%td/%tY", d, d, d)
            );
        } else {
            tvDate.setText("");
        }

        TextView tvResistance = (TextView) convertView.findViewById(R.id.tvResistance);
        TextView tvMeasure = (TextView) convertView.findViewById(R.id.tvMeasure);
        if (fitnessTest.getExerciseMeasure() != null) {
            tvResistance.setText(String.format("%d %s"
                    , fitnessTest.getExerciseMeasure().getForce()
                    , fitnessTest.getExerciseMeasure().getForceUnits().getUnitName()));
            tvMeasure.setText(String.format("%d %s"
                    , fitnessTest.getExerciseMeasure().getMeasure()
                    , fitnessTest.getExerciseMeasure().getMeasureUnits().getUnitName()));
        } else {
            tvResistance.setText("No fitness tests recorded");
            tvMeasure.setText("");
        }


        return convertView;
    }

    @Override
    public void add(FitnessTest object) {
        super.add(object);
    }
}
