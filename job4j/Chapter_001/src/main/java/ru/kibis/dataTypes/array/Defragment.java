package ru.kibis.dataTypes.array;

import java.util.Arrays;

public class Defragment {
    public static String[] compress(String[] array) {
        for (int w = 0; w < array.length - 1; w++) {
            boolean isSorted = false;
            String buf = null;
            while (!isSorted) {
                isSorted = true;
                for (int i = 0; i < array.length - 1; i++) {
                    if (array[i] == null) {
                        isSorted = true;
                        buf = array[i];
                        array[i] = array[i + 1];
                        array[i + 1] = buf;

                    }
                }
            }
        }
        return array;
    }


    public static void main (String[]args){
        String[] input = {"I", null, "wanna", null, "be", null, "compressed"};
        String[] compressed = compress(input);
        System.out.println();
        for (int index = 0; index < compressed.length; index++) {
            System.out.print(compressed[index] + " ");
        }
    }
}
