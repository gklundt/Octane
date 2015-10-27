package edu.uco.schambers4.octane.Fragments;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import edu.uco.schambers4.octane.Activities.ExerciseDetailActivity;
import edu.uco.schambers4.octane.Models.ExerciseAdapter;
import edu.uco.schambers4.octane.Models.ExerciseContainer;
import edu.uco.schambers4.octane.R;
//import edu.uco.schambers4.octane.Fragments.dummy.xxDummyContent;

/**
 * A fragment representing a list of Items.
 * <p>
 * <p>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class WorkoutListFragment extends ListFragment {

    /* There are not any parameters yet, but leaving just in case */
//    // TO DO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TO DO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    private ExerciseContainer mExerciseContainer;
    private boolean mDualPane;
    private int mCurCheckPosition = 0;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mExerciseContainer = ExerciseContainer.getInstance();
        ExerciseAdapter adapter = new ExerciseAdapter(getActivity(), mExerciseContainer.getExercises());
        setListAdapter(adapter);

        View detailsFrame = getActivity().findViewById(R.id.exercise_item_details);
        mDualPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;


        if (savedInstanceState!= null){
            mCurCheckPosition = savedInstanceState.getInt("curChoice",0);
        }

        if (mDualPane){
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            showDetails(mCurCheckPosition);
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

        if(mDualPane){
            getListView().setItemChecked(index,true);
            ExerciseDetailFragment details = (ExerciseDetailFragment) getFragmentManager().findFragmentById(R.id.exercise_item_details);
            if(details==null || details.getShownIndex() != index){

                details = ExerciseDetailFragment.newInstance(index);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                if(index == 0){
                    ft.replace(R.id.exercise_item_details, details);
                } else {
                    ft.replace(R.id.exercise_item_details, details);
                }

                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        } else {

            Intent intent = new Intent();
            intent.setClass(getActivity(), ExerciseDetailActivity.class);
            intent.putExtra("index", index);
            startActivity(intent);
        }
    }


//    private OnFragmentInteractionListener mListener;
//
//    // TO DO: Rename and change types of parameters
//    public static ExerciseListFragment newInstance(String param1, String param2) {
//        ExerciseListFragment fragment = new ExerciseListFragment();
//        Bundle args = new Bundle();
////        args.putString(ARG_PARAM1, param1);
////        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    /**
//     * Mandatory empty constructor for the fragment manager to instantiate the
//     * fragment (e.g. upon screen orientation changes).
//     */
//    public ExerciseLFragment() {
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
////        if (getArguments() != null) {
////            mParam1 = getArguments().getString(ARG_PARAM1);
////            mParam2 = getArguments().getString(ARG_PARAM2);
////        }
//
//        // TO DO: Change Adapter to display your content
//        setListAdapter(new ArrayAdapter<xxDummyContent.DummyItem>(getActivity(),
//                android.R.layout.simple_list_item_1, android.R.id.text1, xxDummyContent.ITEMS));
//    }
//
//
//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        public void onFragmentInteraction(String id);
//    }

}
