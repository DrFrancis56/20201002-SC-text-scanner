/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.textscanner;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author francis
 */
public class Dictionary {

    Map<String, String> dictionary = new HashMap<>();

    static final String UnknownMapping = "?";

    Dictionary() {
        init();
    }

    String lookup(String k) {
        String m = dictionary.get(k);
        return m == null ? UnknownMapping : m;
    }

    final void init() {
        dictionary.put("._.|.||_|".replace('.', ' '), "0");
        dictionary.put(".....|..|".replace('.', ' '), "1");
        dictionary.put("._.._||_.".replace('.', ' '), "2");
        dictionary.put("._.._|._|".replace('.', ' '), "3");
        dictionary.put("...|_|..|".replace('.', ' '), "4");
        dictionary.put("._.|_.._|".replace('.', ' '), "5");
        dictionary.put("._.|_.|_|".replace('.', ' '), "6");
        dictionary.put("._...|..|".replace('.', ' '), "7");
        dictionary.put("._.|_||_|".replace('.', ' '), "8");
        dictionary.put("._.|_|._|".replace('.', ' '), "9");
    }

}
