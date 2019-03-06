package techtask.nanda.techniciantask.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techtask.nanda.techniciantask.DashboardActivity;
import techtask.nanda.techniciantask.LoginActivity;
import techtask.nanda.techniciantask.MyService;
import techtask.nanda.techniciantask.R;
import techtask.nanda.techniciantask.adapter.TaskAvailableListAdapter;
import techtask.nanda.techniciantask.adapter.TaskAvailableListItemListener;
import techtask.nanda.techniciantask.api.ApiInterface;
import techtask.nanda.techniciantask.api.RestProvider;
import techtask.nanda.techniciantask.base.BaseFragment;
import techtask.nanda.techniciantask.fragment.TaskPengerjaan.TaskPengerjaanPendingFragment;
import techtask.nanda.techniciantask.model.TaskPengerjaan;
import techtask.nanda.techniciantask.model._realm.RTaskPengerjaan;
import techtask.nanda.techniciantask.model.pengerjaan.CreateTaskPengerjaan;
import techtask.nanda.techniciantask.model.pengerjaan.CreateTaskPengerjaanResponse;
import techtask.nanda.techniciantask.model.task.Result;
import techtask.nanda.techniciantask.model.user.LoginUser;
import techtask.nanda.techniciantask.model.user.LoginUserResult;

/**
 * A simple {@link Fragment} subclass.
 */
// Menampilkan list dari socket
public class HomeFragment extends BaseFragment {
    // List menampilkan task dari socket
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    // Switch on off untuk mengambil data socket
    /*@BindView(R.id.sw_online)
    Switch swOnline;*/

    @BindView(R.id.tv_online)
    TextView tvOnline;

    LinearLayout bertexSwicth;
    static boolean bertexBool;

    public static CountDownTimer countDownTimer;
    private String TAG = "myd_home";
    TaskAvailableListAdapter taskListAdapter = new TaskAvailableListAdapter(MyService.results);

    static final long CHECK_AVAILABLE_TASK_INTERVAL = 3000;
    long timeLeft = CHECK_AVAILABLE_TASK_INTERVAL;

    static final long NOTIFICATION_CHECK_INTERVAL = 9000;
    long notificationCheckTimeLeft = NOTIFICATION_CHECK_INTERVAL;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment frag = new HomeFragment();
        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //((DashboardActivity) getActivity()).setToolbarTitle("Home");

        bertexSwicth = ((DashboardActivity) getActivity()).findViewById(R.id.lay_bertex_switch);

