package com.firebase.samples.logindemo.utils;

/**
 * Created by arms on 7/8/16.
 */
public class ConvertUtils {

    public static Integer[] toObject(int[] intArray) {

        Integer[] result = new Integer[intArray.length];
        for (int i = 0; i < intArray.length; i++) {
            result[i] = Integer.valueOf(intArray[i]);
        }
        return result;
    }
}
