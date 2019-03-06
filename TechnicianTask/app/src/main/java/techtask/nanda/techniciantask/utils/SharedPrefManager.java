package techtask.nanda.techniciantask.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ASUS on 11/12/2018.
 */

public class SharedPrefManager {
    public static final String BEARLINK_SHARED_PREF = "bearlink_shared_pref";
    public SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedPrefManager(Context context) {
        sp = context.getSharedPreferences(BEARLINK_SHARED_PREF, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public SharedPrefManager() {

    }


    public void setGetTaskSwitch(boolean taskSwitch) {
        spEditor.putBoolean("task_switch", taskSwitch).commit();
    }

    public boolean isGetTaskSwitch() {
        return sp.getBoolean("task_switch", false);
    }


    public void setLoggedIn(boolean loggedIn) {
        spEditor.putBoolean("is_logged_in", loggedIn).commit();
    }

    public boolean isLoggedIn() {
        return sp.getBoolean("is_logged_in", false);
    }

    public void setUsername(String username) {
        spEditor.putString("username", username).commit();
    }

    public String getUsername() {
        return sp.getString("username", null);
    }

    public void setLastLoggedInUsername(String username) {
        spEditor.putString("last_username", username).commit();
    }

    public String getLastLoggedInUsername() {
        return sp.getString("last_username", null);
    }

    public void setLoggedInUsername(String username) {
        spEditor.putString("username", username).commit();
    }

    public String getLoggedInUsername() {
        return sp.getString("username", null);
    }

    public void setRateStar(String rate) {
        spEditor.putString("rate", rate).commit();
    }

    public String getRateStar() {
        return sp.getString("rate", null);
    }

    /*public void setUserId(int userId) {
        spEditor.putInt("id_user", userId);
    }

    public int getUserId() {
        return sp.getInt("id_user", 0);
    }*/

    /*public void setIdRegionalUser(int idRegionalTeknisi) {
        spEditor.putInt("id_regional_user", idRegionalTeknisi).commit();
    }

    public int getIdRegionalUser() {
        return sp.getInt("id_regional_user", 0);
    }*/

    public void setNotification(boolean notificationSet) {
        spEditor.putBoolean("notif_notification", notificationSet).commit();
    }

    public boolean isNotificationOn() {
        return sp.getBoolean("notif_notification", true);
    }

    public void setIdMember(String idMember) {
        spEditor.putString("id_member", idMember).commit();
    }

    public String getIdMember() {
        return sp.getString("id_member", null);
    }

    public void clearIdMember() {
        spEditor.remove("id_member");
    }

    public void setIdPerson(String idPerson) {
        spEditor.putString("id_person", idPerson).commit();
    }

    public String getIdPerson() {
        return sp.getString("id_person", null);
    }

    public void clearIdPerson() {
        spEditor.remove("id_person").commit();
    }

    public void setIdTeknisi(String idTeknisi) {
        spEditor.putString("id_teknisi", idTeknisi).commit();
    }

    public String getIdTeknisi() {
        return sp.getString("id_teknisi", null);
    }

    public void clearIdTeknisi() {
        spEditor.remove("id_teknisi").commit();
    }

    public void setLevelMember(String levelUser) {
        spEditor.putString("level_user", levelUser).commit();
    }

    public String getLevelMember() {
        return sp.getString("level_user", null);
    }

    public void clearLevelMember() {
        spEditor.remove("level_user").commit();
    }
}
