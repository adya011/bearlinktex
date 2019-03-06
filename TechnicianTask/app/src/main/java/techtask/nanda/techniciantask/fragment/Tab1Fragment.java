package techtask.nanda.techniciantask.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import techtask.nanda.techniciantask.R;

/**
 * A simple {@link Fragment} subclass.
 */
// Menampung fragment HomeFragment
public class Tab1Fragment extends Fragment {


    public Tab1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    // Menampilkan fragment Home
    private void init() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frame_content, new HomeFragment());
        ft.commit();
    }
}
