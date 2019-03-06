package techtask.nanda.techniciantask.utils;

import io.realm.Realm;
import io.realm.RealmResults;
import techtask.nanda.techniciantask.model._realm.RTaskReportPhoto;
import techtask.nanda.techniciantask.model._realm.RTeknisi;

/**
 * Created by nandana.samudera on 12/01/2019.
 */

public class RealmHelper {
    Realm realm;

    public RealmHelper(Realm realm){
        this.realm = realm;
    }

    public String getUserPassword() {
        RealmResults<RTeknisi> results = realm.where(RTeknisi.class).findAll();
        String password = null;

        for (RTeknisi teknisi : results) {
            password = results.get(0).getPassword_teknisi();
        }

        return password;
    }

    public void updateUserPassword(String password){
        RealmResults<RTeknisi> results = realm.where(RTeknisi.class)
                .equalTo("id_teknisi", "11").findAll();

        realm.beginTransaction();

        for(RTeknisi teknisi: results){
            teknisi.setPassword_teknisi(password);
        }

        realm.commitTransaction();

        realm.close();
    }
}
