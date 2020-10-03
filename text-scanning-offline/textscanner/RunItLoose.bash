#!/bin/bash

java -DUseTrimInSeparatorEquivalence=true -classpath ./target/textscanner-1.0-SNAPSHOT.jar com.mycompany.textscanner.Scanner $1
