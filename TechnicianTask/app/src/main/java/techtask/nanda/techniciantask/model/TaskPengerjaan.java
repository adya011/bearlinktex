package techtask.nanda.techniciantask.model;

/**
 * Created by ASUS on 11/12/2018.
 */

public class TaskPengerjaan {
    private String nama_wo;
    private String ket_wo;
    private String id_wo;
    private String status_task;
    private String nama_person;
    private String id_task;

    public TaskPengerjaan(String nama_wo, String ket_wo, String id_wo, String status_task, String nama_person, String id_task) {
        this.nama_wo = nama_wo;
        this.ket_wo = ket_wo;
        this.id_wo = id_wo;
        this.status_task = status_task;
        this.nama_person = nama_person;
        this.id_task = id_task;
    }

    public String getNama_wo() {
        return nama_wo;
    }

    public void setNama_wo(String nama_wo) {
        this.nama_wo = nama_wo;
    }

    public String getKet_wo() {
        return ket_wo;
    }

    public void setKet_wo(String ket_wo) {
        this.ket_wo = ket_wo;
    }

    public String getId_wo() {
        return id_wo;
    }

    public void setId_wo(String id_wo) {
        this.id_wo = id_wo;
    }

    public String getStatus_task() {
        return status_task;
    }

    public void setStatus_task(String status_task) {
        this.status_task = status_task;
    }

    public String getNama_person() {
        return nama_person;
    }

    public void setNama_person(String nama_person) {
        this.nama_person = nama_person;
    }

    public String getId_task() {
        return id_task;
    }

    public void setId_task(String id_task) {
        this.id_task = id_task;
    }
}
