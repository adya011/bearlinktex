package techtask.nanda.techniciantask.utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MyUtils {
    public static void alertDialogOK(Context context, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setPositiveButton("OK", null);
        builder.setMessage(message);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public Bitmap bitmapDecode(String encoded){
        byte[] imageAsBytes = Base64.decode(encoded, Base64.NO_WRAP);
        Bitmap bmp = BitmapFactory.decodeByteArray(imageAsBytes,0,imageAsBytes.length);
        return bmp;
    }

    public String bitmapEncode(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);

        return encoded;
    }

    // Keyboard agar tidak selalu muncul otomatis saat masuk fragment
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
