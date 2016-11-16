package com.mortenjust.wearslideshow;

/**
 * Created by mortenjust on 1/21/16.
 */
public class SlideImage {
    int imageId;
    int initialScrollY = 0;
    String name;

    public SlideImage(int id, int y, String name){
        this.imageId = id;
        this.initialScrollY = y;
        this.name = name;
    }
}
