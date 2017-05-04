package ru.sberbank.learning.pagerfragments;

import android.content.pm.ApplicationInfo;
import android.os.Bundle;
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

    private  ApplicationInfo applicationInfo;

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
        return root;
    }
}
