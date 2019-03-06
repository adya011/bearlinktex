package techtask.nanda.techniciantask.model._realm;

import android.graphics.Bitmap;

import io.realm.RealmObject;

/**
 * Created by nandana.samudera on 19/12/2018.
 */

public class RTaskReportPhoto extends RealmObject {
    private int id_task_report_photo;
    private String id_task;
    private String report_photo;
    private double longitude_photo;
    private double latitude_photo;

    public RTaskReportPhoto() {
    };

    public RTaskReportPhoto(int id_task_report_photo, String id_task, String report_photo, double longitude_photo, double latitude_photo) {
        this.id_task_report_photo = id_task_report_photo;
        this.id_task = id_task;
        this.report_photo = report_photo;
        this.longitude_photo = longitude_photo;
        this.latitude_photo = latitude_photo;
    }

    public int getId_task_report_photo() {
        return id_task_report_photo;
    }

    public void setId_task_report_photo(int id_task_report_photo) {
        this.id_task_report_photo = id_task_report_photo;
    }

    public String getId_task() {
        return id_task;
    }

    public void setId_task(String id_task) {
        this.id_task = id_task;
    }

    public String getReport_photo() {
        return report_photo;
    }

    public void setReport_photo(String report_photo) {
        this.report_photo = report_photo;
    }

    public double getLongitude_photo() {
        return longitude_photo;
    }

    public void setLongitude_photo(double longitude_photo) {
        this.longitude_photo = longitude_photo;
    }

    public double getLatitude_photo() {
        return latitude_photo;
    }

    public void setLatitude_photo(double latitude_photo) {
        this.latitude_photo = latitude_photo;
    }
}
