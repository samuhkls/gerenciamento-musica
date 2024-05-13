package test;

import service.ImageGetter;

public class ImageTest {
    public static void main(String[] args) {
        ImageGetter getter = new ImageGetter();
        getter.getImage("redbone", "childish gambino");
    }
}
