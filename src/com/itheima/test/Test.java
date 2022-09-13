package com.itheima.test;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i];
            Random r = new Random();
            int rIndex= r.nextInt(16);
            arr[i]=arr[rIndex];
            arr[rIndex]=temp;
        }
        int[][] data = new int[4][4];
        for (int i = 0; i < arr.length; i++) {
            data[i/4][i%4]=arr[i];

        }

    }
}
