package com.example.shoppingecommerce;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 * MEVLÜT CAN SILACI 19.05.2024 16:31:05
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @BeforeClass
    public static void beforeClass(){
        System.out.println("Her test sınıfı için öncesi");
    }

    @AfterClass
    public static void afterClass(){
        System.out.println("Her test sınıfı için sonrası");
    }

    @Before
    public void setup() throws Exception{
        System.out.println("Metod öncesi");
    }

    @Test
    public void testHelloWorld() throws Exception{
        System.out.println("Hello World Deneme-1");
    }
   }