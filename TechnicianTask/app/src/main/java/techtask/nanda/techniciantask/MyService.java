package techtask.nanda.techniciantask;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import techtask.nanda.techniciantask.fragment.LoginFragment;
import techtask.nanda.techniciantask.model.task.Result;
import techtask.nanda.techniciantask.utils.NotificationGenerator;
import techtask.nanda.techniciantask.utils.SharedPrefManager;

/**
 * Created by nandana.samudera on 10/12/2018.
 */

// Service untuk mengambil data dari socket
public class MyService extends Service {
    public static String level_member;
    public static String id_person;
    public static String id_member;
    public static String id_teknisi;
    private static final String TAG = "service_debug";
    public static List<Result> results = new ArrayList<>();
    //static boolean isScanAvailTask;
    SharedPrefManager sharedPrefManager;

    //public static int userRegionalId = 0;

    OkHttpClient client = new OkHttpClient();
    public static String svc = "service variable test";

    CountDownTimer Count;
    int MaxTaskMonId = 0;
    int LastMaxTaskMonId = 0;

    boolean firstMonitoringReq = true;

    static final long CHECK_AVAILABLE_TASK_INTERVAL = 5000;
    long timeLeft = CHECK_AVAILABLE_TASK_INTERVAL;
    public static String idSubkon;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        //idSubkon = sharedPrefManager.getIdTeknisi();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Log.d(TAG, "start command");
        startGetWebSocket();
        //startTimerCheckAvailableTask();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private final class EchoWebScoketListener extends WebSocketListener {
        static final int NORMAL_CLOSURE_STATUS = 1000;

        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            //Log.d(TAG, "user regional id: " + idSubkon);
            idSubkon = sharedPrefManager.getIdTeknisi();
            webSocket.send("{\"id_subkon\":\"" + idSubkon + "\"}");
            /*webSocket.send("{\"id_subkon\":\"" + idSubkon + "\"}");*/
            webSocket.send(ByteString.decodeHex("deadbeef"));
            webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye");
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            output("Receiving : " + text);
            outputConv(text);
        }

        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
            output("Receiving bytes: " + bytes.hex());
        }

        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            webSocket.close(NORMAL_CLOSURE_STATUS, null);
            output("Closing: \n" + code + " / " + reason);
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            output("Error: " + t.getMessage());
        }
    }

    private void startGetWebSocket() {
        //Log.d(TAG, "Get Websocket");
        Request request = null;
        EchoWebScoketListener listener = null;

        /*request = new Request.Builder().url("ws://192.168.43.63:8181/bearlink_web_socket/monitoring").build();*/
        request = new Request.Builder().url("ws://207.180.252.47:8080/bearlink_web_socket/monitoring").build();
        listener = new EchoWebScoketListener();
        client.newWebSocket(request, listener);

        boolean isScanAvailTask = sharedPrefManager.isGetTaskSwitch();
        if (isScanAvailTask) {
            startTimerCheckAvailableTask();
        }
    }

    private void output(final String txt) {
        //Log.d(TAG, txt);
    }

    private void outputConv(final String txt) {
        jsonToObject(txt);
    }

    // Get JSON
    private void jsonToObject(String jsonStr) {
        try {
            //Log.d("ws", "json to object...");
            results.clear();

            JSONObject obj = new JSONObject(jsonStr);
            JSONObject status = obj.getJSONObject("status");
            JSONArray resultsJson = status.getJSONArray("result");

            try {
                for (int i = 0; i < resultsJson.length(); i++) {
                    JSONObject resultJson0 = resultsJson.getJSONObject(i);
                    String id_wo = resultJson0.getString("id_wo");
                    String nama_wo = resultJson0.getString("nama_wo");
                    String ket_wo = resultJson0.getString("ket_wo");

                    results.add(new Result(id_wo, nama_wo, ket_wo));
                    Log.d(TAG, "Notif List:: Result Svc " + i + ": " + results.get(i).getId_wo() + ", " + results.get(i).getNama_wo() + ", " + results.get(i).getKet_wo());

                    //if (i == resultsJson.length() - 1) {    // Get last monitoring loop
                    if (i == resultsJson.length() - 1) {
                        MaxTaskMonId = Integer.valueOf(results.get(i).getId_wo());
                        Log.d(TAG, "Check Notif:: ID Max Monitoring: " + MaxTaskMonId + " | ID Last Max Monitoring: " + LastMaxTaskMonId);
                        Log.d(TAG, "Check Notif:: Notification on? " + sharedPrefManager.isNotificationOn());

                        // Check if monitoring list difference
                        if (MaxTaskMonId > LastMaxTaskMonId && !firstMonitoringReq) {
                            // If notification settings on, call notification
                            if (sharedPrefManager.isNotificationOn()) {
                                Log.d(TAG, "Check Notif:: CALL NOTIFICATION");
                                NotificationGenerator.openActivityNotification(getApplicationContext());
                            }
                        }
                        firstMonitoringReq = false;
                    }

                    LastMaxTaskMonId = MaxTaskMonId;
                }

            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e);
            }

        } catch (Throwable t) {
            Log.e("ws", "parse error: " + t);
        }
    }

    private void startTimerCheckAvailableTask() {
        Count = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long l) {
                timeLeft = l;
                //Log.d(TAG, "time: " + timeLeft);
            }

            @Override
            public void onFinish() {
                timeLeft = CHECK_AVAILABLE_TASK_INTERVAL;
                //Log.d(TAG, "CountDown Finished");
                startGetWebSocket();
            }
        }.start();
    }
}
