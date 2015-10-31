package edu.uco.schambers4.octane.Fragments.Exercises;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.uco.schambers4.octane.Models.Workout.Exercise;
import edu.uco.schambers4.octane.Models.Workout.ExerciseContainer;
import edu.uco.schambers4.octane.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExerciseDetailFragment extends Fragment {

    ExerciseContainer mExerciseContainer = ExerciseContainer.getInstance();
    Exercise mExercise;

    @Bind(R.id.exercise_name_et)
    EditText mExerciseNameEt;
    @Bind(R.id.exercise_description_et)
    EditText mExerciseDescriptionEt;
    @Bind(R.id.exercise_muscle_group_spinner)
    Spinner mMuscleGroupSp;
    @Bind(R.id.exercise_type_spinner)
    Spinner mExerciseTypeSp;
    @Bind(R.id.exercise_force_value_et)
    EditText mForceValEt;
    @Bind(R.id.exercise_force_unit_spinner)
    Spinner mForceMsrSp;
    @Bind(R.id.exercise_measure_value_et)
    EditText mMeasureValEt;
    @Bind(R.id.exercise_measure_unit_spinner)
    Spinner mMeasureUnitSp;

    @Bind(R.id.exercise_high_msr_et)
    EditText mHighMeasureEt;
    @Bind(R.id.exercise_high_qty_et)
    EditText mHighQtyEt;

    @Bind(R.id.exercise_med_msr_et)
    EditText mMedMeasureEt;
    @Bind(R.id.exercise_med_qty_et)
    EditText mMedQtyEt;

    @Bind(R.id.exercise_low_msr_et)
    EditText mLowMeasureEt;
    @Bind(R.id.exercise_low_qty_et)
    EditText mLowQtyEt;

    public static ExerciseDetailFragment newInstance(int index) {
        ExerciseDetailFragment f = new ExerciseDetailFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }

    public ExerciseDetailFragment() {
        // Required empty public constructor
    }

    public int getShownIndex() {
        return getArguments().getInt("index", 0);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mExercise = mExerciseContainer.getExercises().get(getShownIndex());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exercise_detail, container, false);
        ButterKnife.bind(this, view);


        if (getShownIndex() > -1) {
            fillForm(container.getContext());
        }


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);

    }


    private void fillForm(Context context) {

        mExerciseNameEt.setText(String.format("%s (%d)", mExercise.getName(), getShownIndex()));

        mExerciseDescriptionEt.setText(String.format("%s", mExercise.getDescription()));

        mExerciseTypeSp.setAdapter(mExerciseContainer.getExerciseTypeArrayAdapter(context));
        mExerciseTypeSp.setSelection(
                mExerciseContainer
                        .getExerciseTypeArrayAdapter(context)
                        .getPosition(mExercise.getExerciseType().getExerciseTypeName())
        );


        mMuscleGroupSp.setAdapter(mExerciseContainer.getMuscleArrayAdapter(context));
        mMuscleGroupSp.setSelection(
                mExerciseContainer
                        .getMuscleArrayAdapter(context)
                        .getPosition(mExercise.getMuscleGroup().getGroupName())
        );

        mForceMsrSp.setAdapter(mExerciseContainer.getResistanceUnitsArrayAdapter(context));
        mForceMsrSp.setSelection(
                mExerciseContainer
                        .getResistanceUnitsArrayAdapter(context)
                        .getPosition(mExercise.getMaxIntensityExerciseMeasure().getForceUnits().getUnitName())
        );
        mForceValEt.setText(mExercise.getMaxIntensityExerciseMeasure().getForce().toString());

        mMeasureUnitSp.setAdapter(mExerciseContainer.getMeasurementUnitsArrayAdapter(context));
        mMeasureUnitSp.setSelection(
                mExerciseContainer
                        .getMeasurementUnitsArrayAdapter(context)
                        .getPosition(mExercise.getMaxIntensityExerciseMeasure().getMeasureUnits().getUnitName())
        );
        mMeasureValEt.setText(mExercise.getMaxIntensityExerciseMeasure().getMeasure().toString());

        mHighQtyEt.setText(mExercise.getHighIntensity().getForceInteger().toString());
        mHighMeasureEt.setText(mExercise.getHighIntensity().getMeasureInteger().toString());

        mMedQtyEt.setText(mExercise.getMedIntensity().getForceInteger().toString());
        mMedMeasureEt.setText(mExercise.getMedIntensity().getMeasureInteger().toString());

        mLowQtyEt.setText(mExercise.getLowIntensity().getForceInteger().toString());
        mLowMeasureEt.setText(mExercise.getLowIntensity().getMeasureInteger().toString());

    }


}
