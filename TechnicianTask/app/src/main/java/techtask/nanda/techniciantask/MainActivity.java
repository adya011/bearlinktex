package techtask.nanda.techniciantask;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;
import techtask.nanda.techniciantask.model.LongLat;
import techtask.nanda.techniciantask.model._realm.RTaskPengerjaan;
import techtask.nanda.techniciantask.model._realm.RTaskReport;
import techtask.nanda.techniciantask.model._realm.RTaskReportPhoto;
import techtask.nanda.techniciantask.model._realm.RTeknisi;
import techtask.nanda.techniciantask.utils.MyUtils;
import techtask.nanda.techniciantask.utils.SharedPrefManager;

// class pertama masuk aplikasi
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "myd_MainActivity";
    public SharedPrefManager sharedPrefManager; //Penyimpanan lokal
    Realm realm;    //Penyimpanan database lokal

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    // Inisialisasi awal
    private void init() {
        sharedPrefManager = new SharedPrefManager(this);
        boolean isUserLoggedIn = sharedPrefManager.isLoggedIn();

        // Inisialisasi database lokal
        realm = Realm.getDefaultInstance();

        // Mengecek apakah user telah log in sebelumnya
        if (isUserLoggedIn) {
            gotoDashboard();
            /*int idUserRegional = sharedPrefManager.getIdRegionalUser();*/
            String idUserRegional = sharedPrefManager.getIdTeknisi();
            MyService.idSubkon = idUserRegional;

        } else {
            gotoLogin();
            /*int idUserRegional = sharedPrefManager.getIdRegionalUser();*/
            String idUserRegional = sharedPrefManager.getIdTeknisi();
            if (idUserRegional != null) {
                MyService.idSubkon = idUserRegional;
            }
        }
    }

    // Ke login screen, masuk ke LoginActivity
    private void gotoLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    // Ke dashboard screen, masuk ke DashboardActivity
    private void gotoDashboard() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    // Mengecek data teknisi di Log
    public void viewTeknisi(RealmResults<RTeknisi> results) {
        for (RTeknisi RTeknisi : results) {
            Log.d(TAG, "RTeknisi ID: " + RTeknisi.getId_teknisi() + " ,Username: " + RTeknisi.getUsername_teknisi());
        }
    }

    // Mengecek data pengeraan di Log
    public void viewPengerjaan() {
        RealmResults<RTaskPengerjaan> results = realm.where(RTaskPengerjaan.class).findAll();
        for (RTaskPengerjaan rTaskPengerjaan : results) {

        }
    }

    // Mengecek data report di Log
    public void viewReport() {
        RealmResults<RTaskReport> results = realm.where(RTaskReport.class).findAll();
        for (RTaskReport rTaskReport : results) {
            Log.d(TAG, "Task Report ID: " + rTaskReport.getId_task()
                    + " ,Nama Report: " + rTaskReport.getNama_report()
            );

        }
    }

    // Mengecek data report foto di Log
    public void viewReportPhoto() {
        RealmResults<RTaskReportPhoto> results = realm.where(RTaskReportPhoto.class).findAll();
        for (RTaskReportPhoto rTaskReportPhoto : results) {
            Log.d(TAG, "Task Report Photo ID: " + rTaskReportPhoto.getId_task_report_photo()
                    + " ,Task Report ID: " + rTaskReportPhoto.getId_task()
                    + " ,Photo: " + rTaskReportPhoto.getReport_photo()
            );

        }
    }

    // Jika activity finish close database
    @Override
    protected void onDestroy() {
        Log.d(TAG, "on Destroy");
        realm.close();
        super.onDestroy();
    }
}
