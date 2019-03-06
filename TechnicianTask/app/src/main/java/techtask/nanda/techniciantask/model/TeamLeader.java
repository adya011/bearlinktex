package techtask.nanda.techniciantask.model;

/**
 * Created by ASUS on 12/12/2018.
 */

public class TeamLeader {
    private String foto_leader;
    private int id_leader;
    private String nama_leader;
    private String nomor_leader;
    private String nama_regional;
    private String longlat_regional;
    private String alamat_leader;
    private String alamat_regional;

    public TeamLeader(String foto_leader, int id_leader, String nama_leader, String nomor_leader, String nama_regional, String longlat_regional, String alamat_leader, String alamat_regional) {
        this.foto_leader = foto_leader;
        this.id_leader = id_leader;
        this.nama_leader = nama_leader;
        this.nomor_leader = nomor_leader;
        this.nama_regional = nama_regional;
        this.longlat_regional = longlat_regional;
        this.alamat_leader = alamat_leader;
        this.alamat_regional = alamat_regional;
    }

    public String getFoto_leader() {
        return foto_leader;
    }

    public void setFoto_leader(String foto_leader) {
        this.foto_leader = foto_leader;
    }

    public int getId_leader() {
        return id_leader;
    }

    public void setId_leader(int id_leader) {
        this.id_leader = id_leader;
    }

    public String getNama_leader() {
        return nama_leader;
    }

    public void setNama_leader(String nama_leader) {
        this.nama_leader = nama_leader;
    }

    public String getNomor_leader() {
        return nomor_leader;
    }

    public void setNomor_leader(String nomor_leader) {
        this.nomor_leader = nomor_leader;
    }

    public String getNama_regional() {
        return nama_regional;
    }

    public void setNama_regional(String nama_regional) {
        this.nama_regional = nama_regional;
    }

    public String getLonglat_regional() {
        return longlat_regional;
    }

    public void setLonglat_regional(String longlat_regional) {
        this.longlat_regional = longlat_regional;
    }

    public String getAlamat_leader() {
        return alamat_leader;
    }

    public void setAlamat_leader(String alamat_leader) {
        this.alamat_leader = alamat_leader;
    }

    public String getAlamat_regional() {
        return alamat_regional;
    }

    public void setAlamat_regional(String alamat_regional) {
        this.alamat_regional = alamat_regional;
    }
}
