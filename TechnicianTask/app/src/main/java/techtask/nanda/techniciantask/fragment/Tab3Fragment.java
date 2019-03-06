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
// Menampung SettingsFragment
public class Tab3Fragment extends Fragment {


    public Tab3Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    // Menampilkan fragment Settings
    private void init() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frame_content, new SettingFragment());
        ft.commit();
    }
}
