package edu.uco.schambers4.octane.Models.Workout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.uco.schambers4.octane.R;

public class FitnessTestHistoryAdapter extends ArrayAdapter<FitnessTest> {

    FitnessTest mFitnessTest;
    @Bind(R.id.fitness_test_history_date_tv)
    TextView mFitnessTestHistoryDateTv;
    @Bind(R.id.fitness_test_history_resistance_tv)
    TextView mFitnessTestHistoryResistanceTv;
    @Bind(R.id.fitness_test_history_measure_tv)
    TextView mFitnessTestHistoryMeasureTv;

    public FitnessTestHistoryAdapter(Context context, ArrayList<FitnessTest> FitnessTests) {
        super(context, 0, FitnessTests);
        //mFitnessTest = fitnessTest;
        Collections.sort(FitnessTests, new FitnessTestComparator());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        FitnessTest FitnessTest = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_fitness_test_history, parent, false);
        }

        ButterKnife.bind(this, convertView);


        Integer wef = FitnessTest.getExerciseMeasure().getForce();
        String wefString = String.format("%d %s", wef, FitnessTest.getExerciseMeasure().getForceUnits().getUnitName());
        mFitnessTestHistoryResistanceTv.setText(wefString);

        Integer wem = FitnessTest.getExerciseMeasure().getMeasure();
        String wemString = String.format("%d %s", wem, FitnessTest.getExerciseMeasure().getMeasureUnits().getUnitName());
        mFitnessTestHistoryMeasureTv.setText(wemString);

        Date d = FitnessTest.getDate();
        mFitnessTestHistoryDateTv.setText(String.format("%tm/%td/%tY",d,d,d));

        return convertView;
    }

    @Override
    public void add(FitnessTest object) {
        super.add(object);
    }


}
