package com.example.hellofx3;

import javafx.scene.image.Image;

public class TestCards {
    public static void testcard() {
        Cards test = new Cards.Defend();
        Image img = test.getCardImage();
        if (img != null && !img.isError()) {
            System.out.println("Image loaded successfully");
        } else {
            System.out.println("Failed to load image");
        }
    }

    public static void main(String[] args) {
        testcard();
    }
}

