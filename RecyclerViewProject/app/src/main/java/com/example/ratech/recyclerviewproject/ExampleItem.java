package com.example.ratech.recyclerviewproject;

public class ExampleItem {

    private int mImageRessource;
    private String mText1;
    private String mText2;

    public ExampleItem(int imageRessource, String text1, String text2) {
        mImageRessource = imageRessource;
        mText1 = text1;
        mText2 = text2;
    }

    public void changedText1(String text) {
        mText1 = text;
    }

    public int getImageRessource() {
        return mImageRessource;
    }

    public String getText1() {
        return mText1;
    }

    public String getText2() {
        return mText2;
    }

}
