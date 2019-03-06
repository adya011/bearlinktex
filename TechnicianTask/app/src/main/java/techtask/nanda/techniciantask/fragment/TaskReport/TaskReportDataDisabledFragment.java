package techtask.nanda.techniciantask.fragment.TaskReport;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;

import java.net.SocketTimeoutException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techtask.nanda.techniciantask.R;
import techtask.nanda.techniciantask.TaskReportActivity;
import techtask.nanda.techniciantask.api.ApiInterface;
import techtask.nanda.techniciantask.api.RestProvider;
import techtask.nanda.techniciantask.base.BaseFragment;
import techtask.nanda.techniciantask.config.Constants;
import techtask.nanda.techniciantask.model.LongLat;
import techtask.nanda.techniciantask.model._realm.RTaskReport;
import techtask.nanda.techniciantask.model.report.CreateReportTeknisiResponse;
import techtask.nanda.techniciantask.utils.LocationHelper;

/**
 * A simple {@link Fragment} subclass.
 */
// Fragment Task Report data
public class TaskReportDataDisabledFragment extends BaseFragment {
    @BindView(R.id.et_sc_report)
    EditText etScReport;

    @BindView(R.id.et_no_telp)
    EditText etNoTelp;

    @BindView(R.id.et_user_report)
    EditText etUserReport;

    @BindView(R.id.et_nama_report)
    EditText etNamaReport;

    @BindView(R.id.et_alamat)
    EditText etAlamatReport;

    @BindView(R.id.et_odp_report)
    EditText etOdpReport;

    @BindView(R.id.et_pc_report)
    EditText etPcReport;

    @BindView(R.id.et_br_code_report)
    EditText etBrCodeReport;

    @BindView(R.id.et_port_report)
    EditText etPortReport;

    @BindView(R.id.et_sn_out_report)
    EditText etSnOutReport;

    @BindView(R.id.et_macstb_report)
    EditText etMacstbReport;

    @BindView(R.id.et_sn_ont_report)
    EditText etSnOntReport;

    @BindView(R.id.et_ko_report)
    EditText etKoReport;

    @BindView(R.id.et_kp_report)
    EditText etKpReport;

    @BindView(R.id.et_ivr_report)
    EditText etIvrReport;

    @BindView(R.id.et_ket_report)
    EditText etKetReport;

    /*@BindView(R.id.dt_tgl_report)
    EditText dtTglReport;*/

    @BindView(R.id.btn_submit_report_data)
    Button btnSubmitReportData;

    @BindView(R.id.btn_image_report)
    Button btnPhotoReport;

    @BindView(R.id.spinner_kendala)
    Spinner spKendala;

    @BindView(R.id.cb_kendala)
    Switch cbKendala;

    @BindView(R.id.btn_date_picker)
    ImageButton btnDatePicker;

    @BindView(R.id.et_date)
    TextInputEditText etDate;

    @BindView(R.id.lay_hiddenable)
    LinearLayout hiddenable;

    @BindView(R.id.btn_ko_report)
    ImageButton btnKoReport;

    @BindView(R.id.btn_kp_report)
    ImageButton btnKpReport;

    @BindView(R.id.btn_nama_report)
    ImageButton btnNamaReport;

    String TAG = "myd_task_det_data";
    String displayFormat = "dd MMM yyyy";

    int idTaskReport;
    String id_task;
    String kendalaStr;
    Date tglReportDate;

