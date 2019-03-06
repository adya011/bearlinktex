package techtask.nanda.techniciantask;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import techtask.nanda.techniciantask.fragment.SettingsDetail.AboutFragment;
import techtask.nanda.techniciantask.fragment.SettingsDetail.GuideFragment;
import techtask.nanda.techniciantask.fragment.SettingsDetail.AccountFragment;
import techtask.nanda.techniciantask.fragment.SettingsDetail.ChangePasswordFragment;

// Settings Activity dengan menampilkan fragment > SettingFragment
public class SettingsActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;

    private static final String TAG = "myd_settings";
    int action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        initIntent();
        initToolbar();
    }

    // Ambil dari SettingFragment dan memenentukan fragment apa yang mau ditampilkan
    private void initIntent() {
        Intent intent = getIntent();
        action = intent.getIntExtra("action", 0);

        switch (action) {
            case 1:
                gotoAccountSettings();
                break;
            case 2:
                gotoPasswordSettings();
                break;
            case 3:
                gotoGuide();
                break;
            case 4:
                gotoAbout();
                break;
        }
    }

    // Memanggil fragment AboutFragment
    private void gotoAbout() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_content, new AboutFragment());
        ft.commit();
    }

    // Memanggil fragment GuideFragment
    private void gotoGuide() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_content, new GuideFragment());
        ft.commit();
    }

    // Memanggil fragment CHnagePasswordFragment
    private void gotoPasswordSettings() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_content, new ChangePasswordFragment());
        ft.commit();
    }

    // Memanggil fragment AccountFragment
    private void gotoAccountSettings() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_content, new AccountFragment());
        ft.commit();
    }

    // Inisialisasi toolbar
    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setToolbarTitle(String title){
        toolbarTitle.setText(title);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
