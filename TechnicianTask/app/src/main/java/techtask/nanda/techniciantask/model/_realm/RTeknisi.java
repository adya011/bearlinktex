package techtask.nanda.techniciantask.model._realm;

import io.realm.RealmObject;

/**
 * Created by ASUS on 17/12/2018.
 */

public class RTeknisi extends RealmObject {
    private String id_teknisi;
    private String id_leader;
    private String username_teknisi;
    private String nama_teknisi;
    private String password_teknisi;
    private String level_teknisi;
    private String email_teknisi;
    private String address_teknisi;
    private String phone_teknisi;
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNama_teknisi() {
        return nama_teknisi;
    }

    public void setNama_teknisi(String nama_teknisi) {
        this.nama_teknisi = nama_teknisi;
    }

    public String getEmail_teknisi() {
        return email_teknisi;
    }

    public void setEmail_teknisi(String email_teknisi) {
        this.email_teknisi = email_teknisi;
    }

    public String getAddress_teknisi() {
        return address_teknisi;
    }

    public void setAddress_teknisi(String address_teknisi) {
        this.address_teknisi = address_teknisi;
    }

    public String getPhone_teknisi() {
        return phone_teknisi;
    }

    public void setPhone_teknisi(String phone_teknisi) {
        this.phone_teknisi = phone_teknisi;
    }
    /*public RTeknisi(String id_teknisi, String id_leader, String username_teknisi, String password_teknisi, String level_teknisi) {
        this.id_teknisi = id_teknisi;
        this.id_leader = id_leader;
        this.username_teknisi = username_teknisi;
        this.password_teknisi = password_teknisi;
        this.level_teknisi = level_teknisi;
    }*/

    public String getId_teknisi() {
        return id_teknisi;
    }

    public void setId_teknisi(String id_teknisi) {
        this.id_teknisi = id_teknisi;
    }

    public String getId_leader() {
        return id_leader;
    }

    public void setId_leader(String id_leader) {
        this.id_leader = id_leader;
    }

    public String getUsername_teknisi() {
        return username_teknisi;
    }

    public void setUsername_teknisi(String username_teknisi) {
        this.username_teknisi = username_teknisi;
    }

    public String getPassword_teknisi() {
        return password_teknisi;
    }

    public void setPassword_teknisi(String password_teknisi) {
        this.password_teknisi = password_teknisi;
    }

    public String getLevel_teknisi() {
        return level_teknisi;
    }

    public void setLevel_teknisi(String level_teknisi) {
        this.level_teknisi = level_teknisi;
    }
}