    public TaskReportDataDisabledFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_detail_data, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((TaskReportActivity) getActivity()).setToolbarTitle("Task Report");
        init();
    }

    // Inisialisasi awal logic
    private void init() {
        id_task = ((TaskReportActivity) getActivity()).id_task;

        Log.d(TAG, "== Task Report Data ==");
        Log.d(TAG, "ID Report : " + idTaskReport);
        Log.d(TAG, "ID Pengerjaan : " + id_task);

        utils.hideKeyboard(getActivity());

        //reqReportData();
        getTaskReportTeknisi(id_task);

        etScReport.setEnabled(false);
        etNoTelp.setEnabled(false);
        etUserReport.setEnabled(false);
        etNamaReport.setEnabled(false);
        etAlamatReport.setEnabled(false);
        etOdpReport.setEnabled(false);
        etPcReport.setEnabled(false);
        etBrCodeReport.setEnabled(false);
        etPortReport.setEnabled(false);
        etSnOutReport.setEnabled(false);
        etMacstbReport.setEnabled(false);
        etSnOntReport.setEnabled(false);
        etKoReport.setEnabled(false);
        etKpReport.setEnabled(false);
        etIvrReport.setEnabled(false);
        etKetReport.setEnabled(false);
        btnKoReport.setEnabled(false);
        btnKpReport.setEnabled(false);
        cbKendala.setEnabled(false);
        btnDatePicker.setEnabled(false);
        etDate.setEnabled(false);
        btnSubmitReportData.setEnabled(false);
    }

    // Mengambil data report dari database lokal
    private void reqReportData() {
        id_task = ((TaskReportActivity) getActivity()).id_task;
        RealmResults<RTaskReport> results = ((TaskReportActivity) getActivity()).realm
                .where(RTaskReport.class)
                .equalTo("id_task", id_task).findAll();

        Log.d(TAG, "** Select Task Report by Id: " + id_task + " **");

        for (RTaskReport taskReport : results) {
            Log.d(TAG, "Nama Report: " + taskReport.getNama_report());
            etScReport.setText(taskReport.getSc_report());
            etNoTelp.setText(taskReport.getNo_telpn_report());
            etUserReport.setText(taskReport.getUser_report());
            etNamaReport.setText(taskReport.getNama_report());
            etAlamatReport.setText(taskReport.getAlamat_report());
            etOdpReport.setText(taskReport.getOdp_report());
            etPcReport.setText(taskReport.getPc_report());
            etBrCodeReport.setText(taskReport.getBr_code_report());
            etPortReport.setText(taskReport.getPort_report());
            etSnOutReport.setText(taskReport.getSn_ont_report());
            etMacstbReport.setText(taskReport.getMacstb_report());
            etSnOntReport.setText(taskReport.getSn_ont_report());
            etKoReport.setText(taskReport.getKo_report());
            etKpReport.setText(taskReport.getKp_report());
            etIvrReport.setText(taskReport.getIvr_report());
            etKetReport.setText(taskReport.getKet_report());
            cbKendala.setChecked(taskReport.isKendala());
            try {
                tglReportDate = stringToDate(taskReport.getTanggal_report());
                etDate.setText(dateToStringFormat(tglReportDate));
                Log.d(TAG, "tgl report str to date: " + tglReportDate);
                Log.d(TAG, "tgl report format: " + dateToStringFormat(tglReportDate));

            } catch (ParseException e) {
                Log.d(TAG, "tgl report format ERROR: " + e);
                e.printStackTrace();
            }

        }

        if (cbKendala.isChecked()) {
            hiddenable.setVisibility(View.GONE);
            spKendala.setVisibility(View.VISIBLE);

        } else {
            hiddenable.setVisibility(View.VISIBLE);
            spKendala.setVisibility(View.GONE);
        }
    }


    public void getTaskReportTeknisi(final String id_task) {
        ApiInterface apiInterface = RestProvider.getTeknisiController().create(ApiInterface.class);
        Call<CreateReportTeknisiResponse> call = apiInterface.callReportTeknisi(id_task);
        call.enqueue(new Callback<CreateReportTeknisiResponse>() {
            @Override
            public void onResponse(Call<CreateReportTeknisiResponse> call, Response<CreateReportTeknisiResponse> response) {
                Log.d(TAG, "URL: " + response.raw().request().url());
                Log.d(TAG, "API response getTaskReportTeknisi. Code: " + response.code());
                if (response.code() == 200 && response.body().getRes_code() == 200) {
                    int sc_report = response.body().getStatus().getResult().get(0).getSc_report();
                    String sc_report_str = String.valueOf(sc_report);
                    Log.d(TAG, "sc report: " + sc_report);
                    String no_telpn_report = response.body().getStatus().getResult().get(0).getNo_telpn_report();
                    String user_report = response.body().getStatus().getResult().get(0).getUser_report();
                    Log.d(TAG, "user report: " + user_report);
                    String nama_report = response.body().getStatus().getResult().get(0).getNama_report();
                    String alamat_report = response.body().getStatus().getResult().get(0).getAlamat_report();
                    String odp_report = response.body().getStatus().getResult().get(0).getOdp_report();
                    String pc_report = response.body().getStatus().getResult().get(0).getPc_report();
                    String br_code_report = response.body().getStatus().getResult().get(0).getBr_code_report();
                    String port_report = response.body().getStatus().getResult().get(0).getPort_report();
                    String sn_out_report = response.body().getStatus().getResult().get(0).getSn_out_report();
                    String macstb_report = response.body().getStatus().getResult().get(0).getMacstb_report();
                    String sn_ont_report = response.body().getStatus().getResult().get(0).getSn_ont_report();
                    String ko_report = response.body().getStatus().getResult().get(0).getKo_report();
                    String kp_report = response.body().getStatus().getResult().get(0).getKp_report();
                    String ivr_report = response.body().getStatus().getResult().get(0).getIvr_report();
                    String ket_report = response.body().getStatus().getResult().get(0).getKet_report();
                    String tanggal_report = response.body().getStatus().getResult().get(0).getTanggal_report();
                    boolean kendala_stat = response.body().getStatus().getResult().get(0).isStatus_kendala();

                    etScReport.setText(sc_report_str);
                    etNoTelp.setText(no_telpn_report);
                    etUserReport.setText(user_report);
                    etNamaReport.setText(nama_report);
                    etAlamatReport.setText(alamat_report);
                    etOdpReport.setText(odp_report);
                    etPcReport.setText(pc_report);
                    etBrCodeReport.setText(br_code_report);
                    etPortReport.setText(port_report);
                    etSnOutReport.setText(sn_out_report);
                    etMacstbReport.setText(macstb_report);
                    etSnOntReport.setText(sn_ont_report);
                    etKoReport.setText(ko_report);
                    etKpReport.setText(kp_report);
                    etIvrReport.setText(ivr_report);
                    etKetReport.setText(ket_report);
                    Log.d(TAG, "tanggal report length: " + tanggal_report.length());

                    try {
                        tglReportDate = stringToDate(tanggal_report);
                        etDate.setText(dateToStringFormat(tglReportDate));
                        Log.d(TAG, "tgl report str to date: " + tglReportDate);
                        Log.d(TAG, "tgl report format: " + dateToStringFormat(tglReportDate));

                    } catch (ParseException e) {
                        Log.d(TAG, "tgl report format ERROR: " + e);
                        e.printStackTrace();
                    }

                    cbKendala.setChecked(kendala_stat);

                }
            }

            @Override
            public void onFailure(Call<CreateReportTeknisiResponse> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    utils.alertDialogOK(getContext(), getResources().getString(R.string.conn_time_out));
                } else {
                    utils.alertDialogOK(getContext(), getResources().getString(R.string.problem_occured));
                }
            }
        });
    }


    // Ambil dari database (string) ke variable lokal (date)
    private Date stringToDate(String dateString) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedDate = dateFormat.parse(dateString);

        return convertedDate;
    }

    // Dari variable lokal (date) ditampilkan ke UI (string)
    private String dateToStringFormat(Date date) {
        String newFormat = "yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT'Z yyyy");
        dateFormat.applyPattern(newFormat);
        String convertedDefaultDate = dateFormat.format(date);

        return convertedDefaultDate;
    }
}
