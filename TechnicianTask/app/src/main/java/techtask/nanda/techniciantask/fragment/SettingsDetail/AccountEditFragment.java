package techtask.nanda.techniciantask.fragment.SettingsDetail;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

import butterknife.BindView;
import io.realm.RealmResults;
import techtask.nanda.techniciantask.R;
import techtask.nanda.techniciantask.SettingsActivity;
import techtask.nanda.techniciantask.base.BaseFragment;
import techtask.nanda.techniciantask.model._realm.RTeknisi;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountEditFragment extends BaseFragment {
    @BindView(R.id.et_email)
    TextInputEditText etEmail;

    @BindView(R.id.et_phone)
    TextInputEditText etPhone;

    @BindView(R.id.et_address)
    TextInputEditText etAddress;

    @BindView(R.id.img_photo)
    ImageView imgPhoto;

    @BindView(R.id.btn_save)
    Button btnSave;

    @BindView(R.id.btn_edit_photo)
    Button btnEdit;

    ArrayList<String> permissions = new ArrayList<>();
    ArrayList<String> permissionsToRequest;
    private final static int ALL_PERMISSIONS_RESULT = 107;

    String email, phone, address, photo;
    private final static String TAG = "myd_account";
    private final static int REQUEST_CAMERA = 333;

    String encProfilePhoto;

    public AccountEditFragment() {
        // Required empty public constructor
    }

    // Mengambil data user intent dari SettingsFragment
    public static AccountEditFragment newInstance(String email, String phone, String address, String photo) {
        AccountEditFragment frag = new AccountEditFragment();
        Bundle bundle = new Bundle();
        bundle.putString("email", email);
        bundle.putString("phone", phone);
        bundle.putString("address", address);
        bundle.putString("photo", photo);
        frag.setArguments(bundle);

        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account_edit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((SettingsActivity) getActivity()).setToolbarTitle("Edit Akun");
        initArgs();
        initPermission();
        init();
    }

    // Memasukkan data intent ke variabel
    private void initArgs() {
        email = getArguments().getString("email");
        phone = getArguments().getString("phone");
        address = getArguments().getString("address");
        photo = getArguments().getString("photo");
    }

    // menambahkann permission
    private void initPermission() {
        permissions.add(CAMERA);
        permissions.add(WRITE_EXTERNAL_STORAGE);
        permissions.add(READ_EXTERNAL_STORAGE);
        permissionsToRequest = findUnAskedPermissions(permissions);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (permissionsToRequest.size() > 0)
                requestPermissions(permissionsToRequest.toArray(new String[permissionsToRequest.size()]), ALL_PERMISSIONS_RESULT);
        }
    }

    // Inisialisasi awal logic
    private void init() {
        etEmail.setText(email);
        etPhone.setText(phone);
        etAddress.setText(address);

        if (photo != null) {
            imgPhoto.setImageBitmap(utils.bitmapDecode(photo));
        }

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CAMERA);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updEmail = etEmail.getText().toString();
                String updPhone = etPhone.getText().toString();
                String updAddress = etAddress.getText().toString();

                realmUpdateProfile(updEmail, updPhone, updAddress);

                getFragmentManager().popBackStack();
            }
        });
    }

    // Method dipanggil saat setelah user masuk ke kamera
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                Bundle bundle = data.getExtras();
                final Bitmap bmp = (Bitmap) bundle.get("data");
                String encoded = utils.bitmapEncode(bmp);
                encProfilePhoto = encoded;
                imgPhoto.setImageBitmap(utils.bitmapDecode(encProfilePhoto));
            }
        }
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

    // cek apakah app sudah mempunyai permission
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

    // Update database lokal data profil
    private void realmUpdateProfile(String updEmail, String updPhone, String updAddress) {
        RealmResults<RTeknisi> results = realm
                .where(RTeknisi.class)
                .equalTo("id_teknisi", "11")
                .findAll();

        Log.d(TAG, "** Update Akun Teknisi **");

        realm.beginTransaction();

        for (RTeknisi teknisi : results) {
            teknisi.setEmail_teknisi(updEmail);
            teknisi.setPhone_teknisi(updPhone);
            teknisi.setAddress_teknisi(updAddress);
            teknisi.setPhoto(encProfilePhoto);
        }

        realm.commitTransaction();
    }
}
