package techtask.nanda.techniciantask.fragment.TaskReport;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import io.realm.RealmResults;
import techtask.nanda.techniciantask.R;
import techtask.nanda.techniciantask.base.BaseFragment;
import techtask.nanda.techniciantask.camera.CameraActivity;
import techtask.nanda.techniciantask.model.LongLat;
import techtask.nanda.techniciantask.model._realm.RTaskReportPhoto;
import techtask.nanda.techniciantask.utils.LocationHelper;

/**
 * A simple {@link Fragment} subclass.
 */
// Kelas untuk menampilkan foto satu persatu
public class TaskReportImageDetailFragment extends BaseFragment {
    @BindView(R.id.img_report_detail)
    ImageView imgReportDetail;

    private static final String TAG = "myd_report_img_det";
    private final static int REQUEST_CAMERA = 333;
    private final static int REQUEST_CUSTOM_CAMERA = 334;
    int idReportImage;
    String taskImageReport;

    public TaskReportImageDetailFragment() {
        // Required empty public constructor
    }

    // Mengambil data intent dari TaskReportImage, menampilkan berdasarkan id yang telah dipilih
    public static TaskReportImageDetailFragment newInstance(int idReportImage, String reportPhoto) {
        TaskReportImageDetailFragment frag = new TaskReportImageDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", idReportImage);
        bundle.putString("image", reportPhoto);
        frag.setArguments(bundle);

        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_report_image_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        init();
    }

    // Inisialisasi awal logic
    private void init() {
        idReportImage = getArguments().getInt("id");
        taskImageReport = getArguments().getString("image");

        Log.d(TAG, "id image report: " + idReportImage);

        loadImage();
    }

    // Tampilkan image
    private void loadImage() {
        Bitmap decodedImg = utils.bitmapDecode(taskImageReport);
        imgReportDetail.setImageBitmap(decodedImg);
    }

    // Mengatur icon 3 dots di pojok kanan, delete dan edit foto report
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.task_report_image_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    // Mengatur jika 3 dots dipiih, delete dan edit foto report
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_delete_image:
                backFragment();
                realmDeleteTaskReportImage();
                break;

            case R.id.menu_delete_retake:
                /*Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CAMERA);*/

                Intent intent = new Intent(getActivity(), CameraActivity.class);
                startActivityForResult(intent, REQUEST_CUSTOM_CAMERA);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    // Hapus foto dari database lokal
    private void realmDeleteTaskReportImage() {
        RealmResults<RTaskReportPhoto> results = realm.where(RTaskReportPhoto.class)
                .equalTo("id_task_report_photo", idReportImage).findAll();

        realm.beginTransaction();

        for (RTaskReportPhoto reportPhoto : results) {
            reportPhoto.setReport_photo(null);
        }

        realm.commitTransaction();
    }

    // Update foto dari database lokal
    private void realmUpdateReportImage(String encoded, double longt, double latt) {
        RealmResults<RTaskReportPhoto> results = realm.where(RTaskReportPhoto.class)
                .equalTo("id_task_report_photo", idReportImage).findAll();

        realm.beginTransaction();

        for (RTaskReportPhoto reportPhoto : results) {
            reportPhoto.setReport_photo(encoded);
            reportPhoto.setLongitude_photo(longt);
            reportPhoto.setLatitude_photo(latt);
        }

        realm.commitTransaction();
    }

    // Kembali ke fragment sebelumnya, ke TaskReportImageFragment
    private void backFragment() {
        FragmentManager fm = getFragmentManager();
        fm.popBackStack();
    }

    // Menghandle setelah user ke kamera
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CAMERA) {
            if (resultCode == Activity.RESULT_OK) {
                Bundle bundle = data.getExtras();
                final Bitmap bmp = (Bitmap) bundle.get("data");
                String encoded = utils.bitmapEncode(bmp);

                LocationHelper loc = new LocationHelper(getActivity());
                LongLat longLat = loc.getLongLat();
                double longt = longLat.getLongitude();
                double latt = longLat.getLatitude();

                realmUpdateReportImage(encoded, longt, latt);

                taskImageReport = encoded;
                loadImage();
            }
        } else if (requestCode == REQUEST_CUSTOM_CAMERA) {
            if (resultCode == Activity.RESULT_OK) {
                String encoded = data.getStringExtra("image");

                LocationHelper loc = new LocationHelper(getActivity());
                LongLat longLat = loc.getLongLat();
                double longt = longLat.getLongitude();
                double latt = longLat.getLatitude();

                realmUpdateReportImage(encoded, longt, latt);

                taskImageReport = encoded;
                loadImage();
            }
        }
    }
}
