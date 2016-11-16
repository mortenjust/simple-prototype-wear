package com.mortenjust.wearslideshow;

import android.net.Uri;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.util.Log;

import java.io.File;

public class MainActivity extends WearableActivity implements SlideItemFragment.OnFragmentInteractionListener {
    String TAG = "mj.maina";

    private BoxInsetLayout mContainerView;

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.d("nonsense", "here");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAmbientEnabled();
        mContainerView = (BoxInsetLayout) findViewById(R.id.container);

    }

    public void traverse (File dir) {
        Log.d(TAG, "traverse: ");
        if (dir.exists()) {
            Log.d(TAG, "traverse: exists");
            File[] files = dir.listFiles();
            for (int i = 0; i < files.length; ++i) {
                File file = files[i];
                if (file.isDirectory()) {
                    traverse(file);
                } else {
                    Log.d(TAG, "traverse: "+file.getAbsolutePath());
                    // do something here with the file
                }
            }
        }
    }


    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        super.onEnterAmbient(ambientDetails);
        updateDisplay();
    }

    @Override
    public void onUpdateAmbient() {
        super.onUpdateAmbient();
        updateDisplay();
    }

    @Override
    public void onExitAmbient() {
        updateDisplay();
        super.onExitAmbient();
    }

    private void updateDisplay() {
        if (isAmbient()) {
        } else {
        }
    }
}
