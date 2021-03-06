package ru.sberbank.learning.pagerfragments;

import android.content.pm.ApplicationInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by user10 on 04.05.2017.
 */

public class AppsPagerAdapter extends FragmentStatePagerAdapter {

    private final List<ApplicationInfo> applicationsList;

    public AppsPagerAdapter(FragmentManager fm,
                            List<ApplicationInfo> applications) {
        super(fm);
        applicationsList = applications;
    }

    @Override
    public Fragment getItem(int position) {
        return AppInfoFragment.newInstance(applicationsList.get(position));
    }

    @Override
    public int getCount() {
        return applicationsList.size();
    }
}
