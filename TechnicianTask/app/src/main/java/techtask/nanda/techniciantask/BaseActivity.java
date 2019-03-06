package techtask.nanda.techniciantask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;
import techtask.nanda.techniciantask.model._realm.RTeknisi;

public class BaseActivity extends AppCompatActivity {
    private final static String TAG = "myd_base_act";
    public Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initRealm();
    }

    private void initRealm(){
        realm = Realm.getDefaultInstance();
    }



    @Override
    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }
}
