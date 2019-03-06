package techtask.nanda.techniciantask.fragment.TaskReport;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import techtask.nanda.techniciantask.R;
import techtask.nanda.techniciantask.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskReportDataWODetailFragment extends BaseFragment {
    @BindView(R.id.tv_wo_detail_1)
    TextView tvWODetail1;

    @BindView(R.id.tv_wo_detail_2)
    TextView tvWODetail2;

    int idWO;

    public TaskReportDataWODetailFragment() {
        // Required empty public constructor
    }

    public static TaskReportDataWODetailFragment newInstance(int idWO) {
        TaskReportDataWODetailFragment frag = new TaskReportDataWODetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("idWO", idWO);
        frag.setArguments(bundle);

        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_report_data_wodetail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initIntent();
        init();
    }

    private void initIntent() {
        idWO = getArguments().getInt("idWO");
    }

    private void init() {
        //tvWODetail1.setText("WO detail 1");
        tvWODetail2.setText("WO detail 2");
    }
}
