package techtask.nanda.techniciantask.model.registerteknisi;

/**
 * Created by ASUS on 11/12/2018.
 */

public class RegisterPerson {
    private String nama_person;
    private String alamat_person;
    private String provinsi_person;
    private String kabupaten_person;
    private String ttl_person;
    private String telp_person;
    private String foto_person;
    private String bio_person;
    private String level_person;
    private String file_person;

    public RegisterPerson(String nama_person, String alamat_person, String provinsi_person, String kabupaten_person, String ttl_person, String telp_person, String foto_person, String bio_person, String level_person, String file_person) {
        this.nama_person = nama_person;
        this.alamat_person = alamat_person;
        this.provinsi_person = provinsi_person;
        this.kabupaten_person = kabupaten_person;
        this.ttl_person = ttl_person;
        this.telp_person = telp_person;
        this.foto_person = foto_person;
        this.bio_person = bio_person;
        this.level_person = level_person;
        this.file_person = file_person;
    }

    public String getNama_person() {
        return nama_person;
    }

    public void setNama_person(String nama_person) {
        this.nama_person = nama_person;
    }

    public String getAlamat_person() {
        return alamat_person;
    }

    public void setAlamat_person(String alamat_person) {
        this.alamat_person = alamat_person;
    }

    public String getProvinsi_person() {
        return provinsi_person;
    }

    public void setProvinsi_person(String provinsi_person) {
        this.provinsi_person = provinsi_person;
    }

    public String getKabupaten_person() {
        return kabupaten_person;
    }

    public void setKabupaten_person(String kabupaten_person) {
        this.kabupaten_person = kabupaten_person;
    }

    public String getTtl_person() {
        return ttl_person;
    }

    public void setTtl_person(String ttl_person) {
        this.ttl_person = ttl_person;
    }

    public String getTelp_person() {
        return telp_person;
    }

    public void setTelp_person(String telp_person) {
        this.telp_person = telp_person;
    }

    public String getFoto_person() {
        return foto_person;
    }

    public void setFoto_person(String foto_person) {
        this.foto_person = foto_person;
    }

    public String getBio_person() {
        return bio_person;
    }

    public void setBio_person(String bio_person) {
        this.bio_person = bio_person;
    }

    public String getLevel_person() {
        return level_person;
    }

    public void setLevel_person(String level_person) {
        this.level_person = level_person;
    }

    public String getFile_person() {
        return file_person;
    }

    public void setFile_person(String file_person) {
        this.file_person = file_person;
    }
}
