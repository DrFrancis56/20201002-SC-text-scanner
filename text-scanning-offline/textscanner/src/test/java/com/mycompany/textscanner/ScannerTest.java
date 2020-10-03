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
public class ScannerTest {

    public ScannerTest() {
    }

    /**
     * Test of main method, of class Scanner.
     */
    @Test
    public void testMainArgsException() {
        System.out.println("main simple exception argument tests");
        String[] args = null;
        try {
            Scanner.main(args);
            fail("expecting an exception for no args");
        } catch (Exception ex) {
            // ok
        }
        args = new String[]{"-h"};
        try {
            Scanner.main(args);
            fail("expecting an exception for -h");
        } catch (Exception ex) {
            // ok
        }
        args = new String[]{"a", "b"};
        try {
            Scanner.main(args);
            fail("expecting an exception for too many args");
        } catch (Exception ex) {
            // ok
        }
        args = new String[]{"./someinput.file"};
        try {
            Scanner.main(args);
            fail("expecting an exception for nonexistent file");
        } catch (Exception ex) {
            // ok
        }
    }

    @Test
    public void testMainArgsOk() {
        System.out.println("main simple correct argument test");
        String file = "../input/singleChunk";
        try {
            Scanner.main(new String[]{file});
        } catch (Exception e) {
            fail("Not expecting an exception " + e.getMessage());
        }
    }

}
