package edu.uco.schambers4.octane.Fragments.Workout;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.uco.schambers4.octane.Models.Workout.Exercise;
import edu.uco.schambers4.octane.Models.Workout.ExerciseContainer;
import edu.uco.schambers4.octane.Models.Workout.ExerciseMeasure;
import edu.uco.schambers4.octane.Models.Workout.Intensity;
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

    @Bind(R.id.update_exercise_fab)
    FloatingActionButton mUpdateExercise;
    @Bind(R.id.delete_exercise_fab)
    FloatingActionButton mDeleteExercise;

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
        int i = getShownIndex();
        ArrayList<Exercise> exercises = mExerciseContainer.getExercises(getActivity().getApplicationContext());
        mExercise = exercises.get(i);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise_detail, container, false);
        ButterKnife.bind(this, view);


        if (getShownIndex() > -1) {
            fillForm(container.getContext());
        }

        mUpdateExercise.setOnClickListener(v -> doUpdate());
        mDeleteExercise.setOnClickListener(v -> doDelete());

        return view;
    }

    private void doUpdate() {

        Context context = getActivity().getApplicationContext();

        String fExerciseName;
        fExerciseName = mExerciseNameEt.getText().toString();
        if (fExerciseName.trim().length() == 0) {
            Toast.makeText(context, "Exercise Name", Toast.LENGTH_SHORT).show();
            return;
        }
        mExercise.setName(fExerciseName);


        String fExerciseDescription;
        fExerciseDescription = mExerciseDescriptionEt.getText().toString();
        if (fExerciseDescription.trim().length() == 0) {
            Toast.makeText(context, "Exercise Description", Toast.LENGTH_SHORT).show();
            return;
        }
        mExercise.setDescription(fExerciseDescription);


        Exercise.MuscleGroup fMusleGroup = null;
        String muscleGroupItem = mMuscleGroupSp.getSelectedItem().toString();
        for (Exercise.MuscleGroup m : Exercise.MuscleGroup.values()) {
            if (Objects.equals(m.getGroupName(), muscleGroupItem)) {
                fMusleGroup = m;
            }
        }
        if (fMusleGroup == null) {
            Toast.makeText(context, "Muscle Group", Toast.LENGTH_SHORT).show();
            return;
        }
        mExercise.setMuscleGroup(fMusleGroup);


        Exercise.ExerciseType fExerciseType = null;
        String exerciseTypeItem = mExerciseTypeSp.getSelectedItem().toString();
        for (Exercise.ExerciseType t : Exercise.ExerciseType.values()) {
            if (Objects.equals(t.getExerciseTypeName(), exerciseTypeItem)) {
                fExerciseType = t;
            }
        }
        if (fExerciseType == null) {
            Toast.makeText(context, "Exercise Type", Toast.LENGTH_SHORT).show();
            return;
        }
        mExercise.setExerciseType(fExerciseType);


        String fResistance;
        fResistance = mForceValEt.getText().toString();
        String fMeasure;
        fMeasure = mMeasureValEt.getText().toString();

        ExerciseMeasure.Unit fResistanceUnit = null;
        String rUnitItem = mForceMsrSp.getSelectedItem().toString();
        for (ExerciseMeasure.Unit u : ExerciseMeasure.Unit.values()) {
            if (Objects.equals(u.getUnitName(), rUnitItem)) {
                fResistanceUnit = u;
            }
        }
        if (fResistanceUnit == null) {
            Toast.makeText(context, "Resistance Unit", Toast.LENGTH_SHORT).show();
            return;
        }

        ExerciseMeasure.Unit fMeasureUnit = null;
        String mUnitItem = mMeasureUnitSp.getSelectedItem().toString();
        for (ExerciseMeasure.Unit u : ExerciseMeasure.Unit.values()) {
            if (Objects.equals(u.getUnitName(), mUnitItem)) {
                fMeasureUnit = u;
            }
        }
        if (fMeasureUnit == null) {
            Toast.makeText(context, "Measure Unit", Toast.LENGTH_SHORT).show();
            return;
        }

        ExerciseMeasure fExerciseMeasure;
        try {
            fExerciseMeasure = new ExerciseMeasure();
            fExerciseMeasure.setForce(Integer.parseInt(fResistance));
            fExerciseMeasure.setMeasure(Integer.parseInt(fMeasure));
            fExerciseMeasure.setForceUnits(fResistanceUnit);
            fExerciseMeasure.setMeasureUnits(fMeasureUnit);
        } catch (Exception e) {
            Toast.makeText(context, "Exercise Measure", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return;
        }
        mExercise.setMaxIntensityExerciseMeasure(fExerciseMeasure);

        String fHighResPct;
        String fHighMesPct;
        Intensity fHighIntensity;
        fHighResPct = mHighQtyEt.getText().toString();
        fHighMesPct = mHighMeasureEt.getText().toString();
        try {
            fHighIntensity = new Intensity(
                    Integer.parseInt(fHighResPct),
                    Integer.parseInt(fHighMesPct));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        mExercise.setHighIntensity(fHighIntensity);

        String fMedResPct;
        String fMedMesPct;
        Intensity fMedIntensity;
        fMedResPct = mMedQtyEt.getText().toString();
        fMedMesPct = mMedMeasureEt.getText().toString();
        try {
            fMedIntensity = new Intensity(
                    Integer.parseInt(fMedResPct),
                    Integer.parseInt(fMedMesPct));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        mExercise.setMedIntensity(fMedIntensity);

        String fLowResPct;
        String fLowMesPct;
        Intensity fLowIntensity;
        fLowResPct = mLowQtyEt.getText().toString();
        fLowMesPct = mLowMeasureEt.getText().toString();
        try {
            fLowIntensity = new Intensity(
                    Integer.parseInt(fLowResPct),
                    Integer.parseInt(fLowMesPct));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        mExercise.setLowIntensity(fLowIntensity);


        mExerciseContainer.delete(getActivity().getApplicationContext(), mExercise);
        mExerciseContainer.save(getActivity().getApplicationContext(), mExercise);
        getActivity().onBackPressed();
    }

    private void doDelete() {
        mExerciseContainer.delete(getActivity().getApplicationContext(), mExercise);
        getActivity().onBackPressed();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);

    }


    private void fillForm(Context context) {

        mExerciseNameEt.setText(String.format("%s", mExercise.getName()));

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
        mForceValEt.setText(String.format("%s", mExercise.getMaxIntensityExerciseMeasure().getForce().toString()));

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
