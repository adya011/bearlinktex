package techtask.nanda.techniciantask.fragment.SettingsDetail;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import techtask.nanda.techniciantask.R;
import techtask.nanda.techniciantask.SettingsActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class GuideFragment extends Fragment {


    public GuideFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_guide, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((SettingsActivity) getActivity()).setToolbarTitle("Panduan");
    }
}
