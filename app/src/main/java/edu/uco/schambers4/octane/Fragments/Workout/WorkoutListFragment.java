package edu.uco.schambers4.octane.Fragments.Workout;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import edu.uco.schambers4.octane.Activities.MainActivity;
import edu.uco.schambers4.octane.Models.Workout.ExerciseContainer;
import edu.uco.schambers4.octane.Models.Workout.WorkoutAdapter;
import edu.uco.schambers4.octane.Models.Workout.WorkoutContainer;

public class WorkoutListFragment extends ListFragment {

    private ExerciseContainer mExerciseContainer = ExerciseContainer.getInstance();
    private WorkoutContainer mWorkoutContainer =  WorkoutContainer.getInstance(mExerciseContainer.getRepository());
    private int mCurCheckPosition = 0;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        WorkoutAdapter adapter = mWorkoutContainer.getWorkoutAdapter(getActivity().getApplicationContext());

        setListAdapter(adapter);

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

        Fragment editFragment = WorkoutDetailFragment.newInstance(index);
        ((MainActivity) getActivity()).launchFragment(editFragment);

    }

}
