package techtask.nanda.techniciantask.model.pengerjaan;

/**
 * Created by nandana.samudera on 14/12/2018.
 */

public class CreateTaskPengerjaan {
    private String id_teknisi;
    private String id_wo;
    private String status_task;
    private String tanggal_task;

    public CreateTaskPengerjaan() {
    }

    public CreateTaskPengerjaan(String id_teknisi, String id_wo, String status_task, String tanggal_task) {
        this.id_teknisi = id_teknisi;
        this.id_wo = id_wo;
        this.status_task = status_task;
        this.tanggal_task = tanggal_task;
    }

    public String getId_teknisi() {
        return id_teknisi;
    }

    public void setId_teknisi(String id_teknisi) {
        this.id_teknisi = id_teknisi;
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

    public String getTanggal_task() {
        return tanggal_task;
    }

    public void setTanggal_task(String tanggal_task) {
        this.tanggal_task = tanggal_task;
    }
}
