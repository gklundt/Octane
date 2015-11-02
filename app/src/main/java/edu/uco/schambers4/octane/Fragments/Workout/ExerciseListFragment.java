package edu.uco.schambers4.octane.Fragments.Workout;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import edu.uco.schambers4.octane.Activities.MainActivity;
import edu.uco.schambers4.octane.Models.Workout.ExerciseAdapter;
import edu.uco.schambers4.octane.Models.Workout.ExerciseContainer;
import edu.uco.schambers4.octane.R;

public class ExerciseListFragment extends ListFragment implements AddFragment.OnAddFabClick {

    private int mCurCheckPosition = 0;

    private final String FABTAG = "add_exercise_fab";

    @Override
    public void onResume() {
        super.onResume();
        AddFragment addFragment = AddFragment.add_fab(getActivity(), R.id.fragment_container, FABTAG);
        if (addFragment != null) {
            addFragment.AddOnAddFabClickListener(this);
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        AddFragment.remove_fab(getActivity(), R.id.fragment_container, FABTAG);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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
        Fragment editFragment = ExerciseDetailFragment.newInstance(mCurCheckPosition);
        MainActivity main = (MainActivity) getActivity();
        main.launchFragment(editFragment);
    }


    @Override
    public void add() {

        ExerciseContainer exerciseContainer = ExerciseContainer.getInstance();
        int val = exerciseContainer.createDefaultExercise(getActivity().getApplicationContext());
        showDetails(val);
    }
}



