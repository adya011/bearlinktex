package techtask.nanda.techniciantask.fragment;


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
import io.realm.RealmResults;
import techtask.nanda.techniciantask.R;
import techtask.nanda.techniciantask.TaskReportActivity;
import techtask.nanda.techniciantask.adapter.TasksPagerAdapter;
import techtask.nanda.techniciantask.base.BaseFragment;
import techtask.nanda.techniciantask.fragment.TaskReport.TaskReportDataFragment;
import techtask.nanda.techniciantask.fragment.TaskReport.TaskReportImageFragment;
import techtask.nanda.techniciantask.model._realm.RTaskReport;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskReportFragment extends BaseFragment {
    @BindView(R.id.pager)
    ViewPager viewPager;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    private static final String TAG = "myd_task_report";

    public TaskReportFragment() {
        // Required empty public constructor
    }

    public static TaskReportFragment newInstance(int id_task) {
        TaskReportFragment frag = new TaskReportFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id_task", id_task);
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "Task Report Parent View Created");
        initToolbar();
        init();
    }

    private void initToolbar() {
        getActivity().setTitle("Tasks");
    }

    private void init() {
        Log.d(TAG, "== Task Report Parent ==");

        int idTaskPengerjaan = 0;
        Bundle args = getArguments();
        if (args != null) {
            idTaskPengerjaan = getArguments().getInt("id_task");
        }

        Log.d(TAG, "ID Pengerjaan : " + idTaskPengerjaan);

        /*initPager(getIdTaskPengerjaan);*/

        reqReportByIdTaskPengerjaan(idTaskPengerjaan);
        initPager(idTaskPengerjaan);
    }

    private void reqReportByIdTaskPengerjaan(int idTaskPengerjaan) {
        RealmResults<RTaskReport> results = ((TaskReportActivity) getActivity()).realm
                .where(RTaskReport.class)
                .equalTo("id_task", idTaskPengerjaan).findAll();

        for(RTaskReport taskReport : results){
            Log.d(TAG, "Id Task Report: " + taskReport.getId_task());
            Log.d(TAG, "Nama Task Pengerjaan: " + taskReport.getNama_report());
            ((TaskReportActivity) getActivity()).id_task = taskReport.getId_task();
        }
    }

    private void initPager(int idTaskPengerjaan){
        TasksPagerAdapter adapter = new TasksPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new TaskReportDataFragment(), "Data");
        adapter.addFragment(new TaskReportImageFragment(), "Image");
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
    }
}
