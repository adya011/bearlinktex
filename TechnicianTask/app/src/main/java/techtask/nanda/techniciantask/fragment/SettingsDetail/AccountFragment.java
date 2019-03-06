package techtask.nanda.techniciantask.fragment.SettingsDetail;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.SocketTimeoutException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techtask.nanda.techniciantask.BaseActivity;
import techtask.nanda.techniciantask.LoginActivity;
import techtask.nanda.techniciantask.R;
import techtask.nanda.techniciantask.SettingsActivity;
import techtask.nanda.techniciantask.api.ApiInterface;
import techtask.nanda.techniciantask.api.RestProvider;
import techtask.nanda.techniciantask.base.BaseFragment;
import techtask.nanda.techniciantask.fragment.LoginFragment;
import techtask.nanda.techniciantask.model._realm.RTeknisi;
import techtask.nanda.techniciantask.model.account.AccountUserResponse;
import techtask.nanda.techniciantask.model.account.AccountUserResult;
import techtask.nanda.techniciantask.model.report.CreateReportTeknisi;
import techtask.nanda.techniciantask.model.user.LoginUser;
import techtask.nanda.techniciantask.model.user.LoginUserResponse;
import techtask.nanda.techniciantask.model.user.LoginUserResult;

/**
 * A simple {@link Fragment} subclass.
 */
// Kelas menampilkan profil user
public class AccountFragment extends BaseFragment {

    @BindView(R.id.tv_nama_teknisi)
    TextView tvNamaTeknisi;

    @BindView(R.id.tv_alamat_person)
    TextView tvAlamatPerson;

    @BindView(R.id.tv_provinsi_person)
    TextView tvProvinsiPerson;

    @BindView(R.id.tv_kabupaten_person)
    TextView tvKabupatenPerson;

    @BindView(R.id.tv_telp_person)
    TextView tvTelephonePerson;

    @BindView(R.id.tv_jabatan)
    TextView tvjabatan;

    @BindView(R.id.tv_nama_leader)
    TextView tvNamaLeader;

    @BindView(R.id.tv_provinsi_penempatan)
    TextView tvProvinsiPenempatan;

    @BindView(R.id.tv_kabupaten_penempatan)
    TextView tvKabupatenPenempatan;

    @BindView(R.id.btn_edit)
    Button btnEdit;

    @BindView(R.id.img_profile)
    ImageView imgProfile;

    private final static String TAG = "myd_account";

    String userNameTeknisi;
    String namaTeknisi;
    String emailTeknisi;
    String alamatTeknisi;
    String phoneTeknisi;
    String jabatan;
    String namaLeader;
    String provinsi;
    String kabupaten;
    String photo;

    List<AccountUserResult> accountTeknisiList = new ArrayList<>();
    List<String> accountTeknisiListStr = new ArrayList<>();

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((SettingsActivity) getActivity()).setToolbarTitle("Akun Info");
        init();
    }

    // Inisalisasi awal logic
    private void init() {
        //realm = Realm.getDefaultInstance();
        String idPerson = sharedPrefManager.getIdPerson();
        getPersonDetail(idPerson);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame_content, AccountEditFragment.newInstance(emailTeknisi, phoneTeknisi, alamatTeknisi, photo));
                ft.addToBackStack(null);
                ft.commit();
            }
        });
    }

    private void getPersonDetail(String id_person) {
        ApiInterface apiInterface = RestProvider.getTeknisiController().create(ApiInterface.class);
        Call<AccountUserResponse> call = apiInterface.callAccountSettingPerson(id_person);
        call.enqueue(new Callback<AccountUserResponse>() {
            @Override
            public void onResponse(Call<AccountUserResponse> call, Response<AccountUserResponse> response) {
                System.out.println("Tester Masuk");
                Log.d(TAG, "URL: " + response.raw().request().url());
                Log.d(TAG, "API response login. Code: " + response.code());
                try {
                    if (response.code() == 200 && response.body().getRes_code() == 200) {
                        int provinsiListSize = response.body().getStatus().getResult().size();
                        for (int i = 0; i < provinsiListSize; i++) {
                            String nama_teknisi = response.body().getStatus().getResult().get(i).getNama_teknisi();
                            String kabupaten_person = response.body().getStatus().getResult().get(i).getKabupaten_person();
                            String alamat_person = response.body().getStatus().getResult().get(i).getAlamat_person();
                            String nama_leader = response.body().getStatus().getResult().get(i).getNama_leader();
                            String kabupaten_penempatan = response.body().getStatus().getResult().get(i).getKabupaten_penempatan();
                            String provinsi_person = response.body().getStatus().getResult().get(i).getProvinsi_person();
                            String provinsi_penempatan = response.body().getStatus().getResult().get(i).getProvinsi_penempatan();
                            String telp_person = response.body().getStatus().getResult().get(i).getTelp_person();
                            accountTeknisiList.add(new AccountUserResult(nama_teknisi, kabupaten_person, alamat_person, nama_leader, kabupaten_penempatan, provinsi_person, provinsi_penempatan, telp_person));
                        }
                        getAccountDetailAdapter();
                    } else {
                        String msg = response.body().getStatus().getReq_msg();
                        utils.alertDialogOK(getContext(), msg);
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<AccountUserResponse> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    utils.alertDialogOK(getContext(), "Konteksi Terputus\nPeriksa Kembali Koneksi Internet Anda");
                } else {

                }
            }
        });
    }

    private void getAccountDetailAdapter() {
        final ArrayAdapter<AccountUserResult> adapter = new ArrayAdapter<AccountUserResult>(getContext(), R.layout.fragment_settings_account, accountTeknisiList);
        for (AccountUserResult userResult : accountTeknisiList) {
            tvNamaTeknisi.setText(userResult.getNama_teknisi());
            tvAlamatPerson.setText(userResult.getAlamat_person());
            tvProvinsiPerson.setText(userResult.getProvinsi_person());
            tvKabupatenPerson.setText(userResult.getKabupaten_person());
            tvTelephonePerson.setText(userResult.getTelp_person());
            tvjabatan.setText("Teknisi");
            tvNamaLeader.setText(userResult.getNama_leader());
            tvProvinsiPenempatan.setText(userResult.getProvinsi_penempatan());
            tvKabupatenPenempatan.setText(userResult.getKabupaten_penempatan());
        }
    }
}
