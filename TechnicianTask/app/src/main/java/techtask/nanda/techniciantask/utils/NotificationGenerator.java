package techtask.nanda.techniciantask.utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import techtask.nanda.techniciantask.MainActivity;
import techtask.nanda.techniciantask.R;

public class NotificationGenerator {
    private static final int NOTIFICATION_ID_OPEN_ACIIVITY = 9;

    public static void openActivityNotification(Context context){
        NotificationCompat.Builder nc = new NotificationCompat.Builder(context);
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notifyIntent = new Intent(context, MainActivity.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        nc.setContentIntent(pendingIntent);
        nc.setSmallIcon(android.R.drawable.ic_dialog_alert);
        nc.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher));
        nc.setAutoCancel(true);
        nc.setContentTitle("Notification Title");
        nc.setContentText("CLick please");

        nm.notify(NOTIFICATION_ID_OPEN_ACIIVITY, nc.build());
    }
}
