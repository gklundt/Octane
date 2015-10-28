package edu.uco.schambers4.octane.Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.uco.schambers4.octane.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutDetailFragment extends Fragment {


    public WorkoutDetailFragment() {
        // Required empty public constructor
    }

    public int getShownIndex() {
        return getArguments().getInt("index", 0);
    }

    public static WorkoutDetailFragment newInstance(int index) {
        WorkoutDetailFragment f = new WorkoutDetailFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exercise_detail, container, false);
    }


}