package edu.uco.schambers4.octane.Fragments.Dashboard;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.uco.schambers4.octane.R;


public class DetailsForDateFragment extends Fragment {
    private static final String ARG_DATE = "DATE";

    private Date date;

    @Bind(R.id.details_lbl)
    TextView detailsTitle;

    public static DetailsForDateFragment newInstance(Date param1)
    {
        DetailsForDateFragment fragment = new DetailsForDateFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public DetailsForDateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            date =  (Date)getArguments().getSerializable(ARG_DATE);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_details_for_date, container, false);
        ButterKnife.bind(this, view);

        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = formatter.format(date);

        detailsTitle.setText(String.format("%s", formattedDate));

        return view;
    }
}
