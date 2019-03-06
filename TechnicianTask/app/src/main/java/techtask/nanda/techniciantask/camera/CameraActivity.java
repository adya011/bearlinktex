package techtask.nanda.techniciantask.camera;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.hardware.Camera;

import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import techtask.nanda.techniciantask.R;
import techtask.nanda.techniciantask.model.LongLat;
import techtask.nanda.techniciantask.utils.LocationHelper;

public class CameraActivity extends AppCompatActivity {
    private Camera mCamera;
    private Camera mCamera2;
    private CameraPreview mPreview;
    private Camera.PictureCallback mPicture;
    private ImageButton capture;
    private Context myContext;
    private LinearLayout cameraPreview;
    private boolean cameraFront = false;
    public static Bitmap bitmap;
    Geocoder geocoder;

    @BindView(R.id.tv_longlat)
    TextView tvLongLat;

    @BindView(R.id.tv_clock)
    TextView tvClock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);

        getLongLat();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        myContext = this;

        mCamera = Camera.open();
        mCamera.setDisplayOrientation(90);
        cameraPreview = (LinearLayout) findViewById(R.id.cPreview);

        mPreview = new CameraPreview(myContext, mCamera);
        cameraPreview.addView(mPreview);

        capture = (ImageButton) findViewById(R.id.btnCam);
        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                releaseCamera();

                int cameraId = findBackFacingCamera();
                mCamera = Camera.open(cameraId);
                mCamera.setDisplayOrientation(90);

                mPicture = getPictureCallback();
                mPreview.refreshCamera(mCamera);

                mCamera.takePicture(null, null, mPicture);
            }
        });

        /*switchCamera = (Button) findViewById(R.id.btnSwitch);
        switchCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the number of cameras
                int camerasNumber = Camera.getNumberOfCameras();
                if (camerasNumber > 1) {
                    //release the old camera instance
                    //switch camera, from the front and the back and vice versa

                    releaseCamera();
                    chooseCamera();
                } else {

                }
            }
        });*/

        mCamera.startPreview();
    }

    private void getLongLat() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        } else {

            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if (location != null) {
                double latt = location.getLatitude();
                double longt = location.getLongitude();

                Log.d("longlatdebug", "Latitude awal: " + latt);
                Log.d("longlatdebug", "Longitude awal: " + longt);

                tvLongLat.setText(longt + ", " + latt);

                final Handler someHandler = new Handler(getMainLooper());
                someHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tvClock.setText(new SimpleDateFormat("HH:mm:ss", Locale.US).format(new Date()));
                        someHandler.postDelayed(this, 1000);
                    }
                }, 10);

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListener);
            }
        }
    }

    private final LocationListener locationListener = new LocationListener() {
        double longitude;
        double latitude;

        @Override
        public void onLocationChanged(Location location) {
            longitude = location.getLongitude();
            latitude = location.getLatitude();

            Log.d("longlatdebug", "Latitude: " + latitude);
            Log.d("longlatdebug", "Longitude: " + longitude);

            tvLongLat.setText(longitude + ", " + latitude);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };


    private int findFrontFacingCamera() {

        int cameraId = -1;
        // Search for the front facing camera
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                cameraId = i;
                cameraFront = true;
                break;
            }
        }
        return cameraId;

    }

    private int findBackFacingCamera() {
        int cameraId = -1;
        //Search for the back facing camera
        //get the number of cameras
        int numberOfCameras = Camera.getNumberOfCameras();
        //for every camera check
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                cameraId = i;
                cameraFront = false;
                break;

            }

        }
        return cameraId;
    }

    public void onResume() {

        super.onResume();
        if (mCamera == null) {
            mCamera = Camera.open();
            mCamera.setDisplayOrientation(90);
            mPicture = getPictureCallback();
            mPreview.refreshCamera(mCamera);
            Log.d("nu", "null");
        } else {
            Log.d("nu", "no null");
        }

    }

    public void chooseCamera() {
        //if the camera preview is the front
        if (cameraFront) {
            int cameraId = findBackFacingCamera();
            if (cameraId >= 0) {
                //open the backFacingCamera
                //set a picture callback
                //refresh the preview

                mCamera = Camera.open(cameraId);
                mCamera.setDisplayOrientation(90);
                mPicture = getPictureCallback();
                mPreview.refreshCamera(mCamera);
            }
        } else {
            int cameraId = findFrontFacingCamera();
            if (cameraId >= 0) {
                //open the backFacingCamera
                //set a picture callback
                //refresh the preview
                mCamera = Camera.open(cameraId);
                mCamera.setDisplayOrientation(0);
                mPicture = getPictureCallback();
                mPreview.refreshCamera(mCamera);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //when on Pause, release camera in order to be used from other applications
        releaseCamera();
    }

    private void releaseCamera() {
        // stop and release camera
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.setPreviewCallback(null);
            mCamera.release();
            mCamera = null;
        }
    }

    private Camera.PictureCallback getPictureCallback() {
        Camera.PictureCallback picture = new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

                Matrix matrix = new Matrix();
                matrix.postRotate(90);
                Bitmap rotatedBitmap = bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

                DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
                Date date = new Date();

                LocationHelper loc = new LocationHelper(CameraActivity.this);
                LongLat longLat = loc.getLongLat();
                double longt = longLat.getLongitude();
                double latt = longLat.getLatitude();

                Geocoder geocoderr = new Geocoder(CameraActivity.this, Locale.getDefault());

                String address1, address2, address3, address4;

                try {
                    address1 = geocoderr.getFromLocation(latt, longt, 1).get(0).getAddressLine(0);

                } catch (Exception e) {
                    address1 = "";
                }

                try {
                    address2 = geocoderr.getFromLocation(latt, longt, 1).get(0).getSubLocality();
                } catch (Exception e) {
                    address2 = "";
                }

                try {
                    address3 = geocoderr.getFromLocation(latt, longt, 1).get(0).getLocality();
                } catch (Exception e) {
                    address3 = "";
                }

                try {
                    address4 = geocoderr.getFromLocation(latt, longt, 1).get(0).getCountryName();
                } catch (Exception e) {
                    address4 = "";
                }

                Canvas canvas = new Canvas(rotatedBitmap);
                Paint paint = new Paint();

                canvas.drawBitmap(rotatedBitmap, 0, 0, paint);
                paint.setColor(Color.WHITE);
                paint.setTextSize(8);
                paint.setAntiAlias(true);
                paint.setTextAlign(Paint.Align.RIGHT);

                canvas.drawText(dateFormat.format(date), canvas.getWidth() - 10, canvas.getHeight() - 60, paint);
                canvas.drawText(longt + "  " + latt, canvas.getWidth() - 10, canvas.getHeight() - 50, paint);
                canvas.drawText(address1, canvas.getWidth() - 10, canvas.getHeight() - 40, paint);
                canvas.drawText(address2, canvas.getWidth() - 10, canvas.getHeight() - 30, paint);
                canvas.drawText(address3, canvas.getWidth() - 10, canvas.getHeight() - 20, paint);
                canvas.drawText(address4, canvas.getWidth() - 10, canvas.getHeight() - 10, paint);

                String encoded = bitmapEncode(rotatedBitmap);
                cameraResult(encoded);
            }
        };
        return picture;
    }

    private String bitmapEncode(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);

        return encoded;
    }

    private void cameraResult(String image) {
        Intent data = new Intent();
        data.putExtra("image", image);
        setResult(Activity.RESULT_OK, data);
        finish();
    }
}
