package techtask.nanda.techniciantask.base;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import butterknife.ButterKnife;
import io.realm.Realm;
import techtask.nanda.techniciantask.R;
import techtask.nanda.techniciantask.api.TaskApi;
import techtask.nanda.techniciantask.utils.MyUtils;
import techtask.nanda.techniciantask.utils.SharedPrefManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {
    protected SharedPrefManager sharedPrefManager;
    protected MyUtils utils;
    protected TaskApi taskApi;
    protected Realm realm;
    protected ProgressBar progressBarDialog;

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPrefManager = new SharedPrefManager(getContext());
        utils = new MyUtils();
        taskApi = new TaskApi(getActivity(), getContext(), utils);
        progressBarDialog = new ProgressBar(getActivity(), null, android.R.attr.progressBarStyleLarge);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_base, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initRealmDb();
    }



    protected void initRealmDb(){
        realm = Realm.getDefaultInstance();
    }
}
