package com.mortenjust.chatappoidssbs;

/**
 * Created by mortenjust on 1/21/16.
 */
public class SbsImage {
    int imageId;
    int initialScrollY = 0;
    String name;

    public SbsImage(int id, int y, String name){
        this.imageId = id;
        this.initialScrollY = y;
        this.name = name;
    }
}
