/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.textscanner;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 *
 * @author francis
 */
public class Scanner {

    final int NumberOfScanLinesPerSet = 3;
    final String SeparatorLine = "";
    final int ScanLineLength;
    final int nDigits;
    boolean looseSeparator;

    Scanner() {
        this(9);
    }

    Scanner(int nDigits) {
        this.nDigits = nDigits;
        this.ScanLineLength = nDigits * 3;
        looseSeparator = Boolean.getBoolean("UseTrimInSeparatorEquivalence");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // simple main file as entry point
        new Scanner().run(args);
    }

    // method returning the desired output
    void run(String[] args) throws Exception {
        // expect single arg which is the file name
        if (args == null || args.length != 1 || "-h".equals(args[0])) {
            System.err.println("incorrect args");
            System.err.println("Usage: Scanner FilePathName");
            throw new Exception("Usage");
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            System.err.println("Input file does not exist");
            throw new Exception("unknown input file");
        }
        // create
        try (BufferedInputStream is = new BufferedInputStream(new FileInputStream(file))) {
            process(is);
        }
    }

    void process(InputStream is) throws Exception {
        Dictionary dictionary = new Dictionary();
        // Iterate over the input file
        // create RawScannedLineSet validating the input formats
        // process RawScannedLineSet into a String of Digits and print
        try (LineNumberReader lnr = new LineNumberReader(new InputStreamReader(is))) {
            ScannedLineSet scanLines = null;
            ScannedLineSetReader reader = new ScannedLineSetReader(this, lnr);
            while ((scanLines = reader.read()) != null) {
                StringBuilder sb = new StringBuilder();
                boolean isLegal = true;
                for (int i = 1; i <= nDigits; i++) {
                    String key = scanLines.getDigitKey(i);
                    String digit = dictionary.lookup(key);
                    isLegal = isLegal && !dictionary.UnknownMapping.equals(digit);
                    sb.append(digit);
                }
                if (!isLegal) {
                    sb.append("ILL");
                }
                String finalString = sb.toString();
                System.out.println(finalString);
            }
        }
    }

}
