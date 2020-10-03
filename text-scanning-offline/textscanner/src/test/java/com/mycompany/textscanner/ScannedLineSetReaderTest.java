/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.textscanner;

import java.io.LineNumberReader;
import java.io.StringReader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author francis
 */
public class ScannedLineSetReaderTest {

    public ScannedLineSetReaderTest() {
    }

    @Test
    public void testRawScanReadOk() {
        // test with a reduced but valid line/character length
        Scanner scanner = new Scanner(1);
        String[] lineSets = new String[]{
            // simple single set
            "...\n"
            + "|||\n"
            + "___\n",
            // multiple set with separator at end too
            "...\n"
            + "|||\n"
            + "___\n"
            + "\n"
            + "___\n"
            + "|||\n"
            + "...\n"
            + "\n"
        };
        int[] numberSets = new int[]{1, 2};

        for (int i = 0; i < lineSets.length; i++) {
            String lines = lineSets[i];
            int number = numberSets[i];
            System.out.println("Testing with " + lines);
            try (LineNumberReader lnr = new LineNumberReader(new StringReader(lines))) {
                ScannedLineSetReader reader = new ScannedLineSetReader(scanner, lnr);
                ScannedLineSet set;
                int count = 0;
                while ((set = reader.read()) != null) {
                    assertTrue(set != null, "Empty set");
                    count++;
                };
                assertEquals(number, count, "Wrong number of sets read");
                assertEquals(true, reader.atEndOfFile, "End of input");
            } catch (Exception e) {
                fail("Unexpected Exception with '" + lines + "'", e);
            }
        }
    }

    @Test
    public void testLineLengthErrors() {
        runFailTest("...]\n---\n[[[\n", "Line length error on first line");
        runFailTest("...\n--|-\n[[[\n", "Line length error on 2nd line");
        runFailTest("...\n---\n[[[[[\n", "Line length error on last line");
    }

    void runFailTest(String lines, String message) {
        Scanner scanner = new Scanner(1);
        try (LineNumberReader lnr = new LineNumberReader(new StringReader(lines))) {
            ScannedLineSetReader reader = new ScannedLineSetReader(scanner, lnr);
            try {
                reader.read();
                fail(message);
            } catch (Exception e) {
                // ok
            }
        } catch (Exception ee) {
            fail("Unexpected Exception with '" + lines + "'");
        }
    }

    @Test
    public void testLlineNumberErrors() {
        runFailTest("...\n---\n[[[\naaa\n", "Too many lines");
        runFailTest("...\n---\n", "Not enough lines");
    }

    @Test
    public void testRawScanReadOkLoose() {
        // test with a reduced but valid line/character length
        Scanner scanner = new Scanner(1);
        scanner.looseSeparator = true;
        String[] lineSets = new String[]{
            // simple single set
            "...\n"
            + "|||\n"
            + "___\n",
            // multiple set with separator at end too
            "...\n"
            + "|||\n"
            + "___\n"
            + " \n"
            + "___\n"
            + "|||\n"
            + "...\n"
            + "\t\n"
        };
        int[] numberSets = new int[]{1, 2};

        for (int i = 0; i < lineSets.length; i++) {
            String lines = lineSets[i];
            int number = numberSets[i];
            System.out.println("Testing with " + lines);
            try (LineNumberReader lnr = new LineNumberReader(new StringReader(lines))) {
                ScannedLineSetReader reader = new ScannedLineSetReader(scanner, lnr);
                ScannedLineSet set;
                int count = 0;
                while ((set = reader.read()) != null) {
                    assertTrue(set != null, "Empty set");
                    count++;
                };
                assertEquals(number, count, "Wrong number of sets read");
                assertEquals(true, reader.atEndOfFile, "End of input");
            } catch (Exception e) {
                fail("Unexpected Exception with '" + lines + "'", e);
            }
        }
    }

}
