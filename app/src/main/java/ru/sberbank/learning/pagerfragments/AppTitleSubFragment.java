package ru.sberbank.learning.pagerfragments;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * Created by user10 on 04.05.2017.
 */

public class AppTitleSubFragment extends Fragment {

    private static final String ARG_APPLICATION = "application";
    private TextView packageNameView;

    public static AppTitleSubFragment newInstance(ApplicationInfo app) {
        AppTitleSubFragment fragment = new AppTitleSubFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_APPLICATION, app);

        fragment.setArguments(args);
        return fragment;
    }

    private  ApplicationInfo applicationInfo;
    private ImageView applicationIcon;
    private TextView applicationName;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applicationInfo = getArguments().getParcelable(ARG_APPLICATION);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.app_title_fragment,
                container, false);

        applicationIcon = (ImageView) root.findViewById(R.id.application_icon);
        applicationName = (TextView) root.findViewById(R.id.application_title);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        AppInfoTask task = new AppInfoTask(applicationInfo, applicationName, applicationIcon);
        task.execute();
    }

    private static class AppInfoTask extends AsyncTask<Void, Void, AppInfo> {

        private WeakReference<TextView> nameViewRef;
        private WeakReference<ImageView> iconViewRef;
        private PackageManager packageManager;
        private ApplicationInfo applicationInfo;

        public AppInfoTask(ApplicationInfo appInfo,
                           TextView nameView, ImageView iconView) {
            applicationInfo = appInfo;
            nameViewRef = new WeakReference<>(nameView);
            iconViewRef = new WeakReference<>(iconView);
            packageManager = iconView.getContext().getPackageManager();
        }

        @Override
        protected AppInfo doInBackground(Void... params) {
            AppInfo info = new AppInfo();
            info.name = packageManager.getApplicationLabel(applicationInfo).toString();
            info.icon = packageManager.getApplicationIcon(applicationInfo);
            return info;
        }

        @Override
        protected void onPostExecute(AppInfo result) {
            ImageView iconView = iconViewRef.get();
            TextView titleView = nameViewRef.get();

            if (iconView != null && titleView != null) {
                iconView.setImageDrawable(result.icon);
                titleView.setText(result.name);
            }
        }
    }

    private static final class AppInfo {
        private String name;
        private Drawable icon;
    }
}
