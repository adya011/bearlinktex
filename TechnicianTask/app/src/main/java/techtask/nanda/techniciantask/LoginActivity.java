package techtask.nanda.techniciantask;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import techtask.nanda.techniciantask.fragment.FrontFragment;
import techtask.nanda.techniciantask.fragment.LoginFragment;

// Activity login dengan menampilkan fragment > LoginFragment
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_content, new FrontFragment());
        ft.commit();
    }
}
