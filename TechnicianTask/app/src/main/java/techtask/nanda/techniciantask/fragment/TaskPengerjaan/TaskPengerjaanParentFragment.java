package techtask.nanda.techniciantask.fragment.TaskPengerjaan;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import techtask.nanda.techniciantask.DashboardActivity;
import techtask.nanda.techniciantask.R;
import techtask.nanda.techniciantask.adapter.TasksPagerAdapter;
import techtask.nanda.techniciantask.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
// Fragment yang menampung Task Pengerjaan Done dan Pending
public class TaskPengerjaanParentFragment extends BaseFragment {
    @BindView(R.id.pager)
    ViewPager viewPager;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    private String TAG = "myd_tasks_frag";

    public TaskPengerjaanParentFragment() {
        // Required empty public constructor
    }

    public static TaskPengerjaanParentFragment newInstance(){
        TaskPengerjaanParentFragment frag = new TaskPengerjaanParentFragment();
        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //((DashboardActivity) getActivity()).setToolbarTitle("Tasks");
        initPager();
    }

    // Menampung Task Pengerjaan done dan pending
    private void initPager(){
        Log.d(TAG, "init pager");
        TasksPagerAdapter adapter = new TasksPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new TaskPengerjaanPendingFragment(), "Progress");
        adapter.addFragment(new TaskPengerjaanDoneFragment(), "Done");
        adapter.addFragment(new TaskPengerjaanFailedFragment(), "Failed");
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
    }
}
