package techtask.nanda.techniciantask.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class TasksPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentTitleList = new ArrayList<>();
    private String TAG = "myd_taskpager";

    public TasksPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        /*Fragment fragment = null;
        if(position == 1){
            fragment = new TaskPengerjaanDoneFragment();
        }else if(position == 2){
            fragment = new TaskPengerjaanPendingFragment();
        }

        return fragment;*/

        Log.d(TAG, "getItem position: " + position);
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addFragment(Fragment fragment, String title) {
        fragmentList.add(fragment);
        fragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Log.d(TAG, "Page title position: " + position);
        return fragmentTitleList.get(position);
    }
}