        init();
    }


    // Inisialisasi awal
    private void init() {
        initMonitoring();
        //getActivity().setTitle("Home");
        //startTimer();

        /*boolean isScanAvailTask = sharedPrefManager.isGetTaskSwitch();
        swOnline.setChecked(isScanAvailTask);

        if (swOnline.isChecked()) {
            getActivity().startService(new Intent(getActivity().getApplicationContext(), MyService.class));
            tvOnline.setText("ONLINE");
            ((DashboardActivity) getActivity()).toolbarToggle(true);

        } else {
            getActivity().stopService(new Intent(getActivity().getApplicationContext(), MyService.class));
            tvOnline.setText("OFFLINE");
            ((DashboardActivity) getActivity()).toolbarToggle(false);
        }

        swOnline.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    getActivity().startService(new Intent(getActivity().getApplicationContext(), MyService.class));
                    Log.d(TAG, "SERVCE START");
                    sharedPrefManager.setGetTaskSwitch(true);
                    tvOnline.setText("ONLINE");
                    ((DashboardActivity) getActivity()).toolbarToggle(true);

                } else {
                    getActivity().stopService(new Intent(getActivity().getApplicationContext(), MyService.class));
                    Log.d(TAG, "SERVCE STOP");
                    sharedPrefManager.setGetTaskSwitch(false);
                    tvOnline.setText("OFFLINE");
                    ((DashboardActivity) getActivity()).toolbarToggle(false);
                    recyclerView.setAdapter(null);
                }
            }
        });*/

        boolean isScanAvailTask = sharedPrefManager.isGetTaskSwitch();
        bertexBool = isScanAvailTask;

        bertexSwicth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bertexBool) {
                    // offline
                    bertexBool = false;
                    getActivity().stopService(new Intent(getActivity().getApplicationContext(), MyService.class));
                    Log.d(TAG, "SERVCE STOP");
                    sharedPrefManager.setGetTaskSwitch(false);
                    tvOnline.setText("OFFLINE");
                    ((DashboardActivity) getActivity()).toolbarToggle(false);
                    recyclerView.setAdapter(null);

                } else {
                    // online
                    bertexBool = true;
                    getActivity().startService(new Intent(getActivity().getApplicationContext(), MyService.class));
                    Log.d(TAG, "SERVCE START");
                    sharedPrefManager.setGetTaskSwitch(true);
                    tvOnline.setText("ONLINE");
                    ((DashboardActivity) getActivity()).toolbarToggle(true);
                }
            }
        });
    }

    private void initMonitoring() {
        initRecyclerView();
        getAvailableTaskList();
        startTimer();
    }

    // Timer untuk mengambil data task list
    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long l) {
                timeLeft = l;
            }

            @Override
            public void onFinish() {
                timeLeft = CHECK_AVAILABLE_TASK_INTERVAL;
                getAvailableTaskList();
                startTimer();
            }
        }.start();
    }

    private void initRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        /*recyclerView.setItemAnimator(new DefaultItemAnimator());*/
        recyclerView.setItemAnimator(null);
    }

    // Menampilkan data task list
    private void getAvailableTaskList() {
        try {
            if(sharedPrefManager.isGetTaskSwitch()) {
                Parcelable recyclerViewState;
                recyclerViewState = recyclerView.getLayoutManager().onSaveInstanceState();

                recyclerView.getRecycledViewPool().clear();
                taskListAdapter.notifyDataSetChanged();

                recyclerView.setAdapter(taskListAdapter);
                recyclerView.addOnItemTouchListener(new TaskAvailableListItemListener(getContext(), recyclerView,
                        new TaskAvailableListItemListener.ClickListener() {
                            @Override
                            public void onClick(View view, int position) {
                                String id_wo = MyService.results.get(position).getId_wo();
                                String nama_wo = MyService.results.get(position).getNama_wo();
                                String ket_wo = MyService.results.get(position).getKet_wo();
                            /*String id_teknisi = LoginFragment.id_teknisi;*/
                                String id_teknisi = sharedPrefManager.getIdTeknisi();
                                itemSelectWo(id_teknisi, id_wo, nama_wo, ket_wo);
                            }

                            @Override
                            public void onLongClick(View view, int position) {

                            }
                        }));
                taskListAdapter.notifyDataSetChanged();
                recyclerView.getLayoutManager().onRestoreInstanceState(recyclerViewState);
            }

        } catch (Exception e) {
            Log.d(TAG, "Exception Home: " + e);
        }
    }

    // Dipanggil jika item diklik, menampilkan pop up
    private void itemSelectWo(final String id_teknisi, final String id_wo, final String nama_wo, String ket_wo) {
        String alert1 = "" + nama_wo;
        String alert2 = "Detail: \n" + ket_wo;
        String alert3 = "Ambil WO ? ";

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(alert1 + "\n\n" + alert2 + "\n\n\n" + alert3);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                createTaskPengerjaan(id_teknisi, id_wo, "1", "2019-01-25");
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void createTaskPengerjaan(String id_teknisi, String id_wo, String status_task, String tanggal_task) {
        ApiInterface apiInterface = RestProvider.getTeknisiController().create(ApiInterface.class);
        Call<CreateTaskPengerjaanResponse> call = apiInterface.createTaskWo(new CreateTaskPengerjaan(id_teknisi, id_wo, status_task, tanggal_task));
        Log.d(TAG, "== Create Task Pengerjaan ==");
        Log.d(TAG, "BODY: " + "id_teknisi: " + id_teknisi + ", id_wo: " + id_wo + ", status_task: " + status_task + ", tgl_task: " + tanggal_task);
        call.enqueue(new Callback<CreateTaskPengerjaanResponse>() {
            @Override
            public void onResponse(Call<CreateTaskPengerjaanResponse> call, Response<CreateTaskPengerjaanResponse> response) {
                try {
                    Log.d(TAG, "URL: " + response.raw().request().url());
                    Log.d(TAG, "API response Create Pengerjaan. Code: " + response.code());

                    if (response.code() == 200 && response.body().getRes_code() == 200) {
                        /*Intent intent = new Intent(getContext(), TaskPengerjaanPendingFragment.class);
                        startActivity(intent);*/
                        ((DashboardActivity) getActivity()).selectNav(2);

                    } else {
                        utils.alertDialogOK(getContext(), getResources().getString(R.string.server_problem));
                    }

                } catch (Exception e) {
                    utils.alertDialogOK(getContext(), getResources().getString(R.string.problem_occured));
                }
            }

            @Override
            public void onFailure(Call<CreateTaskPengerjaanResponse> call, Throwable t) {
                try {
                    if (t instanceof SocketTimeoutException) {
                        utils.alertDialogOK(getContext(), getResources().getString(R.string.conn_time_out));

                    } else {
                        utils.alertDialogOK(getContext(), getResources().getString(R.string.problem_occured));
                    }
                } catch (Exception e) {
                    Log.e(TAG, "Exception createTaskPengerjaan: " + e);
                }
            }
        });
    }

}
