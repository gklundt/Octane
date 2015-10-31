package edu.uco.schambers4.octane.Fragments.Exercises;

import android.app.Fragment;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import edu.uco.schambers4.octane.Activities.MainActivity;
import edu.uco.schambers4.octane.Models.Workout.ExerciseAdapter;
import edu.uco.schambers4.octane.Models.Workout.ExerciseContainer;
import edu.uco.schambers4.octane.R;
public class ExerciseListFragment extends ListFragment {

    private ExerciseContainer mExerciseContainer;
    private int mCurCheckPosition = 0;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mExerciseContainer = ExerciseContainer.getInstance();
        ExerciseAdapter adapter = new ExerciseAdapter(getActivity(), mExerciseContainer.getExercises());
        setListAdapter(adapter);

        View detailsFrame = getActivity().findViewById(R.id.exercise_item_details);

        if (savedInstanceState!= null){
            mCurCheckPosition = savedInstanceState.getInt("curChoice",0);
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

    private void showDetails(int index){

        mCurCheckPosition = index;

        Fragment editFragment = ExerciseDetailFragment.newInstance(index);
        ((MainActivity) getActivity()).launchFragment(editFragment);

    }

}
