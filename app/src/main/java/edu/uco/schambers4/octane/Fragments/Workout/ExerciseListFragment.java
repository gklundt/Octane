package edu.uco.schambers4.octane.Fragments.Workout;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import edu.uco.schambers4.octane.Activities.MainActivity;
import edu.uco.schambers4.octane.Models.Workout.ExerciseAdapter;
import edu.uco.schambers4.octane.Models.Workout.ExerciseContainer;

public class ExerciseListFragment extends ListFragment {

    private int mCurCheckPosition = 0;

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

}
