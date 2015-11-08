package edu.uco.schambers4.octane.Fragments.Workout;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.uco.schambers4.octane.Models.Workout.Exercise;
import edu.uco.schambers4.octane.Models.Workout.ExerciseContainer;
import edu.uco.schambers4.octane.Models.Workout.ExerciseMeasure;
import edu.uco.schambers4.octane.Models.Workout.FitnessTest;
import edu.uco.schambers4.octane.Models.Workout.FitnessTestContainer;
import edu.uco.schambers4.octane.Models.Workout.FitnessTestHistoryAdapter;
import edu.uco.schambers4.octane.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FitnessTestDetailFragment extends Fragment {

    FitnessTestContainer mFitnessTestContainer = FitnessTestContainer.getInstance();
    FitnessTest mFitnessTest;

    @Bind(R.id.fitness_test_name_tv)
    TextView mFitnessTestNameTv;
    @Bind(R.id.fitness_test_force_value_et)
    EditText mForceValEt;
    @Bind(R.id.fitness_test_force_unit_tv)
    TextView mForceMsrTv;
    @Bind(R.id.fitness_test_measure_value_et)
    EditText mMeasureValEt;
    @Bind(R.id.fitness_test_measure_unit_tv)
    TextView mMeasureUnitTv;
    @Bind(R.id.fragment_fitness_test_history_lv)
    ListView mFitnessTestList;

    @Bind(R.id.add_fitness_test_fab)
    FloatingActionButton mUpdateFitnessTest;

    public static FitnessTestDetailFragment newInstance(int index) {
        FitnessTestDetailFragment f = new FitnessTestDetailFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }

    public FitnessTestDetailFragment() {
        // Required empty public constructor
    }

    public int getShownIndex() {
        return getArguments().getInt("index", 0);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int i = getShownIndex();
        Context context = getActivity().getApplicationContext();
        ArrayList<FitnessTest> fitnessTests = mFitnessTestContainer.getRecentFitnessTests(context);
        mFitnessTest = fitnessTests.get(i);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fitness_test_detail, container, false);
        ButterKnife.bind(this, view);


        if (getShownIndex() > -1) {
            fillForm(container.getContext());
        }

        mUpdateFitnessTest.setOnClickListener(v -> doUpdate());

        return view;
    }

    private void doUpdate() {

        Context context = getActivity().getApplicationContext();
        ExerciseContainer exerciseContainer = ExerciseContainer.getInstance();
        Exercise exercise = exerciseContainer.getExerciseByName(context, mFitnessTest.getExerciseName());

        String fResistance;
        fResistance = mForceValEt.getText().toString();
        if (fResistance == null) {
            Toast.makeText(context, "Resistance Value", Toast.LENGTH_SHORT).show();
            return;
        }
        String fMeasure;
        fMeasure = mMeasureValEt.getText().toString();
        if (fMeasure == null) {
            Toast.makeText(context, "Measure Value", Toast.LENGTH_SHORT).show();
            return;
        }
        ExerciseMeasure.Unit fResistanceUnit;
        ExerciseMeasure.Unit fMeasureUnit;
        if (mFitnessTest.getExerciseMeasure() != null) {
            fResistanceUnit = mFitnessTest.getExerciseMeasure().getForceUnits();
            fMeasureUnit = mFitnessTest.getExerciseMeasure().getMeasureUnits();
        } else {
            fResistanceUnit = exercise.getMaxIntensityExerciseMeasure().getForceUnits();
            fMeasureUnit = exercise.getMaxIntensityExerciseMeasure().getMeasureUnits();
        }

        ExerciseMeasure fFitnessTestMeasure;
        try {
            fFitnessTestMeasure = new ExerciseMeasure();
            fFitnessTestMeasure.setForce(Integer.parseInt(fResistance));
            fFitnessTestMeasure.setMeasure(Integer.parseInt(fMeasure));
            fFitnessTestMeasure.setForceUnits(fResistanceUnit);
            fFitnessTestMeasure.setMeasureUnits(fMeasureUnit);
        } catch (Exception e) {
            Toast.makeText(context, "Exercise Measure", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return;
        }

        FitnessTest replacement = new FitnessTest(
                mFitnessTest.getExerciseName(),
                fFitnessTestMeasure,
                Calendar.getInstance().getTime());

        exercise.setMaxIntensityExerciseMeasure(fFitnessTestMeasure);
        exerciseContainer.save(context, exercise);

        mFitnessTestContainer.delete(context, mFitnessTest);
        mFitnessTestContainer.save(context, replacement);
        getActivity().onBackPressed();
    }

    private void doDelete() {
        mFitnessTestContainer.delete(getActivity().getApplicationContext(), mFitnessTest);
        getActivity().onBackPressed();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);

    }


    private void fillForm(Context context) {

        mFitnessTestNameTv.setText(String.format("%s", mFitnessTest.getExerciseName()));
        if (mFitnessTest.getExerciseMeasure() != null) {
            mForceMsrTv.setText(String.format("%s", mFitnessTest.getExerciseMeasure().getForceUnits().getUnitName()));
            mMeasureUnitTv.setText(String.format("%s", mFitnessTest.getExerciseMeasure().getMeasureUnits().getUnitName()));

            mForceValEt.setText(mFitnessTest.getExerciseMeasure().getForce().toString());
            mMeasureValEt.setText(mFitnessTest.getExerciseMeasure().getMeasure().toString());
        } else {
            Exercise exercise = ExerciseContainer.getInstance().getExerciseByName(context, mFitnessTest.getExerciseName());
            mForceMsrTv.setText(exercise.getMaxIntensityExerciseMeasure().getForceUnits().getUnitName());
            mMeasureUnitTv.setText(exercise.getMaxIntensityExerciseMeasure().getMeasureUnits().getUnitName());
        }

        FitnessTestHistoryAdapter fitnessTestHistoryAdapter = new FitnessTestHistoryAdapter(context
                , mFitnessTestContainer.getFitnessTests(context, mFitnessTest.getExerciseName()));

        mFitnessTestList.setAdapter(fitnessTestHistoryAdapter);

    }


}
