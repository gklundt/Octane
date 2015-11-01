package edu.uco.schambers4.octane.Fragments.Exercises;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import edu.uco.schambers4.octane.Activities.MainActivity;
import edu.uco.schambers4.octane.Models.Workout.ExerciseAdapter;
import edu.uco.schambers4.octane.Models.Workout.ExerciseContainer;
import edu.uco.schambers4.octane.R;

public class ExerciseListFragment extends ListFragment {

    private int mCurCheckPosition = 0;
    FloatingActionButton mAddExercise;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflater.inflate(R.layout.list_fragment_with_fab, container);
        View view = super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        mAddExercise = (FloatingActionButton) getActivity().findViewById(R.id.exercises_fab);
        ViewGroup p1 = (ViewGroup)mAddExercise.getParent();
        ViewGroup p2 = (ViewGroup)this.getListView().getParent();

        ViewGroup.LayoutParams lp = mAddExercise.getLayoutParams();

        p1.removeView(mAddExercise);


        p2.addView(mAddExercise,lp);


        mAddExercise.setOnClickListener(v -> showDetails(-1));



        ExerciseContainer exerciseContainer = ExerciseContainer.getInstance();
        ExerciseAdapter adapter = exerciseContainer.getExerciseAdapter(getActivity().getApplicationContext());
        setListAdapter(adapter);

        if (savedInstanceState != null) {
            mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", mCurCheckPosition);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        showDetails(position);
    }

    private void showDetails(int index) {
        mCurCheckPosition = index;

        Fragment editFragment = ExerciseDetailFragment.newInstance(index);
        ((MainActivity) getActivity()).launchFragment(editFragment);
    }

}
