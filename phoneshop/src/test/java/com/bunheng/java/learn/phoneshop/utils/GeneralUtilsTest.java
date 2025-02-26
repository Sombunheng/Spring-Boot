package com.bunheng.java.learn.phoneshop.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class GeneralUtilsTest {

    @Test
    public void testToIntegerList(){
        //Given valuel
        List<String> name = List.of("Dara" , "Chenda" , "Thida");

        //when
        //call function
        List<Integer> list = GeneralUtils.toIntegerList(name);

        assertEquals(4, list.get(0));
        assertEquals(6, list.get(1));
        assertEquals(5, list.get(2));

    }
}
