package techtask.nanda.techniciantask;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.net.SocketTimeoutException;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techtask.nanda.techniciantask.api.ApiInterface;
import techtask.nanda.techniciantask.api.RestProvider;
import techtask.nanda.techniciantask.fragment.HomeFragment;
import techtask.nanda.techniciantask.fragment.Tab1Fragment;
import techtask.nanda.techniciantask.fragment.Tab2Fragment;
import techtask.nanda.techniciantask.fragment.Tab3Fragment;
import techtask.nanda.techniciantask.model.account.AccountUserResponse;
import techtask.nanda.techniciantask.model.account.AccountUserResult;
import techtask.nanda.techniciantask.utils.SharedPrefManager;

// Activity dashboard terdiri dari Tab1Fragment, Tab2Fragment, Tab3Fragment
// yang menampilkan HomeFragment, TaskPengerjaanFragment, Settings Fragment
public class DashboardActivity extends AppCompatActivity {
    @BindView(R.id.bot_nav)
    BottomNavigationView bottomNavigationView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.menu_drawer)
    DrawerLayout drawerLayout;

    @BindView(R.id.nv)
    NavigationView navigationView;

    @BindView(R.id.img_bertex_toolbar_offline)
    ImageView imgLogoOff;

    @BindView(R.id.img_bertex_toolbar_online)
    ImageView imgLogoOn;

    @BindView(R.id.lay_bertex_switch)
    LinearLayout bertexSwitch;

    TextView navUsername;
    ImageView imgRate1, imgRate2, imgRate3, imgRate4, imgRate5;

    ActionBarDrawerToggle barToggle;

    /*@BindView(R.id.toolbar_title)
    TextView toolbarTitle;*/

    public Realm realm;
    public int userId;

    protected SharedPrefManager sharedPrefManager;

    public static MyService myService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        sharedPrefManager = new SharedPrefManager(this);

        ButterKnife.bind(this);
        initToolbar();
        initNav();
        init();
        initRealm();
    }

    // Inisialisasi toolbar
    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        barToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(barToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void toolbarToggle(boolean toggle) {
        if (toggle) {
            imgLogoOn.setVisibility(View.VISIBLE);
            imgLogoOff.setVisibility(View.GONE);

        } else {
            imgLogoOn.setVisibility(View.GONE);
            imgLogoOff.setVisibility(View.VISIBLE);
        }
    }

    // Inisialisasi awal logic
    private void init() {
        final Intent settingsIntent = new Intent(this, SettingsActivity.class);
        final Intent loginIntent = new Intent(this, LoginActivity.class);
        //userId = sharedPrefManager.getUserId();
        rateStar(3);

        bertexSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("myd", "Bertex Clicked");
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.account:
                        settingsIntent.putExtra("action", 1);
                        startActivity(settingsIntent);
                        break;

                    case R.id.password:
                        settingsIntent.putExtra("action", 2);
                        startActivity(settingsIntent);
                        break;

                    case R.id.notification:
                        settingsIntent.putExtra("action", 5);
                        startActivity(settingsIntent);
                        break;

                    case R.id.guide:
                        settingsIntent.putExtra("action", 3);
                        startActivity(settingsIntent);
                        break;

                    case R.id.about:
                        settingsIntent.putExtra("action", 4);
                        startActivity(settingsIntent);
                        break;

                    case R.id.logout:
                        sharedPrefManager.setLoggedIn(false);
                        sharedPrefManager.setGetTaskSwitch(false);

                        sharedPrefManager.clearLevelMember();
                        sharedPrefManager.clearIdPerson();
                        sharedPrefManager.clearIdMember();
                        sharedPrefManager.clearIdTeknisi();

                        startActivity(loginIntent);
                        break;

                    default:
                        return true;
                }
                return true;
            }
        });
    }

    /*public void setToolbarTitle(String title){
        toolbarTitle.setText(title);
    }*/

    // Inisialisasi database lokal
    private void initRealm() {
        realm = Realm.getDefaultInstance();
    }

    // Fungsi untuk tombol navigasi
    private void initNav() {
        View headerView = navigationView.getHeaderView(0);
        navUsername = headerView.findViewById(R.id.nav_slider_name);
        imgRate1 = headerView.findViewById(R.id.img_rate_1);
        imgRate2 = headerView.findViewById(R.id.img_rate_2);
        imgRate3 = headerView.findViewById(R.id.img_rate_3);
        imgRate4 = headerView.findViewById(R.id.img_rate_4);
        imgRate5 = headerView.findViewById(R.id.img_rate_5);

        String idPerson = sharedPrefManager.getIdPerson();

        getPersonDetail(idPerson);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFrag = HomeFragment.newInstance();

                switch (item.getItemId()) {
                    case R.id.action_item1:
                        selectedFrag = new Tab1Fragment();
                        break;

                    case R.id.action_item2:
                        selectedFrag = new Tab2Fragment();
                        break;

                    /*case R.id.action_item3:
                        selectedFrag = new Tab3Fragment();
                        break;*/
                }

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frame_content, selectedFrag);
                ft.commit();

                return true;
            }
        });

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_content, new Tab1Fragment());
        ft.commit();
    }

    // Mengaktifkan tombol navigasi
    public void selectNav(int item) {
        int itemId = R.id.action_item1;
        switch (item) {
            case 1:
                itemId = R.id.action_item1;
                break;
            case 2:
                itemId = R.id.action_item2;
                break;
            /*case 3:
                itemId = R.id.action_item3;
                break;*/
        }
        bottomNavigationView.setSelectedItemId(itemId);
    }

    private void getPersonDetail(String id_person) {
        ApiInterface apiInterface = RestProvider.getTeknisiController().create(ApiInterface.class);
        Call<AccountUserResponse> call = apiInterface.callAccountSettingPerson(id_person);
        call.enqueue(new Callback<AccountUserResponse>() {
            @Override
            public void onResponse(Call<AccountUserResponse> call, Response<AccountUserResponse> response) {
                System.out.println("Tester Masuk");
                try {
                    if (response.code() == 200 && response.body().getRes_code() == 200) {
                        int provinsiListSize = response.body().getStatus().getResult().size();
                        for (int i = 0; i < provinsiListSize; i++) {
                            String nama_teknisi = response.body().getStatus().getResult().get(i).getNama_teknisi();
                            navUsername.setText(nama_teknisi);
                            sharedPrefManager.setLoggedInUsername(nama_teknisi);
                        }

                    } else {
                        String msg = response.body().getStatus().getReq_msg();
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<AccountUserResponse> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {

                } else {

                }
            }
        });
    }

    private void rateStar(float rate) {
        int rateRound = Math.round(rate);

        switch (rateRound) {
            case 1:
                imgRate1.setVisibility(View.VISIBLE);
                imgRate2.setVisibility(View.INVISIBLE);
                imgRate3.setVisibility(View.INVISIBLE);
                imgRate4.setVisibility(View.INVISIBLE);
                imgRate5.setVisibility(View.INVISIBLE);
                break;
            case 2:
                imgRate1.setVisibility(View.VISIBLE);
                imgRate2.setVisibility(View.VISIBLE);
                imgRate3.setVisibility(View.INVISIBLE);
                imgRate4.setVisibility(View.INVISIBLE);
                imgRate5.setVisibility(View.INVISIBLE);
                break;
            case 3:
                imgRate1.setVisibility(View.VISIBLE);
                imgRate2.setVisibility(View.VISIBLE);
                imgRate3.setVisibility(View.VISIBLE);
                imgRate4.setVisibility(View.INVISIBLE);
                imgRate5.setVisibility(View.INVISIBLE);
                break;
            case 4:
                imgRate1.setVisibility(View.VISIBLE);
                imgRate2.setVisibility(View.VISIBLE);
                imgRate3.setVisibility(View.VISIBLE);
                imgRate4.setVisibility(View.VISIBLE);
                imgRate5.setVisibility(View.INVISIBLE);
                break;
            case 5:
                imgRate1.setVisibility(View.VISIBLE);
                imgRate2.setVisibility(View.VISIBLE);
                imgRate3.setVisibility(View.VISIBLE);
                imgRate4.setVisibility(View.VISIBLE);
                imgRate5.setVisibility(View.VISIBLE);
                break;
        }
    }


    // close database lokal jika activity destroy
    @Override
    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        barToggle.syncState();
    }
}
