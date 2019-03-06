package techtask.nanda.techniciantask.model.task;

/**
 * Created by ASUS on 09/12/2018.
 */

public class Result {
    private String id_wo;
    private String nama_wo;
    private String ket_wo;

    public Result(String id_wo, String nama_wo, String ket_wo) {
        this.id_wo = id_wo;
        this.nama_wo = nama_wo;
        this.ket_wo = ket_wo;
    }

    public String getId_wo() {
        return id_wo;
    }

    public void setId_wo(String id_wo) {
        this.id_wo = id_wo;
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
}
