package techtask.nanda.techniciantask.fragment.TaskReport;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import butterknife.BindView;
import io.realm.RealmList;
import io.realm.RealmResults;
import techtask.nanda.techniciantask.R;
import techtask.nanda.techniciantask.TaskReportActivity;
import techtask.nanda.techniciantask.adapter.RTaskDetailImageAdapter;
import techtask.nanda.techniciantask.adapter.RTaskDetailImageDisabledAdapter;
import techtask.nanda.techniciantask.adapter.TaskAvailableListItemListener;
import techtask.nanda.techniciantask.base.BaseFragment;
import techtask.nanda.techniciantask.camera.CameraActivity;
import techtask.nanda.techniciantask.model.LongLat;
import techtask.nanda.techniciantask.model._realm.RTaskReportPhoto;
import techtask.nanda.techniciantask.utils.LocationHelper;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * A simple {@link Fragment} subclass.
 */

// Task report foto
public class TaskReportImageDisabledFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private static final String TAG = "myd_detail_image";
    private final static int REQUEST_CAMERA = 333;
    private final static int REQUEST_CUSTOM_CAMERA = 334;
    private final static int ALL_PERMISSIONS_RESULT = 107;

    RealmList<RTaskReportPhoto> rTaskList = new RealmList<>();

    private ArrayList<String> permissionsToRequest;
    private ArrayList<String> permissions = new ArrayList<>();

    RTaskDetailImageDisabledAdapter rTaskImageAdapter = new RTaskDetailImageDisabledAdapter(rTaskList, getActivity());

    String id_task;
    int idImageReport;

    public TaskReportImageDisabledFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_detail_image, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    // Inisialisasi awal logic
    private void init() {
        checkIfTaskImageRealmExist();
        getAvailableRTaskList();

        permissions.add(CAMERA);
        permissions.add(WRITE_EXTERNAL_STORAGE);
        permissions.add(READ_EXTERNAL_STORAGE);
        permissions.add(ACCESS_COARSE_LOCATION);
        permissions.add(ACCESS_FINE_LOCATION);
        permissionsToRequest = findUnAskedPermissions(permissions);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (permissionsToRequest.size() > 0)
                requestPermissions(permissionsToRequest.toArray(new String[permissionsToRequest.size()]), ALL_PERMISSIONS_RESULT);
        }
    }

    // Cek apakah gambar sudah ada jika belum create tabel report image
    private void checkIfTaskImageRealmExist() {
        Log.d(TAG, "** Check if task image exist **");
        rTaskList.clear();

        id_task = ((TaskReportActivity) getActivity()).id_task;

        RealmResults<RTaskReportPhoto> results = ((TaskReportActivity) getActivity()).realm
                .where(RTaskReportPhoto.class)
                .equalTo("id_task", id_task).findAll();

        for (RTaskReportPhoto taskReportPhoto : results) {
            Log.d(TAG, "ID RTaskReportPhoto: " + taskReportPhoto.getId_task_report_photo());
            Log.d(TAG, "ID RTaskReport: " + taskReportPhoto.getId_task());
        }

        if (results.size() == 0) {
            Log.d(TAG, "Task report photo NOT exist");
            for (int i = 1; i <= 9; i++) {
                createTaskImageRealm(i);
            }

        } else {
            Log.d(TAG, "Task report photo EXIST. " + results.size());
        }

        rTaskList.addAll(results);
    }

    // Create data image report
    private void createTaskImageRealm(int id) {
        realm.beginTransaction();

        RTaskReportPhoto taskReportPhoto = realm.createObject(RTaskReportPhoto.class);
        taskReportPhoto.setId_task_report_photo(id);
        taskReportPhoto.setId_task(id_task);
        taskReportPhoto.setReport_photo(null);

        realm.commitTransaction();
    }

    // Menampilkan list foto
    private void getAvailableRTaskList() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(rTaskImageAdapter);
        recyclerView.addOnItemTouchListener(new TaskAvailableListItemListener(getContext(), recyclerView,
                new TaskAvailableListItemListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        int idTaskImageReport = rTaskList.get(position).getId_task_report_photo();
                        String taskImageReport = rTaskList.get(position).getReport_photo();
                        idImageReport = idTaskImageReport;

                        Log.d(TAG, "id image report: " + idTaskImageReport);

                        if (taskImageReport != null) {
                            gotoImageDetail(idTaskImageReport, taskImageReport);
                        }
                    }

                    @Override
                    public void onLongClick(View view, int position) {
                    }
                }));
    }

    // Tampilkan foto setelah user masuk ke kamera
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CAMERA) {
            if (resultCode == Activity.RESULT_OK) {
                Bundle bundle = data.getExtras();
                final Bitmap bmp = (Bitmap) bundle.get("data");
                String encoded = bitmapEncode(bmp);

                LocationHelper loc = new LocationHelper(getActivity());
                LongLat longLat = loc.getLongLat();
                double longt = longLat.getLongitude();
                double latt = longLat.getLatitude();

                realmUpdateReportImage(encoded, longt, latt);
                getAvailableRTaskList();
            }

        } else if (requestCode == REQUEST_CUSTOM_CAMERA) {
            if (resultCode == Activity.RESULT_OK) {
                String encoded = data.getStringExtra("image");
                Log.d(TAG, "Intent camera result: " + encoded);

                LocationHelper loc = new LocationHelper(getActivity());
                LongLat longLat = loc.getLongLat();
                double longt = longLat.getLongitude();
                double latt = longLat.getLatitude();

                realmUpdateReportImage(encoded, longt, latt);
                getAvailableRTaskList();
            }
        }
    }

    // Update report image setelah replace image dari TaskReportImageDetailFragment
    private void realmUpdateReportImage(String encoded, double longt, double latt) {
        RealmResults<RTaskReportPhoto> results = realm.where(RTaskReportPhoto.class)
                .equalTo("id_task_report_photo", idImageReport).findAll();

        realm.beginTransaction();

        for (RTaskReportPhoto reportPhoto : results) {
            reportPhoto.setReport_photo(encoded);
            reportPhoto.setLongitude_photo(longt);
            reportPhoto.setLatitude_photo(latt);
        }

        realm.commitTransaction();
    }

    // Encode bitmap setelah ambil foto dari kamera
    private String bitmapEncode(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);

        return encoded;
    }

    // list permissions
    private ArrayList<String> findUnAskedPermissions(ArrayList<String> wanted) {
        ArrayList<String> result = new ArrayList<String>();

        for (String perm : wanted) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }

        return result;
    }

    // Cek jika sudah ada permissions
    public boolean hasPermission(String permission) {
        if (canMakeSmores()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return (getContext().checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
            }
        }
        return true;
    }

    private boolean canMakeSmores() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    // Menuju ke TaskReportImageDetailFragment
    private void gotoImageDetail(int idTaskImageReport, String reportPhoto) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frame_content, TaskReportImageDetailFragment.newInstance(idTaskImageReport, reportPhoto));
        ft.addToBackStack(null);
        ft.commit();
    }
}
