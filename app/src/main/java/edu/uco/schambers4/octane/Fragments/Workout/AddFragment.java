package edu.uco.schambers4.octane.Fragments.Workout;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.uco.schambers4.octane.Activities.MainActivity;
import edu.uco.schambers4.octane.R;


public class AddFragment extends Fragment {


    OnAddFabClick mListener;

    public void AddOnAddFabClickListener(OnAddFabClick listener){
        mListener = listener;
    }

    public static AddFragment add_fab(MainActivity activity) {

        MainActivity mainActivity = activity;
        FragmentManager fragmentManager = mainActivity.getFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag("add_exercise");
        if (fragment == null) {
            AddFragment addFragment = new AddFragment();
            fragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, addFragment, "add_exercise")
                    .commit();

            return addFragment;

        }
        return null;
    }


    public static void remove_fab(MainActivity activity) {
        MainActivity mainActivity = activity;
        FragmentManager fragmentManager = mainActivity.getFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag("add_exercise");
        if (fragment != null) {
            fragmentManager
                    .beginTransaction()
                    .remove(fragment)
                    .commit();

            ViewGroup container = (ViewGroup) mainActivity.findViewById(R.id.fragment_container);
            container.removeView(mainActivity.findViewById(R.id.fucking_delete_me));
        }
    }

    @Bind(R.id.generic_add_fab)
    FloatingActionButton mFloatingActionButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_fab, container);
        ButterKnife.bind(this, view);
        mFloatingActionButton.setOnClickListener(v -> mListener.add());
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public interface OnAddFabClick {
        void add();
    }
}
