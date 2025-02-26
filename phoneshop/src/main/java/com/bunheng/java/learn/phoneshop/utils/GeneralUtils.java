package com.bunheng.java.learn.phoneshop.utils;

import java.util.List;

public class GeneralUtils {
    // convert list of string to list of list of integer
    // we have ["data" , "Thida" , "Seyha"]
    // convert to [4 , 5, 5]

    public static List<Integer> toIntegerList(List<String> list){
        return list.stream()
        .map(s -> s.length())
        .toList();
    }
}
