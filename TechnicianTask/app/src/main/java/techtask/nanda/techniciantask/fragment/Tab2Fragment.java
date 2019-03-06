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
import techtask.nanda.techniciantask.fragment.TaskPengerjaan.TaskPengerjaanParentFragment;

/**
 * A simple {@link Fragment} subclass.
 */
// Menampung TaskPengerjaanParentFagment
public class Tab2Fragment extends Fragment {


    public Tab2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    // Menampilkan fragment Task Pengerjaan, ke TaskPengerjaanParentFragment
    private void init() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frame_content, new TaskPengerjaanParentFragment());
        ft.commit();
    }
}
