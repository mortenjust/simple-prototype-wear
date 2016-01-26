package com.mortenjust.chatappoidssbs;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.wearable.view.FragmentGridPagerAdapter;

/**
 * Created by mortenjust on 1/21/16.
 */
public class SbsPagerAdapter extends FragmentGridPagerAdapter {
    SbsImage[] images = SbsStore.allImages;


    public SbsPagerAdapter(FragmentManager fm){
        super(fm);

    }

    @Override
    public int getColumnCount(int i) {
        return images.length;
    }

    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public Fragment getFragment(int i, int i1) {
        SbsImage image = images[i1];
        Fragment f = SbsItemFragment.newInstance(image.imageId, image.initialScrollY, image.name);
        return f;

    }


}
