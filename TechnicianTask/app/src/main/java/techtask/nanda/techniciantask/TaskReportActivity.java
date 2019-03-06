package techtask.nanda.techniciantask;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
/*import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;*/
import io.realm.Realm;
import techtask.nanda.techniciantask.fragment.TaskReport.TaskReportDataDisabledFragment;
import techtask.nanda.techniciantask.fragment.TaskReport.TaskReportDataFragment;
import techtask.nanda.techniciantask.model.report.ReportImageTeknisi;

public class TaskReportActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;

    ArrayList<String> filePaths = new ArrayList<String>();
    public static ArrayList<ReportImageTeknisi> reportImages = new ArrayList<>();
    private static final String TAG = "myd_taskdetailact";
    public Realm realm;

    public String id_task = "";
    public boolean isPending;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        initToolbar();
        init();
        initRealm();
    }

    private void initToolbar() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    public void toolbarMoveRight(){
        toolbar.setPadding(200, 0, 0, 0);
    }

    public void setToolbarTitle(String title){
        toolbarTitle.setText(title);
    }

    private void init() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id_task = extras.getString("id_task");
            isPending = extras.getBoolean("is_pending");
        }

        Log.d(TAG, "== Task Report Activity ==");
        Log.d(TAG, "Id Task Pegerjaan: " + id_task);
        Log.d(TAG, "Is Pending: " + isPending);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if(isPending) {
            ft.replace(R.id.frame_content, new TaskReportDataFragment());
        }else {
            ft.replace(R.id.frame_content, new TaskReportDataDisabledFragment());
        }

        ft.commit();
    }

    private void initRealm() {
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "report image: Request Code: " + requestCode);
        Log.d(TAG, "report image: Result Code: " + resultCode);
    }

    @Override
    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }
}
