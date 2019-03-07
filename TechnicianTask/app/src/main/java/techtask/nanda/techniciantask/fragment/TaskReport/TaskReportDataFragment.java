package techtask.nanda.techniciantask.fragment.TaskReport;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import android.widget.Toast;

import java.net.SocketTimeoutException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techtask.nanda.techniciantask.DashboardActivity;
import techtask.nanda.techniciantask.R;
import techtask.nanda.techniciantask.TaskReportActivity;
import techtask.nanda.techniciantask.api.ApiInterface;
import techtask.nanda.techniciantask.api.RestProvider;
import techtask.nanda.techniciantask.base.BaseFragment;
import techtask.nanda.techniciantask.camera.CameraActivity;
import techtask.nanda.techniciantask.config.Constants;
import techtask.nanda.techniciantask.fragment.TaskPengerjaan.TaskPengerjaanPendingFragment;
import techtask.nanda.techniciantask.model.LongLat;
import techtask.nanda.techniciantask.model._realm.RTaskReport;
import techtask.nanda.techniciantask.model.registerteknisi.RegisterPerson;
import techtask.nanda.techniciantask.model.registerteknisi.RegisterTeknisiResponse;
import techtask.nanda.techniciantask.model.report.CreateReportTeknisi;
import techtask.nanda.techniciantask.model.report.CreateReportTeknisiResponse;
import techtask.nanda.techniciantask.model.report.ReportTeknisi;
import techtask.nanda.techniciantask.model.report.ReportTeknisiResponse;
import techtask.nanda.techniciantask.utils.LocationHelper;

/**
 * A simple {@link Fragment} subclass.
 */
// Fragment Task Report data
public class TaskReportDataFragment extends BaseFragment {
    Activity activity;
    Context context;

    @BindView(R.id.etlay_sc_report)
    TextInputLayout etLayScReport;

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

    @BindView(R.id.lay_hiddenable_2)
    LinearLayout hiddenable2;

    @BindView(R.id.btn_ko_report)
    ImageButton btnKoReport;

    @BindView(R.id.btn_kp_report)
    ImageButton btnKpReport;

    @BindView(R.id.btn_nama_report)
    ImageButton btnNamaReport;

    String TAG = "myd_task_det_data";
    String displayFormat = "yyyy-MM-dd";

    String id_task;
    String kendalaStr;
    Date tglReportDate;

    List<CreateReportTeknisi> reportTeknisiList = new ArrayList<>();
    List<String> reportTeknisiListStr = new ArrayList<>();

    public TaskReportDataFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_detail_data, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((TaskReportActivity) getActivity()).setToolbarTitle("Task Report");
        ((TaskReportActivity) getActivity()).toolbarMoveRight();
        setHasOptionsMenu(true);
        init();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_report_data, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_copy:
                String noTelp = (etNoTelp.getText().toString().length() > 0) ? etNoTelp.getText().toString() : " - ";
                String userReport = (etUserReport.getText().toString().length() > 0) ? etUserReport.getText().toString() : " - ";
                String namaReport = (etNamaReport.getText().toString().length() > 0) ? etNamaReport.getText().toString() : " - ";
                String alamatReport = (etAlamatReport.getText().toString().length() > 0) ? etAlamatReport.getText().toString() : " - ";
                String odpReport = (etOdpReport.getText().toString().length() > 0) ? etOdpReport.getText().toString() : " - ";
                String pcReport = (etPcReport.getText().toString().length() > 0) ? etPcReport.getText().toString() : " - ";
                String brCodeReport = (etBrCodeReport.getText().toString().length() > 0) ? etBrCodeReport.getText().toString() : " - ";
                String portReport = (etPortReport.getText().toString().length() > 0) ? etPortReport.getText().toString() : " - ";
                String snOutReport = (etSnOutReport.getText().toString().length() > 0) ? etSnOutReport.getText().toString() : " - ";
                String macstbReport = (etMacstbReport.getText().toString().length() > 0) ? etMacstbReport.getText().toString() : " - ";
                String snOntReport = (etSnOntReport.getText().toString().length() > 0) ? etSnOntReport.getText().toString() : " - ";
                String koReport = (etKoReport.getText().toString().length() > 0) ? etKoReport.getText().toString() : " - ";
                String kpReport = (etKpReport.getText().toString().length() > 0) ? etKpReport.getText().toString() : " - ";
                String ivrReport = (etIvrReport.getText().toString().length() > 0) ? etIvrReport.getText().toString() : " - ";
                String ketReport = (etKetReport.getText().toString().length() > 0) ? etKetReport.getText().toString() : " - ";
                String dateReport = (etDate.getText().toString().length() > 0) ? etDate.getText().toString() : " - ";

