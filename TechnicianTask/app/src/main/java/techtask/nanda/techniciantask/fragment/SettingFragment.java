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
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import butterknife.BindView;
import techtask.nanda.techniciantask.DashboardActivity;
import techtask.nanda.techniciantask.LoginActivity;
import techtask.nanda.techniciantask.R;
import techtask.nanda.techniciantask.SettingsActivity;
import techtask.nanda.techniciantask.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends BaseFragment {
    private static final String TAG = "myd_settings";
    @BindView(R.id.akun)
    LinearLayout account;

    @BindView(R.id.password)
    LinearLayout password;

    @BindView(R.id.guide)
    LinearLayout guide;

    @BindView(R.id.about)
    LinearLayout about;

    @BindView(R.id.logout)
    LinearLayout logout;

    @BindView(R.id.sw_notification)
    Switch swNotification;

    public SettingFragment() {
        // Required empty public constructor
    }

    public static SettingFragment newInstance() {
        SettingFragment frag = new SettingFragment();
        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //((DashboardActivity) getActivity()).setToolbarTitle("Settings");
        initView();
        init();
    }

    // Mengambil dari shared prefence jika true switch di on
    private void initView() {
        boolean isNotificationOn = sharedPrefManager.isNotificationOn();
        swNotification.setChecked(isNotificationOn);
    }

    // Inisialisasi awal logic
    private void init() {
        final Intent settingsIntent = new Intent(getContext(), SettingsActivity.class);

        // Menuju ke SettingsActivity dengan menampilkan fragment > SettingsDetail > AccountFragment
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingsIntent.putExtra("action", 1);
                startActivity(settingsIntent);
            }
        });

        // Menuju ke SettingsActivity dengan menampilkan fragment > SettingsDetail > ChangePasswordFragment
        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingsIntent.putExtra("action", 2);
                startActivity(settingsIntent);
            }
        });

        // Switch untuk notifikasi
        swNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sharedPrefManager.setNotification(isChecked);
                Log.d(TAG, "Switch: " + isChecked + ", Notification: " + sharedPrefManager.isNotificationOn());
            }
        });

        // Menuju ke SettingsActivity dengan menampilkan fragment > SettingsDetail > GuideFragment
        guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingsIntent.putExtra("action", 3);
                startActivity(settingsIntent);
            }
        });

        // Menuju ke SettingsActivity dengan menampilkan fragment > SettingsDetail > AboutFrament
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingsIntent.putExtra("action", 4);
                startActivity(settingsIntent);
            }
        });

        // Logout aplikasi dengan menuju halaman LoginActivity dengan menampilkan LoginFragment
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPrefManager.setLoggedIn(false);
                sharedPrefManager.setGetTaskSwitch(false);

                sharedPrefManager.clearLevelMember();
                sharedPrefManager.clearIdPerson();
                sharedPrefManager.clearIdMember();
                sharedPrefManager.clearIdTeknisi();

                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });
    }
}