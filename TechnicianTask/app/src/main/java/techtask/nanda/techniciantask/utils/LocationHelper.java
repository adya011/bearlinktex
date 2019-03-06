package techtask.nanda.techniciantask.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import techtask.nanda.techniciantask.model.LongLat;

/**
 * Created by nandana.samudera on 20/01/2019.
 */

public class LocationHelper {
    Activity activity;

    public LocationHelper(Activity activity) {
        this.activity = activity;
    }

    public LongLat getLongLat() {
        Log.d("longlat", "get long lat");

        LongLat longLat = new LongLat();

        LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        } else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if (location != null) {
                double latt = location.getLatitude();
                longLat.setLatitude(latt);

                double longt = location.getLongitude();
                longLat.setLongitude(longt);

                Log.d("longlat", "Latitude: " + latt);
                Log.d("longlat", "Longitude: " + longt);
            }
        }

        return longLat;
    }
}
