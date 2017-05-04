package ru.sberbank.learning.pagerfragments;

import android.content.pm.ApplicationInfo;
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

    public static AppInfoFragment newInstance(ApplicationInfo app) {
        AppInfoFragment fragment = new AppInfoFragment();
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

    private static class AppinfoTask extends AsyncTask<Void, Void, Void> {

        private WeakReference<TextView> nameViewRef;
        private WeakReference<ImageView> iconViewRef;

        public AppinfoTask(TextView nameView, ImageView iconView) {
            nameViewRef = new WeakReference<>(nameView);
            iconViewRef = new WeakReference<>(iconView);
        }

        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
