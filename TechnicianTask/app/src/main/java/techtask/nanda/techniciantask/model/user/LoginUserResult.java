package techtask.nanda.techniciantask.model.user;

import android.app.Activity;
import android.app.Application;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by nandana.samudera on 14/12/2018.
 */

public class LoginUserResult {
    public String level_member;
    public String id_person;
    public String id_member;
    public String id_teknisi;

    public LoginUserResult() {
    }

    public LoginUserResult(String level_member, String id_person, String id_member, String id_teknisi) {
        this.level_member = level_member;
        this.id_person = id_person;
        this.id_member = id_member;
        this.id_teknisi = id_teknisi;
    }

    public String getLevel_member() {
        return level_member;
    }

    public void setLevel_member(String level_member) {
        this.level_member = level_member;
    }

    public String getId_person() {
        return id_person;
    }

    public void setId_person(String id_person) {
        this.id_person = id_person;
    }

    public String getId_member() {
        return id_member;
    }

    public void setId_member(String id_member) {
        this.id_member = id_member;
    }

    public String getId_teknisi() {
        return id_teknisi;
    }

    public void setId_teknisi(String id_teknisi) {
        this.id_teknisi = id_teknisi;
    }
}
