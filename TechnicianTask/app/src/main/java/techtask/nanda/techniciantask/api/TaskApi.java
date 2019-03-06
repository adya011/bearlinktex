package techtask.nanda.techniciantask.api;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.net.SocketTimeoutException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techtask.nanda.techniciantask.R;
import techtask.nanda.techniciantask.model.TaskPengerjaan;
import techtask.nanda.techniciantask.model.TaskPengerjaanResponse;
import techtask.nanda.techniciantask.model.report.CreateReportTeknisi;
import techtask.nanda.techniciantask.model.report.CreateReportTeknisiResponse;
import techtask.nanda.techniciantask.utils.MyUtils;

/**
 * Created by nandana.samudera on 13/12/2018.
 */

public class TaskApi {
    Activity activity;
    Context context;
    String TAG = "myd_task_api";
    MyUtils utils;

    public TaskApi(Activity activity, Context context, MyUtils utils) {
        this.activity = activity;
        this.context = context;
        this.utils = utils;
    }

    /*public void proceedCreateTaskPengerjaan() {
        ApiInterface apiInterface = RestProvider.getTeknisiController().create(ApiInterface.class);
        Call<TaskPengerjaanResponse> call = apiInterface.createTaskPengerjaan(
                new RTaskPengerjaan("asdasd"));
        call.enqueue(new Callback<TaskPengerjaanResponse>() {
            @Override
            public void onResponse(Call<TaskPengerjaanResponse> call, Response<TaskPengerjaanResponse> response) {
                Log.d(TAG, "URL: " + response.raw().request().url());
                Log.d(TAG, "API response login. Code: " + response.code());

                *//*if(response.code() == 200 && response.body().getRes_code() == 200){

                }else {

                }*//*
            }

            @Override
            public void onFailure(Call<TaskPengerjaanResponse> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    utils.alertDialogOK(activity, activity.getResources().getString(R.string.conn_time_out));
                } else {
                    utils.alertDialogOK(context, activity.getResources().getString(R.string.problem_occured));
                }
            }
        });
    }*/


    public void proceedCreateTaskReport(
            int idUserLogin, String idTaskPengerjaan,
            int scReport, String noTelpReport, String userReport, String namaReport, String alamatReport,
            String OdpReport, String PcReport, String BrCodeReport, String PortReport, String SnOutReport,
            String MacstbReport, String snOntReport, String KoReport, String KpReport, String ivrReport,
            String ketReport, String tglReport, boolean isKendala) {
        ApiInterface apiInterface = RestProvider.getTeknisiController().create(ApiInterface.class);
        Call<CreateReportTeknisiResponse> call = apiInterface.createReportTeknisi(
                new CreateReportTeknisi(idTaskPengerjaan, scReport,
                        noTelpReport, userReport, namaReport, alamatReport,
                        OdpReport, PcReport, BrCodeReport, PortReport, SnOutReport,
                        MacstbReport, snOntReport, KoReport, KpReport, ivrReport,
                        ketReport, tglReport, isKendala));
        call.enqueue(new Callback<CreateReportTeknisiResponse>() {
            @Override
            public void onResponse(Call<CreateReportTeknisiResponse> call, Response<CreateReportTeknisiResponse> response) {
                Log.d(TAG, "URL: " + response.raw().request().url());
                Log.d(TAG, "API response login. Code: " + response.code());

                if (response.code() == 200 && response.body().getRes_code() == 200) {

                } else {

                }
            }

            @Override
            public void onFailure(Call<CreateReportTeknisiResponse> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    utils.alertDialogOK(activity, activity.getResources().getString(R.string.conn_time_out));
                } else {
                    utils.alertDialogOK(context, activity.getResources().getString(R.string.problem_occured));
                }
            }
        });
    }


    /*public void getTaskPengerjaan(int idTeknisi, final List<RTaskPengerjaan> pengerjaanList) {
        ApiInterface apiInterface = RestProvider.getTeknisiController().create(ApiInterface.class);
        Call<TaskPengerjaanResponse> call = apiInterface.callPengerjaanByIdTeknisi(idTeknisi);
        *//*final List<RTaskPengerjaan> pengerjaanList = new ArrayList<>();*//*
        call.enqueue(new Callback<TaskPengerjaanResponse>() {
            @Override
            public void onResponse(Call<TaskPengerjaanResponse> call, Response<TaskPengerjaanResponse> response) {
                Log.d(TAG, "URL: " + response.raw().request().url());
                Log.d(TAG, "API response login. Code: " + response.code());

                try {
                    if (response.code() == 200 && response.body().getRes_code() == 200) {
                        int resultSize = response.body().getStatus().getResults().size();
                        for (int i = 0; i < resultSize; i++) {
                            String statPengerjaan = response.body().getStatus().getResults().get(i).getStatus_pengerjaan();
                            int idTaskPengerjaan = response.body().getStatus().getResults().get(i).getId_task_pengerjaan();
                            String nomorTeknisi = response.body().getStatus().getResults().get(i).getNomor_teknisi();
                            String longLatPengerjaan = response.body().getStatus().getResults().get(i).getLonglat_monitoring();
                            String tglPengerjaan = response.body().getStatus().getResults().get(i).getTanggal_pengerjaan();
                            String namaLeader = response.body().getStatus().getResults().get(i).getNama_leader();
                            String ketMonitoring = response.body().getStatus().getResults().get(i).getKet_monitoring();
                            String namaMonitoring = response.body().getStatus().getResults().get(i).getNama_monitoring();
                            String namaTeknisi = response.body().getStatus().getResults().get(i).getNama_teknisi();
                            String nomorLeader = response.body().getStatus().getResults().get(i).getNomor_leader();
                            String namaRegional = response.body().getStatus().getResults().get(i).getNama_regional();
                            String tglMonitoring = response.body().getStatus().getResults().get(i).getTanggal_monitoring();
                            int idRegional = response.body().getStatus().getResults().get(i).getId_regional();

                            pengerjaanList.add(new RTaskPengerjaan(statPengerjaan, idTaskPengerjaan, nomorTeknisi,
                                    longLatPengerjaan, tglPengerjaan, namaLeader, ketMonitoring, namaMonitoring, namaTeknisi, nomorLeader, namaRegional,
                                    tglMonitoring, idRegional));

                            Log.d(TAG, "Task Pengerjaan " + i + ": " + pengerjaanList.get(i).getId_task_pengerjaan());
                        }
                    } else {

                    }
                } catch (Exception e) {
                    Log.e(TAG, "Get Task Pengerjaan exception: " + e);
                }
            }

            @Override
            public void onFailure(Call<TaskPengerjaanResponse> call, Throwable t) {

            }
        });

    }*/
}
