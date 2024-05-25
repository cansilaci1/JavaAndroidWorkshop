package com.example.lab6;
//MEVLÜT CAN SILACI 21181616060
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void testTopla() {
        assertEquals(4, 2 + 2);
        ExampleUnitTest exampleUnitTest = new ExampleUnitTest();

        int sayi1 = 3;
        int sayi2 = 19;

        int topla = sayi1 + sayi2;

        int toplam = topla;
        int cikarma = sayi2 - sayi1;
        int carpim = sayi2 * sayi1;
        int bolme = sayi2 / sayi1;

        Assert.assertEquals(23, toplam);
        Assert.assertEquals(23, cikarma);
        Assert.assertEquals(23, carpim);
        Assert.assertEquals(23, bolme);
    }
    @Test
    public void testCikar() {
        assertEquals(4, 2 + 2);
        ExampleUnitTest exampleUnitTest = new ExampleUnitTest();

        int sayi1 = 3;
        int sayi2 = 19;
        int cikarma = sayi2 - sayi1;

        Assert.assertEquals(23, cikarma);

    }
    @Test
    public void testCarpim() {
        assertEquals(4, 2 + 2);
        ExampleUnitTest exampleUnitTest = new ExampleUnitTest();

        int sayi1 = 3;
        int sayi2 = 19;
        int carpim = sayi2 * sayi1;

        Assert.assertEquals(23, carpim);
    }
    @Test
    public void testBolme() {
        assertEquals(4, 2 + 2);
        ExampleUnitTest exampleUnitTest = new ExampleUnitTest();

        int sayi1 = 3;
        int sayi2 = 19;
        int bolme = sayi2 / sayi1;

        Assert.assertEquals(23, bolme);
    }
}