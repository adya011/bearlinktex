package techtask.nanda.techniciantask.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techtask.nanda.techniciantask.R;
import techtask.nanda.techniciantask.api.ApiInterface;
import techtask.nanda.techniciantask.api.RestProvider;
import techtask.nanda.techniciantask.base.BaseFragment;
import techtask.nanda.techniciantask.model.Kabupaten;
import techtask.nanda.techniciantask.model.KabupatenResponse;
import techtask.nanda.techniciantask.model.Provinsi;
import techtask.nanda.techniciantask.model.ProvinsiResponse;
import techtask.nanda.techniciantask.model.registerteknisi.RegisterPerson;
import techtask.nanda.techniciantask.model.registerteknisi.RegisterTeknisiResponse;
import techtask.nanda.techniciantask.model.TeamLeader;
import techtask.nanda.techniciantask.model.TeamLeaderResponse;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends BaseFragment {
    @BindView(R.id.et_nama_person)
    EditText etNamaTeknisi;

    @BindView(R.id.et_alamat_person)
    EditText etAlamatTeknisi;

    @BindView(R.id.sp_provinsi)
    Spinner spProvinsi;

    @BindView(R.id.sp_kabupaten)
    Spinner spKabupaten;

    @BindView(R.id.et_tanggal_lahir)
    EditText etTanggalLahirPerson;

    @BindView(R.id.et_telephone)
    EditText etTelephonePerson;

    @BindView(R.id.sp_penempatan)
    Spinner spPenempatanProvinsi;

    @BindView(R.id.sp_penempatan_area)
    Spinner spPenempatanArea;

    @BindView(R.id.btn_register)
    Button btnRegister;

    private static final String TAG = "myd_register";
    List<Provinsi> provinsiList = new ArrayList<>();
    List<String> provinsiListStr = new ArrayList<>();
    List<Kabupaten> kabupatensList = new ArrayList<>();
    List<String> kabupatenListStr = new ArrayList<>();
    List<Provinsi> penempatanProvinsiList = new ArrayList<>();
    List<String> penempatanProvinsiListStr = new ArrayList<>();
    List<Kabupaten> penempatanAreaList = new ArrayList<>();
    List<String> penempatanAreaListStr = new ArrayList<>();
    List<TeamLeader> spPenempatan = new ArrayList<>();
    List<String> spPenempatanStr = new ArrayList<>();

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //((RegisterActivity) getActivity()).setToolbarTitle("Registrasi");
        init();
    }

    // Inisialisasi awal logic
    private void init() {
        proceedGetProvinsi();
        proceedGetPenempatanProvinsi();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namaPerson = etNamaTeknisi.getText().toString();
                String alamatPerson = etAlamatTeknisi.getText().toString();
                String provinsiPerson;
                try {
                    provinsiPerson = spProvinsi.getSelectedItem().toString();
                }catch (Exception e){
                    provinsiPerson = null;
                }

                String kabupatenPerson;
                try {
                    kabupatenPerson = spKabupaten.getSelectedItem().toString();
                }catch (Exception e){
                    kabupatenPerson = null;
                }
                String ttlPerson = etTanggalLahirPerson.getText().toString();
                String telephonePerson = etTelephonePerson.getText().toString();

                String penempatanPerson;
                try {
                    penempatanPerson = spPenempatanProvinsi.getSelectedItem().toString();
                }catch (Exception e){
                    penempatanPerson = null;
                }
                proceedRegisterPerson(namaPerson, alamatPerson, "34", "3404", "1995-11-11", telephonePerson, null, null, "6", null);
            }
        });
    }

    private void proceedGetProvinsi() {
        ApiInterface apiInterface = RestProvider.getTeknisiController().create(ApiInterface.class);
        Call<ProvinsiResponse> call = apiInterface.callProvinsi();
        call.enqueue(new Callback<ProvinsiResponse>() {
            @Override
            public void onResponse(Call<ProvinsiResponse> call, Response<ProvinsiResponse> response) {
                Log.d(TAG, "URL: " + response.raw().request().url());
                Log.d(TAG, "API response login. Code: " + response.code());

                try {
                    if (response.code() == 200 && response.body().getRes_code() == 200) {
                        int provinsiListSize = response.body().getStatus().getResult().size();
                        for (int i = 0; i < provinsiListSize; i++) {
                            String namaProv = response.body().getStatus().getResult().get(i).getName();
                            String idProv = response.body().getStatus().getResult().get(i).getId();
                            provinsiList.add(new Provinsi(namaProv, idProv));
                            provinsiListStr.add(provinsiList.get(i).getName());
                        }
                        provinsiAdapter();
                    } else {

                    }
                } catch (Exception e) {
                    Log.d(TAG, "Exception: " + e);
                }
            }

            @Override
            public void onFailure(Call<ProvinsiResponse> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    utils.alertDialogOK(getContext(), getResources().getString(R.string.conn_time_out));
                } else {
                    //utils.alertDialogOK(getContext(), getResources().getString(R.string.problem_occured));
                    Log.d(TAG, "Throw: " + t);
                }
            }
        });
    }

    private void provinsiAdapter() {
        final ArrayAdapter<Provinsi> provinsi = new ArrayAdapter<>(getContext(),  R.layout.spinner_provinsi, provinsiList);
        final ArrayAdapter<String> kabupaten = new ArrayAdapter<>(getContext(),  R.layout.spinner_provinsi, provinsiListStr);
        spProvinsi.setAdapter(kabupaten);
        spProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                kabupatensList.clear();
                kabupatenListStr.clear();
                String prov = spProvinsi.getAdapter().getItem(i).toString();
                proceedGetKabupaten(provinsi.getItem(i).getId().toString());
                Log.d(TAG, "Selected provinsi: " + prov);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void proceedGetKabupaten(String id_provinsi) {
        ApiInterface apiInterface = RestProvider.getTeknisiController().create(ApiInterface.class);
        Call<KabupatenResponse> call = apiInterface.callKabupatenByProvinsi(id_provinsi);
        call.enqueue(new Callback<KabupatenResponse>() {
            @Override
            public void onResponse(Call<KabupatenResponse> call, Response<KabupatenResponse> response) {
                Log.d(TAG, "URL: " + response.raw().request().url());
                Log.d(TAG, "API response login. Code: " + response.code());

                try {
                    if (response.code() == 200 && response.body().getRes_code() == 200) {
                        int provinsiListSize = response.body().getStatus().getResult().size();
                        for (int i = 0; i < provinsiListSize; i++) {
                            String namaKab = response.body().getStatus().getResult().get(i).getId();
                            String idKab = response.body().getStatus().getResult().get(i).getName();
                            kabupatensList.add(new Kabupaten(namaKab, idKab));
                            kabupatenListStr.add(kabupatensList.get(i).getName());
                        }
                        kabupatenAdapter();
                    } else {

                    }
                } catch (Exception e) {
                    Log.d(TAG, "Exception: " + e);
                }
            }

            @Override
            public void onFailure(Call<KabupatenResponse> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    utils.alertDialogOK(getContext(), getResources().getString(R.string.conn_time_out));
                } else {
                    spKabupaten.setAdapter(null);
                    //utils.alertDialogOK(getContext(), "Kabupaten null");
                    Log.d(TAG, "Throw: " + t);
                }
            }
        });
    }

    private void kabupatenAdapter() {
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_provinsi, kabupatenListStr);
        spKabupaten.setAdapter(adapter);
        spKabupaten.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String alamatRegional = kabupatensList.get(i).getName();
                spPenempatan.clear();
                spPenempatanStr.clear();
                Log.d(TAG, "Kabupaten item selected kabupaten: " + kabupatenListStr.get(i));
                Log.d(TAG, "Kabupaten item selected alamat Regional: " + alamatRegional);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void proceedGetPenempatanProvinsi() {
        ApiInterface apiInterface = RestProvider.getTeknisiController().create(ApiInterface.class);
        Call<ProvinsiResponse> call = apiInterface.callProvinsi();
        call.enqueue(new Callback<ProvinsiResponse>() {
            @Override
            public void onResponse(Call<ProvinsiResponse> call, Response<ProvinsiResponse> response) {
                Log.d(TAG, "URL: " + response.raw().request().url());
                Log.d(TAG, "API response login. Code: " + response.code());

                try {
                    if (response.code() == 200 && response.body().getRes_code() == 200) {
                        int provinsiListSize = response.body().getStatus().getResult().size();
                        for (int i = 0; i < provinsiListSize; i++) {
                            String namaProv = response.body().getStatus().getResult().get(i).getName();
                            String idProv = response.body().getStatus().getResult().get(i).getId();
                            penempatanProvinsiList.add(new Provinsi(namaProv, idProv));
                            penempatanProvinsiListStr.add(penempatanProvinsiList.get(i).getName());
                        }
                        penempatanProvinsiAdapter();
                    } else {

                    }
                } catch (Exception e) {
                    Log.d(TAG, "Exception: " + e);
                }
            }

            @Override
            public void onFailure(Call<ProvinsiResponse> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    utils.alertDialogOK(getContext(), getResources().getString(R.string.conn_time_out));
                } else {
                    //utils.alertDialogOK(getContext(), getResources().getString(R.string.problem_occured));
                    Log.d(TAG, "Throw: " + t);
                }
            }
        });
    }

    private void penempatanProvinsiAdapter() {
        final ArrayAdapter<Provinsi> penempatanProvinsi = new ArrayAdapter<>(getContext(),  R.layout.spinner_provinsi, penempatanProvinsiList);
        final ArrayAdapter<String> kabupaten = new ArrayAdapter<>(getContext(),  R.layout.spinner_provinsi, penempatanProvinsiListStr);
        spPenempatanProvinsi.setAdapter(kabupaten);
        spPenempatanProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                penempatanAreaList.clear();
                penempatanAreaListStr.clear();
                String prov = spProvinsi.getAdapter().getItem(i).toString();
                proceedGetPenempatanArea(penempatanProvinsi.getItem(i).getId().toString());
                Log.d(TAG, "Selected provinsi: " + prov);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void proceedGetPenempatanArea(String id_provinsi) {
        ApiInterface apiInterface = RestProvider.getTeknisiController().create(ApiInterface.class);
        Call<KabupatenResponse> call = apiInterface.callKabupatenByProvinsi(id_provinsi);
        call.enqueue(new Callback<KabupatenResponse>() {
            @Override
            public void onResponse(Call<KabupatenResponse> call, Response<KabupatenResponse> response) {
                Log.d(TAG, "URL: " + response.raw().request().url());
                Log.d(TAG, "API response login. Code: " + response.code());

                try {
                    if (response.code() == 200 && response.body().getRes_code() == 200) {
                        int provinsiListSize = response.body().getStatus().getResult().size();
                        for (int i = 0; i < provinsiListSize; i++) {
                            String namaKab = response.body().getStatus().getResult().get(i).getId();
                            String idKab = response.body().getStatus().getResult().get(i).getName();
                            penempatanAreaList.add(new Kabupaten(namaKab, idKab));
                            penempatanAreaListStr.add(penempatanAreaList.get(i).getName());
                        }
                        penemempatanAreaAdapter();
                    } else {

                    }
                } catch (Exception e) {
                    Log.d(TAG, "Exception: " + e);
                }
            }

            @Override
            public void onFailure(Call<KabupatenResponse> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    utils.alertDialogOK(getContext(), getResources().getString(R.string.conn_time_out));
                } else {
                    spKabupaten.setAdapter(null);
                    //utils.alertDialogOK(getContext(), "Kabupaten null");
                    Log.d(TAG, "Throw: " + t);
                }
            }
        });
    }

    private void penemempatanAreaAdapter() {
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_provinsi, penempatanAreaListStr);
        spPenempatanArea.setAdapter(adapter);
        spPenempatanArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String alamatRegional = penempatanAreaList.get(i).getName();
                spPenempatan.clear();
                spPenempatanStr.clear();
                Log.d(TAG, "Area : " + penempatanAreaListStr.get(i));
                Log.d(TAG, "Area : " + alamatRegional);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void proceedRegisterPerson(String namaPerson, String alamatPerson, String provinsiPerson, String kabupatenPerson, String ttlPerson, String telpnPerson, String fotoPerson, String bioPerson, String levelPerson, String filePerson) {
        ApiInterface apiInterface = RestProvider.getTeknisiController().create(ApiInterface.class);
        Call<RegisterTeknisiResponse> call = apiInterface.registerPerson(new RegisterPerson(namaPerson, alamatPerson, provinsiPerson, kabupatenPerson, ttlPerson, telpnPerson, fotoPerson, bioPerson, levelPerson, filePerson));
        call.enqueue(new Callback<RegisterTeknisiResponse>() {
            @Override
            public void onResponse(Call<RegisterTeknisiResponse> call, Response<RegisterTeknisiResponse> response) {
                System.out.println("Tester Masuk");
                Log.d(TAG, "URL: " + response.raw().request().url());
                Log.d(TAG, "API response login. Code: " + response.code());
                try {
                    if (response.code() == 200 && response.body().getRes_code() == 200) {
                        utils.alertDialogOK(getContext(), "ANDA BERHASIL MENDAFTAR\nMENUNGGU PANGGILAN SELANJUTNYA");
                        getActivity().finish();
                    } else {
                        String msg = response.body().getStatus().getReq_msg();
                        utils.alertDialogOK(getContext(), msg);
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<RegisterTeknisiResponse> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    utils.alertDialogOK(getContext(), "Konteksi Terputus\nPeriksa Kembali Koneksi Internet Anda");
                } else {
                    spKabupaten.setAdapter(null);
                    Log.d(TAG, "Throw: " + t);
                }
            }
        });
    }

}
