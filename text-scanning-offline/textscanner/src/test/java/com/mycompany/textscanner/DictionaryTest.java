/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.textscanner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author francis
 */
public class DictionaryTest {

    public DictionaryTest() {
    }

    @Test
    public void testLookup() throws Exception {
        // use the input from the description to create a scanned line set
        // augmented with 0
        ScannedLineSet set = new ScannedLineSet(
                "...._.._....._.._.._.._.._.._.".replace('.', ' '),
                "..|._|._||_||_.|_...||_||_||.|".replace('.', ' '),
                "..||_.._|..|._||_|..||_|._||_|".replace('.', ' '));

        Dictionary dictionary = new Dictionary();
        for (int i = 1; i <= 9; i++) {
            String k = set.getDigitKey(i);
            String l = dictionary.lookup(k);
            System.out.println(k + ":" + l);
            assertEquals("" + i, l);
        }
        assertEquals("0", dictionary.lookup(set.getDigitKey(10)));

    }

    public void testUnknownLookup() {
        Dictionary dictionary = new Dictionary();
        for (String k : new String[]{"", "xxxxx"}) {
            assertEquals("?", dictionary.lookup(k), "Expecting unknown lookup for " + k);
        }
    }

}
