package com.mortenjust.wearslideshow;

import java.util.List;

/**
 * Created by mortenjust on 1/21/16.
 */
public class SlideStore {
    private static SlideStore instance = null;
    List<SlideItemFragment> allItems = null;

        static SlideImage[] allImages = {
                // image id, scroll position, description
                new SlideImage(R.drawable.mock01, 0, "01"),
                new SlideImage(R.drawable.mock02, 0, "02"),
                new SlideImage(R.drawable.mock03, 0, "03"),
                new SlideImage(R.drawable.mock04, 0, "04"),
                new SlideImage(R.drawable.mock05, 0, "05"),
                new SlideImage(R.drawable.mock06, 0, "06"),
                new SlideImage(R.drawable.mock07, 0, "07"),
                new SlideImage(R.drawable.mock08, 0, "08"),
                new SlideImage(R.drawable.mock09, 0, "09"),
                new SlideImage(R.drawable.mock10, 0, "10"),
                new SlideImage(R.drawable.mock11, 0, "11"),
                new SlideImage(R.drawable.mock12, 0, "12"),
                new SlideImage(R.drawable.mock13, 0, "13"),
                new SlideImage(R.drawable.mock14, 0, "14"),
                new SlideImage(R.drawable.mock15, 0, "15"),
                new SlideImage(R.drawable.mock16, 0, "16"),
                new SlideImage(R.drawable.mock17, 0, "17"),
                new SlideImage(R.drawable.mock18, 0, "18"),
                new SlideImage(R.drawable.mock19, 0, "19"),
                new SlideImage(R.drawable.mock20, 0, "20")
        };

    protected SlideStore(){}

    public static SlideStore getInstance() {
        if(instance == null){
            instance = new SlideStore();
        }
        return instance;
    }
}
