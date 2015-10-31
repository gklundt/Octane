package edu.uco.schambers4.octane.Fragments.Workouts;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import edu.uco.schambers4.octane.Activities.MainActivity;
import edu.uco.schambers4.octane.Models.Workout.WorkoutAdapter;
import edu.uco.schambers4.octane.Models.Workout.WorkoutContainer;
import edu.uco.schambers4.octane.R;

public class WorkoutListFragment extends ListFragment {

    private WorkoutContainer mWorkoutContainer;
    private int mCurCheckPosition = 0;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mWorkoutContainer = WorkoutContainer.getInstance();
        WorkoutAdapter adapter = new WorkoutAdapter(getActivity(), mWorkoutContainer.getWorkouts());
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