                String textToCopy =
                        "No Telp Report: " + noTelp + "\n" +
                                "User Report: " + userReport + "\n" +
                                "Nama Report: " + namaReport + "\n" +
                                "Alamat Report: " + alamatReport + "\n" +
                                "ODP Report: " + odpReport + "\n" +
                                "PC Report: " + pcReport + "\n" +
                                "BR Code Report: " + brCodeReport + "\n" +
                                "Port Report: " + portReport + "\n" +
                                "Sn Out Report: " + snOutReport + "\n" +
                                "Mactstb Report:: " + macstbReport + "\n" +
                                "Sn Ont Report: " + snOntReport + "\n" +
                                "Ko Report: " + koReport + "\n" +
                                "Kp Report: " + kpReport + "\n" +
                                "IVR Report: " + ivrReport + "\n" +
                                "Ket Report: " + ketReport + "\n" +
                                "Date Report: " + dateReport;

                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("copy_report", textToCopy);
                clipboard.setPrimaryClip(clip);

                Toast.makeText(getActivity(), getResources().getString(R.string.report_copied), Toast.LENGTH_SHORT).show();

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    // Inisialisasi awal logic
    private void init() {
        id_task = ((TaskReportActivity) getActivity()).id_task;

        Log.d(TAG, "== Task Report Data ==");
        Log.d(TAG, "ID Task Report : " + id_task);
        Log.d(TAG, "ID Pengerjaan : " + id_task);

        utils.hideKeyboard(getActivity());

        getTaskReportTeknisi(id_task);

        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    showDatePicker();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        btnSubmitReportData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int scReport;
                if (etScReport.getText().toString().length() > 0) {
                    scReport = Integer.parseInt(etScReport.getText().toString());
                } else {
                    scReport = 0;
                }
                String noTelp = etNoTelp.getText().toString();
                String userReport = etUserReport.getText().toString();
                String namaReport = etNamaReport.getText().toString();
                String alamatReport = etAlamatReport.getText().toString();
                String odpReport = etOdpReport.getText().toString();
                String pcReport = etPcReport.getText().toString();
                String brCodeReport = etBrCodeReport.getText().toString();
                String portReport = etPortReport.getText().toString();
                String snOutReport = etSnOutReport.getText().toString();
                String macstbReport = etMacstbReport.getText().toString();
                String snOntReport = etSnOntReport.getText().toString();
                String koReport = etKoReport.getText().toString();
                String kpReport = etKpReport.getText().toString();
                String ivrReport = etIvrReport.getText().toString();
                String ketReport = etKetReport.getText().toString();
                String tglReport;

                if (etDate.getText().toString().length() > 0) {
                    tglReport = etDate.getText().toString();
                } else {
                    tglReport = dateToStringFormat(getDefaultDate());
                }
                //String tglReport = dateToStringRaw(tglReportDate);
                boolean isKendala = cbKendala.isChecked();

                /*etLayScReport.setErrorEnabled(false);*/

                //if (scReport != 0) {
                proceedCreateTaskReport(id_task, scReport,
                        noTelp, userReport, namaReport, alamatReport, odpReport,
                        pcReport, brCodeReport, portReport, snOutReport, macstbReport,
                        snOntReport, koReport, kpReport, ivrReport, ketReport, tglReport, isKendala);
                //} else {
                    /*etLayScReport.setError("SC Report tidak boleh kosong");
                    etLayScReport.setErrorEnabled(true);*/
                //}

            }
        });

        kendalaAdapter();

        btnPhotoReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame_content, new TaskReportImageFragment());
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        btnKoReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationHelper loc = new LocationHelper(getActivity());
                LongLat longLat = loc.getLongLat();
                double longt = longLat.getLongitude();
                double latt = longLat.getLatitude();
                etKoReport.setText(longt + ", " + latt);
            }
        });

        btnKpReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationHelper loc = new LocationHelper(getActivity());
                LongLat longLat = loc.getLongLat();
                double longt = longLat.getLongitude();
                double latt = longLat.getLatitude();
                etKpReport.setText(longt + ", " + latt);
            }
        });

        btnNamaReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame_content, TaskReportDataWODetailFragment.newInstance(1));
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        if (cbKendala.isChecked()) {
            spKendala.setVisibility(View.VISIBLE);
            hiddenable.setVisibility(View.GONE);
        } else {
            hiddenable.setVisibility(View.VISIBLE);
            spKendala.setVisibility(View.GONE);
        }

        cbKendala.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    hiddenable.setVisibility(View.GONE);
                    spKendala.setVisibility(View.VISIBLE);

                } else {
                    hiddenable.setVisibility(View.VISIBLE);
                    spKendala.setVisibility(View.GONE);
                }
            }
        });
    }

    // List drop down kendala
    private void kendalaAdapter() {
        ArrayAdapter<String> spinAdapter = new ArrayAdapter<String>(getContext(),
                R.layout.spinner_provinsi, Constants.kendala);
        spKendala.setAdapter(spinAdapter);
        spKendala.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                kendalaStr = spKendala.getAdapter().getItem(position).toString();
                if (position == 8 && cbKendala.isChecked()) {
                    hiddenable2.setVisibility(View.VISIBLE);

                } else {
                    hiddenable2.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    // Menampilkan dialog date picker
    private void showDatePicker() throws ParseException {
        Log.d(TAG, "show date picker");

        String getDateStr = "2019-01-01";
        SimpleDateFormat dateFormat = new SimpleDateFormat(displayFormat);
        Date getDate = dateFormat.parse(getDateStr);
        Log.d(TAG, "get Date: " + getDate);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getDate);

        DatePickerDialog datePicker = new DatePickerDialog(getContext(), AlertDialog.THEME_HOLO_LIGHT, datePickerListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePicker.setCancelable(false);
        datePicker.setTitle("");
        datePicker.show();
    }

    // Menampilkan date dari dialog date picker
    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            month = month + 1;

            //Convert integer into 2 digit
            DecimalFormat decimalFormat = new DecimalFormat("00");
            String dayDual = decimalFormat.format(dayOfMonth);
            String monthDual = decimalFormat.format(month);
            String yearDual = decimalFormat.format(year);

            String yyyy = String.valueOf(yearDual);
            String mm = String.valueOf(monthDual);
            String dd = String.valueOf(dayDual);

            String pickedDateStr = yyyy + "-" + mm + "-" + dd;
            Log.d(TAG, "picked date string: " + pickedDateStr);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            String convertedDate;

            try {
                etDate.setText(pickedDateStr);
                Date date = dateFormat.parse(pickedDateStr);
                tglReportDate = date;

            } catch (ParseException e) {
                e.printStackTrace();
            }

            Log.d(TAG, "Date Picker: " + dayOfMonth + "-" + month + "-" + year);
        }
    };

    // Tanggal default jika date belum diisi sebelumnya
    private Date getDefaultDate() {
        Calendar calendar = Calendar.getInstance();
        Date defaultDate = calendar.getTime();
        return defaultDate;
    }

    // Dari variable lokal (date) ditampilkan ke UI (string)
    private String dateToStringFormat(Date date) {
        String newFormat = "yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT'Z yyyy");
        dateFormat.applyPattern(newFormat);
        String convertedDefaultDate = dateFormat.format(date);

        return convertedDefaultDate;
    }

    // Dari variable lokal (date) disimpan ke database(string)
    private String dateToStringRaw(Date date) {
        return String.valueOf(date);
    }

    // Ambil dari database (string) ke variable lokal (date)
    private Date stringToDate(String dateString) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedDate = dateFormat.parse(dateString);

        return convertedDate;
    }

    public void proceedCreateTaskReport(
            String id_task,
            int scReport, String noTelpReport, String userReport, String namaReport, String alamatReport,
            String OdpReport, String PcReport, String BrCodeReport, String PortReport, String SnOutReport,
            String MacstbReport, String snOntReport, String KoReport, String KpReport, String ivrReport,
            String ketReport, String tglReport, boolean statKendala) {
        ApiInterface apiInterface = RestProvider.getTeknisiController().create(ApiInterface.class);
        Call<CreateReportTeknisiResponse> call = apiInterface.createReportTeknisi(new CreateReportTeknisi(id_task, scReport, noTelpReport, userReport, namaReport, alamatReport, OdpReport, PcReport, BrCodeReport, PortReport, SnOutReport, MacstbReport, snOntReport, KoReport, KpReport, ivrReport, ketReport, tglReport, statKendala));

        Log.d(TAG, "== Create Task Report ==");
        Log.d(TAG, "BODY: " + "id_task: " + id_task
                + ", sc_report: " + scReport + ", no_telpn_report: " + noTelpReport + ", user_report: " + userReport + ", nama_report: " + namaReport + ", alamat_report: " + alamatReport
                + ", odp_report: " + OdpReport + ", pc_report: " + PcReport + ", br_code_report: " + BrCodeReport + ", port_report: " + PortReport + ", sn_out_report: " + SnOutReport
                + ", macstb_report: " + MacstbReport + ", sn_ont_report: " + snOntReport + ", ko_report: " + KoReport + ", kp_report: " + KpReport + ", ivr_report: " + ivrReport
                + ", ket_report: " + ketReport + ", tanggal_report: " + tglReport + "stat_kendala: " + statKendala);
        call.enqueue(new Callback<CreateReportTeknisiResponse>() {
            @Override
            public void onResponse(Call<CreateReportTeknisiResponse> call, Response<CreateReportTeknisiResponse> response) {
                Log.d(TAG, "URL: " + response.raw().request().url());
                Log.d(TAG, "API response proceedCreateTaskReport. Code: " + response.code());
                try {
                    if (response.code() == 200 && response.body().getRes_code() == 200) {
                        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
                        builder.setMessage("Data Report Di Simpan");

                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getActivity().finish();
                            }
                        });

                        android.support.v7.app.AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                    } else {
                        String msg = response.body().getStatus().getReq_msg();
                        utils.alertDialogOK(getContext(), msg);
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<CreateReportTeknisiResponse> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    utils.alertDialogOK(getContext(), getResources().getString(R.string.conn_time_out));
                } else {

                }
            }
        });
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
                    //int provinsiListSize = response.body().getStatus().getResult().size();
                    //for (int i = 0; i < provinsiListSize; i++) {
                    String id_task = response.body().getStatus().getResult().get(0).getId_task();
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
                    if (tanggal_report.length() > 0) {
                        try {
                            tglReportDate = stringToDate(tanggal_report);
                            etDate.setText(dateToStringFormat(tglReportDate));
                            Log.d(TAG, "tgl report str to date: " + tglReportDate);
                            Log.d(TAG, "tgl report format: " + dateToStringFormat(tglReportDate));

                        } catch (ParseException e) {
                            Log.d(TAG, "tgl report format ERROR: " + e);
                            e.printStackTrace();
                        }

                    } else {
                        tglReportDate = getDefaultDate();
                        etDate.setText(dateToStringFormat(getDefaultDate()));
                        Log.d(TAG, "tgl report DEFAULT: " + getDefaultDate());
                    }
                    cbKendala.setChecked(kendala_stat);

                } else {
                    etScReport.setText("0");
                    etDate.setText(dateToStringFormat(getDefaultDate()));
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

    /*private void penemempatanAreaAdapter() {
        final ArrayAdapter<CreateReportTeknisi> adapter = new ArrayAdapter<CreateReportTeknisi>(getContext(), R.layout.fragment_task_on_progress, reportTeknisiList);
        for (CreateReportTeknisi taskReport : reportTeknisiList) {
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
            Log.d(TAG, "tgl report: " + taskReport.getTanggal_report());
            if (taskReport.getTanggal_report() != null) {
                try {
                    tglReportDate = stringToDate(taskReport.getTanggal_report());
                    etDate.setText(dateToStringFormat(tglReportDate));
                    Log.d(TAG, "tgl report str to date: " + tglReportDate);
                    Log.d(TAG, "tgl report format: " + dateToStringFormat(tglReportDate));

                } catch (ParseException e) {
                    Log.d(TAG, "tgl report format ERROR: " + e);
                    e.printStackTrace();
                }

            } else {
                tglReportDate = getDefaultDate();
                etDate.setText(dateToStringFormat(getDefaultDate()));
                Log.d(TAG, "tgl report DEFAULT: " + getDefaultDate());
            }
        }
    }*/
}
