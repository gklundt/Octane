package edu.uco.schambers4.octane.Fragments.Workout;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.uco.schambers4.octane.R;


public class AddFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflater.inflate(R.layout.fragment_add_fab, container);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
