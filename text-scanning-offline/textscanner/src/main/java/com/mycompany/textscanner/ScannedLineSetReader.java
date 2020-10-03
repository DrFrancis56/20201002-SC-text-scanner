/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.textscanner;

import java.io.LineNumberReader;

/**
 *
 * @author francis
 */
class ScannedLineSetReader {

    // marker indicating we've reached end of file
    boolean atEndOfFile = false;
    final LineNumberReader lnr;
    final Scanner scanner;

    ScannedLineSetReader(Scanner scanner, LineNumberReader lnr) {
        this.lnr = lnr;
        this.scanner = scanner;
    }

    ScannedLineSet read() throws Exception {
        // if we have already reached the end of file and there is no more input return null
        if (atEndOfFile) {
            return null;
        }
        // read and validate the raw data
        String[] theLines = new String[scanner.NumberOfScanLinesPerSet];
        for (int i = 0; i < scanner.NumberOfScanLinesPerSet; i++) {
            String line = lnr.readLine();
            if (line == null) {
                atEndOfFile = true;
                if (i == 0) {
                    // valid - no more chunks to read case
                    return null;
                } else {
                    reportFileInputError("Empty input line");
                }
            }
            if (line.length() != scanner.ScanLineLength) {
                reportFileInputError("incorrect line length '" + line + "'");
            }
            theLines[i] = line;
        }
        // finally check and clear the separating line
        // nb the input file has illegal separatr consisting not of an empty line but a space
        // so add the ability to allow loose testing of this separator
        String separator = lnr.readLine();
        if (separator == null) {
            atEndOfFile = true;
        } else if (!((scanner.looseSeparator && scanner.SeparatorLine.equals(separator.trim()))
                || scanner.SeparatorLine.equals(separator))) {
            reportFileInputError("incorrect separating line '" + separator + "'");
        }
        return new ScannedLineSet(theLines);
    }

    void reportFileInputError(String s) throws Exception {
        // stop reading a badly formed file and report the error
        atEndOfFile = true;
        String m = "Malformed input - " + s;
        System.err.println(m);
        throw new Exception(m);
    }

}
