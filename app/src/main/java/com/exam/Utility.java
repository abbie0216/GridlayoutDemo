package com.exam;

import android.graphics.Color;

import java.util.Random;

public class Utility {
    public static int getRandomColor(){
        Random random = new Random();
        return Color.argb(100, random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    public static int getRandomNum(int min, int max){
        Random random = new Random();
        int pick = random.nextInt(max - min + 1) + min;
        return pick;
    }
}
