package techtask.nanda.techniciantask.fragment;


import android.content.Intent;
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
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.SocketTimeoutException;
import java.util.List;

import static butterknife.ButterKnife.findById;

import butterknife.BindView;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techtask.nanda.techniciantask.DashboardActivity;
import techtask.nanda.techniciantask.MyService;
import techtask.nanda.techniciantask.R;
import techtask.nanda.techniciantask.RegisterActivity;
import techtask.nanda.techniciantask.api.ApiInterface;
import techtask.nanda.techniciantask.api.RestProvider;
import techtask.nanda.techniciantask.base.BaseFragment;
import techtask.nanda.techniciantask.model.Provinsi;
import techtask.nanda.techniciantask.model.ProvinsiResponse;
import techtask.nanda.techniciantask.model._realm.RTaskReport;
import techtask.nanda.techniciantask.model._realm.RTeknisi;
import techtask.nanda.techniciantask.model.pengerjaan.CreateTaskPengerjaan;
import techtask.nanda.techniciantask.model.registerteknisi.RegisterPerson;
import techtask.nanda.techniciantask.model.registerteknisi.RegisterTeknisiResponse;
import techtask.nanda.techniciantask.model.user.LoginUser;
import techtask.nanda.techniciantask.model.user.LoginUserResponse;
import techtask.nanda.techniciantask.model.user.LoginUserResult;
import techtask.nanda.techniciantask.utils.SharedPrefManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment {
    private static final String TAG = "myd_login";

    @BindView(R.id.btn_login)
    Button btnLogin;

    @BindView(R.id.tv_register)
    TextView tvRegister;

    @BindView(R.id.et_username)
    EditText etUsername;

    @BindView(R.id.et_password)
    EditText etPassword;

    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    // Inisialisasi awal
    private void init() {
        String lastUsername = sharedPrefManager.getLastLoggedInUsername();

        if (lastUsername != null) {
            etUsername.setText(lastUsername);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                prosesLogin(username, password);
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoRegister();
            }
        });
    }

    /*public static String id_member;
    public static String id_person;
    public static String id_teknisi;
    public static String level_member;*/

    private void prosesLogin(final String username, String password) {
        progressBar.setVisibility(View.VISIBLE);
        btnLogin.setText("");
        ApiInterface apiInterface = RestProvider.getTeknisiController().create(ApiInterface.class);
        Call<LoginUserResponse> call = apiInterface.loginTeknisi(new LoginUser(username, password));
        call.enqueue(new Callback<LoginUserResponse>() {
            @Override
            public void onResponse(Call<LoginUserResponse> call, Response<LoginUserResponse> response) {
                System.out.println("Tester Masuk");
                Log.d(TAG, "URL: " + response.raw().request().url());
                Log.d(TAG, "API response login. Code: " + response.code());
                try {
                    if (response.code() == 200 && response.body().getRes_code() == 200) {
                        List<LoginUserResult> result = response.body().getStatus().getResult();
                        for (LoginUserResult result1 : result) {
                            String id_member = result1.getId_member();
                            String id_person = result1.getId_person();
                            String id_teknisi = result1.getId_teknisi();
                            String level_member = result1.getLevel_member();

                            sharedPrefManager.setIdMember(id_member);
                            sharedPrefManager.setIdPerson(id_person);
                            sharedPrefManager.setIdTeknisi(id_teknisi);
                            sharedPrefManager.setLevelMember(level_member);
                        }
                        sharedPrefManager.setNotification(true);
                        sharedPrefManager.setLoggedIn(true);
                        sharedPrefManager.setLastLoggedInUsername(username);
                        gotoDashboard();

                    } else {
                        String msg = response.body().getStatus().getReq_msg();
                        utils.alertDialogOK(getContext(), msg);
                    }
                } catch (Exception e) {

                }

                progressBar.setVisibility(View.GONE);
                btnLogin.setText("Sign In");
            }

            @Override
            public void onFailure(Call<LoginUserResponse> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    utils.alertDialogOK(getContext(), "Konteksi Terputus\nPeriksa Kembali Koneksi Internet Anda");
                } else {

                }

                progressBar.setVisibility(View.GONE);
                btnLogin.setText("Sign In");
            }
        });
    }

    // Ke halaman dashboard ke DashbardActivity
    private void gotoDashboard() {
        Intent intent = new Intent(getContext(), DashboardActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    // Ke halaman register ke RegisterActivity
    private void gotoRegister() {
        Intent intent = new Intent(getContext(), RegisterActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

}
