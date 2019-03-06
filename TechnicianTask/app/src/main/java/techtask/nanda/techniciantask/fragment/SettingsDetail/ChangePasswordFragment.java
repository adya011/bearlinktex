package techtask.nanda.techniciantask.fragment.SettingsDetail;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import io.realm.RealmResults;
import techtask.nanda.techniciantask.R;
import techtask.nanda.techniciantask.SettingsActivity;
import techtask.nanda.techniciantask.base.BaseFragment;
import techtask.nanda.techniciantask.model._realm.RTeknisi;
import techtask.nanda.techniciantask.utils.RealmHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangePasswordFragment extends BaseFragment {
    @BindView(R.id.et_old_password)
    TextInputEditText etOldPassword;

    @BindView(R.id.et_new_password)
    TextInputEditText etNewPassword;

    @BindView(R.id.et_conf_new_password)
    TextInputEditText etKonfNewPassword;

    @BindView(R.id.btn_save)
    Button btnSave;

    @BindView(R.id.lay_new_password)
    TextInputLayout layNewPassword;

    @BindView(R.id.lay_conf_new_password)
    TextInputLayout layKonfNewPassword;

    @BindView(R.id.lay_old_password)
    TextInputLayout layOldPassword;

    private final static String TAG = "myd_chg_pass";
    RealmHelper realmHelper;

    public ChangePasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings_password, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        realmHelper = new RealmHelper(realm);
        ((SettingsActivity) getActivity()).setToolbarTitle("Ubah Kata Sandi");
        init();
    }

    // Inisialisasi awal
    private void init() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layNewPassword.setErrorEnabled(false);
                layOldPassword.setErrorEnabled(false);

                String oldPassword = etOldPassword.getText().toString();
                final String newPassword = etNewPassword.getText().toString();
                String KonfirmPassword = etKonfNewPassword.getText().toString();

                Log.d(TAG, "old pass: " + oldPassword);
                Log.d(TAG, "new pass: " + newPassword);
                Log.d(TAG, "konf pass: " + KonfirmPassword);

                // Validasi password
                if (oldPassword.equals(realmHelper.getUserPassword()) && oldPassword.length() > 0) {
                    if (newPassword.equals(KonfirmPassword) && newPassword.length() > 0) {
                        realmHelper.updateUserPassword(newPassword);
                        getActivity().finish();
                        Log.d(TAG, "user password set to: " + newPassword);

                    } else {
                        layNewPassword.setErrorEnabled(true);
                        if (newPassword.length() == 0) {
                            layNewPassword.setError("Password tidak boleh kosong");

                        } else {
                            layKonfNewPassword.setError("Konfirm password dan password harus sama");
                        }
                    }
                } else {
                    layOldPassword.setErrorEnabled(true);
                    if (oldPassword.length() == 0) {
                        layOldPassword.setError("Password lama harus diisi");
                    } else {
                        layOldPassword.setError("Password lama yang anda masukkan salah");
                    }
                }
            }
        });
    }
}
