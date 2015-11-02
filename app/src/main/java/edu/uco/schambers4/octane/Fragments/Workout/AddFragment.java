package edu.uco.schambers4.octane.Fragments.Workout;

import android.app.Activity;
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

    public static AddFragment add_fab(Activity activity, Integer container, String tag) {

        FragmentManager fragmentManager = activity.getFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment == null) {
            AddFragment addFragment = new AddFragment();
            fragmentManager
                    .beginTransaction()
                    .add(container, addFragment, tag)
                    .commit();

            return addFragment;

        }
        return null;
    }


    public static void remove_fab(Activity activity, Integer container, String tag) {
        FragmentManager fragmentManager = activity.getFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment != null) {
            fragmentManager
                    .beginTransaction()
                    .remove(fragment)
                    .commit();

            ViewGroup containerVg = (ViewGroup) activity.findViewById(container);
            containerVg.removeView(activity.findViewById(R.id.generic_add_fab_fragment_handle));
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
