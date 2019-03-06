package techtask.nanda.techniciantask.model.account;

/**
 * Created by nandana.samudera on 14/12/2018.
 */

public class AccountUserResult {
    public String nama_teknisi;
    public String kabupaten_person;
    public String alamat_person;
    public String nama_leader;
    public String kabupaten_penempatan;
    public String provinsi_person;
    public String provinsi_penempatan;
    public String telp_person;

    public AccountUserResult() {
    }

    public AccountUserResult(String nama_teknisi, String kabupaten_person, String alamat_person, String nama_leader, String kabupaten_penempatan, String provinsi_person, String provinsi_penempatan, String telp_person) {
        this.nama_teknisi = nama_teknisi;
        this.kabupaten_person = kabupaten_person;
        this.alamat_person = alamat_person;
        this.nama_leader = nama_leader;
        this.kabupaten_penempatan = kabupaten_penempatan;
        this.provinsi_person = provinsi_person;
        this.provinsi_penempatan = provinsi_penempatan;
        this.telp_person = telp_person;
    }

    public String getNama_teknisi() {
        return nama_teknisi;
    }

    public void setNama_teknisi(String nama_teknisi) {
        this.nama_teknisi = nama_teknisi;
    }

    public String getKabupaten_person() {
        return kabupaten_person;
    }

    public void setKabupaten_person(String kabupaten_person) {
        this.kabupaten_person = kabupaten_person;
    }

    public String getAlamat_person() {
        return alamat_person;
    }

    public void setAlamat_person(String alamat_person) {
        this.alamat_person = alamat_person;
    }

    public String getNama_leader() {
        return nama_leader;
    }

    public void setNama_leader(String nama_leader) {
        this.nama_leader = nama_leader;
    }

    public String getKabupaten_penempatan() {
        return kabupaten_penempatan;
    }

    public void setKabupaten_penempatan(String kabupaten_penempatan) {
        this.kabupaten_penempatan = kabupaten_penempatan;
    }

    public String getProvinsi_person() {
        return provinsi_person;
    }

    public void setProvinsi_person(String provinsi_person) {
        this.provinsi_person = provinsi_person;
    }

    public String getProvinsi_penempatan() {
        return provinsi_penempatan;
    }

    public void setProvinsi_penempatan(String provinsi_penempatan) {
        this.provinsi_penempatan = provinsi_penempatan;
    }

    public String getTelp_person() {
        return telp_person;
    }

    public void setTelp_person(String telp_person) {
        this.telp_person = telp_person;
    }
}
