/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.textscanner;

/**
 *
 * @author francis
 */
class ScannedLineSet {

    // class is not fool proof balancing validation of this class and state with use and visibility
    // ie assume checking that initiates/creates this class is already complete
    private final String[] lines;

    ScannedLineSet(String... theLines) {
        lines = theLines;
    }

    String getDigitKey(int n) throws Exception {
        int startIndex = (n - 1) * 3;
        int endIndex = n * 3;
        if (startIndex < 0 || endIndex > lines[0].length()) {
            throw new Exception("Digit '" + n + "' outside bounds of scanned lines - " + lines[0].length() + "/3");
        }
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            sb.append(line.substring(startIndex, endIndex));
        }
        return sb.toString();
    }

}
