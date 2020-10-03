/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.textscanner;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author francis
 */
public class ScannedLineSetTest {

    public ScannedLineSetTest() {
    }

    @Test
    public void testGetDigit() {
        ScannedLineSet s = new ScannedLineSet("111222333", "444555666", "777888999");

        try {
            assertEquals("111444777", s.getDigitKey(1));
            assertEquals("222555888", s.getDigitKey(2));
            assertEquals("333666999", s.getDigitKey(3));
        } catch (Exception ex) {
            Logger.getLogger(ScannedLineSetTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testGetDigitFailures() {
        ScannedLineSet s = new ScannedLineSet("111222333", "444555666", "777888999");

        try {
            s.getDigitKey(0);
            fail("Digit 0");
        } catch (Exception e) {
            //ok
        }
        try {
            s.getDigitKey(4);
            fail("Digit 4");
        } catch (Exception e) {
            //ok
        }
        try {
            s.getDigitKey(-1);
            fail("Digit -1");
        } catch (Exception e) {
            //ok
        }
    }
}
