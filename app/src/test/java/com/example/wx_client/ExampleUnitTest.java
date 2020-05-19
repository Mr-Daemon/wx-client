package com.example.wx_client;

import org.junit.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void test() {
        String str= "\\u4f60\\u597d";
        String str2=new String(str.getBytes(), StandardCharsets.UTF_8);
        System.out.println(str2);
    }
}