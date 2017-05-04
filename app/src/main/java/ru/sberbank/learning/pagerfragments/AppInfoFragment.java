package ru.sberbank.learning.pagerfragments;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by user10 on 04.05.2017.
 */

public class AppInfoFragment extends Fragment {

    private static final String ARG_APPLICATION = "application";
    private TextView packageNameView;

    public static AppInfoFragment newInstance(ApplicationInfo app) {
        AppInfoFragment fragment = new AppInfoFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_APPLICATION, app);

        fragment.setArguments(args);
        return fragment;
    }

    private ApplicationInfo applicationInfo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applicationInfo = getArguments().getParcelable(ARG_APPLICATION);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.app_info_fragment,container, false);
        packageNameView = (TextView) root.findViewById(R.id.package_name);
        packageNameView.setText(applicationInfo.packageName);

        boolean powerSave = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            PowerManager powerManager =
                    (PowerManager) getContext()
                            .getSystemService(Context.POWER_SERVICE);
            powerSave = powerManager.isPowerSaveMode();
        }
        packageNameView.setText(powerSave? "Power save" : "No power save");

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState == null) {

            AppTitleSubFragment fragment = AppTitleSubFragment
                    .newInstance(applicationInfo);

            getChildFragmentManager().beginTransaction()
                    .add(R.id.app_info_layout, fragment)
                    .commit();
        }
    }
}
