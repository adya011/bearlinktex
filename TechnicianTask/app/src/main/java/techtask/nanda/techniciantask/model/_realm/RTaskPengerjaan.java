package techtask.nanda.techniciantask.model._realm;

import io.realm.RealmObject;

/**
 * Created by ASUS on 17/12/2018.
 */

public class RTaskPengerjaan extends RealmObject {
    private int id_task_pengerjaan;
    private int id_task_monitoring;
    private String id_teknisi;
    private String nama_task_pengerjaan;
    private int status_pengerjaan;
    private String tanggal_pengerjaan;

    public RTaskPengerjaan() {
    }

    public RTaskPengerjaan(int id_task_pengerjaan, int id_task_monitoring, String id_teknisi, String nama_task_pengerjaan, int status_pengerjaan, String tanggal_pengerjaan) {
        this.id_task_pengerjaan = id_task_pengerjaan;
        this.id_task_monitoring = id_task_monitoring;
        this.id_teknisi = id_teknisi;
        this.nama_task_pengerjaan = nama_task_pengerjaan;
        this.status_pengerjaan = status_pengerjaan;
        this.tanggal_pengerjaan = tanggal_pengerjaan;
    }

    /*public RTaskPengerjaan(String id_task_pengerjaan, String id_task_monitoring, String id_teknisi, String nama_task_pengerjaan, String status_pengerjaan, String tanggal_pengerjaan) {
        this.id_task_pengerjaan = id_task_pengerjaan;
        this.id_task_monitoring = id_task_monitoring;
        this.id_teknisi = id_teknisi;
        this.nama_task_pengerjaan = nama_task_pengerjaan;
        this.status_pengerjaan = status_pengerjaan;
        this.tanggal_pengerjaan = tanggal_pengerjaan;
    }*/

    public int getId_task_pengerjaan() {
        return id_task_pengerjaan;
    }

    public void setId_task_pengerjaan(int id_task_pengerjaan) {
        this.id_task_pengerjaan = id_task_pengerjaan;
    }

    public int getId_task_monitoring() {
        return id_task_monitoring;
    }

    public void setId_task_monitoring(int id_task_monitoring) {
        this.id_task_monitoring = id_task_monitoring;
    }

    public String getId_teknisi() {
        return id_teknisi;
    }

    public void setId_teknisi(String id_teknisi) {
        this.id_teknisi = id_teknisi;
    }

    public String getNama_task_pengerjaan() {
        return nama_task_pengerjaan;
    }

    public void setNama_task_pengerjaan(String nama_task_pengerjaan) {
        this.nama_task_pengerjaan = nama_task_pengerjaan;
    }

    public int getStatus_pengerjaan() {
        return status_pengerjaan;
    }

    public void setStatus_pengerjaan(int status_pengerjaan) {
        this.status_pengerjaan = status_pengerjaan;
    }

    public String getTanggal_pengerjaan() {
        return tanggal_pengerjaan;
    }

    public void setTanggal_pengerjaan(String tanggal_pengerjaan) {
        this.tanggal_pengerjaan = tanggal_pengerjaan;
    }
}
