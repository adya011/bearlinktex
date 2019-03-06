package techtask.nanda.techniciantask.fragment.TaskPengerjaan;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techtask.nanda.techniciantask.DashboardActivity;
import techtask.nanda.techniciantask.R;
import techtask.nanda.techniciantask.TaskReportActivity;
import techtask.nanda.techniciantask.adapter.RTaskPendingListAdapter;
import techtask.nanda.techniciantask.adapter.TaskDoneListAdapter;
import techtask.nanda.techniciantask.adapter.TaskDoneListItemListener;
import techtask.nanda.techniciantask.adapter.TaskPendingListAdapter;
import techtask.nanda.techniciantask.adapter.TaskPendingListItemListener;
import techtask.nanda.techniciantask.api.ApiInterface;
import techtask.nanda.techniciantask.api.RestProvider;
import techtask.nanda.techniciantask.base.BaseFragment;
import techtask.nanda.techniciantask.model.TaskPengerjaan;
import techtask.nanda.techniciantask.model.TaskPengerjaanResponse;
import techtask.nanda.techniciantask.model._realm.RTaskPengerjaan;

/**
 * A simple {@link Fragment} subclass.
 */
// Fragment Task Pengerjaan Done
public class TaskPengerjaanDoneFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private static final String TAG = "myd_taskpengerjaan_done";
    /*List<String> taskList = new ArrayList<>();*/

    public static List<TaskPengerjaan> taskPengerjaanList = new ArrayList<>();
    TaskPendingListAdapter taskPendingListAdapter = new TaskPendingListAdapter(taskPengerjaanList);

    public TaskPengerjaanDoneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_done, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        taskPengerjaanList.clear();
        init();
    }

    // Inisialisasi awal
    private void init() {
        requestTaskDoneList();
    }

    // Data dummy untuk Task Done
    private void requestTaskDoneList() {
        String id_teknisi = sharedPrefManager.getIdTeknisi();
        getTaskPengerjaan(id_teknisi);
    }

    private void getTaskPengerjaan(final String id_teknisi) {
        ApiInterface apiInterface = RestProvider.getTeknisiController().create(ApiInterface.class);
        Call<TaskPengerjaanResponse> call = apiInterface.callTaskWoByIdTeknisi(id_teknisi, "2");
        call.enqueue(new Callback<TaskPengerjaanResponse>() {
            @Override
            public void onResponse(Call<TaskPengerjaanResponse> call, Response<TaskPengerjaanResponse> response) {
                Log.d(TAG, "URL: " + response.raw().request().url());
                Log.d(TAG, "API response get task pengerjaan DONE. Code: " + response.code());

                try {
                    if (response.code() == 200 && response.body().getRes_code() == 200) {
                        int taskSize = response.body().getStatus().getResults().size();
                        for (int i = 0; i < taskSize; i++) {
                            String nama_wo = response.body().getStatus().getResults().get(i).getNama_wo();
                            String ket_wo = response.body().getStatus().getResults().get(i).getKet_wo();
                            String id_wo = response.body().getStatus().getResults().get(i).getId_wo();
                            String status_task = response.body().getStatus().getResults().get(i).getStatus_task();
                            String nama_person = response.body().getStatus().getResults().get(i).getNama_person();
                            String id_task = response.body().getStatus().getResults().get(i).getId_task();
                            taskPengerjaanList.add(new TaskPengerjaan(nama_wo, ket_wo, id_wo, status_task, nama_person, id_task));
                        }
                        onProgTasksAdapter();
                    } else {

                    }
                } catch (Exception e) {
                    Log.e(TAG, "Get Task Pengerjaan exception: " + e);
                }
            }

            @Override
            public void onFailure(Call<TaskPengerjaanResponse> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    utils.alertDialogOK(getContext(), getResources().getString(R.string.conn_time_out));
                } else {
                    Log.d(TAG, "Failure: " + t);
                }
            }
        });
    }

    // Menampilkan task pengerjaan dari db ke list
    private void onProgTasksAdapter() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(taskPendingListAdapter);

        recyclerView.addOnItemTouchListener(new TaskPendingListItemListener(getContext(), recyclerView,
                new TaskPendingListItemListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        /*String id_wo = taskPengerjaanList.get(position).getId_wo();
                        String ket_wo = taskPengerjaanList.get(position).getKet_wo();*/
                        String id_task = taskPengerjaanList.get(position).getId_task();

                        Log.d(TAG, "Task Pengerjaan Selected, ID Pengerjaan: " + id_task);

                        gotoReportData(id_task);

                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));
    }

    // Menuju TaskReportActivity
    private void gotoReportData(String id_task) {
        Intent intent = new Intent(getContext(), TaskReportActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("id_task", id_task);
        bundle.putBoolean("is_pending", false);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
