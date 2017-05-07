// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.ArrayList;

public class aq
{
    private static boolean a(final Character.UnicodeBlock unicodeBlock) {
        return unicodeBlock == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || unicodeBlock == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || unicodeBlock == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B || unicodeBlock == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || unicodeBlock == Character.UnicodeBlock.CJK_RADICALS_SUPPLEMENT || unicodeBlock == Character.UnicodeBlock.CJK_COMPATIBILITY || unicodeBlock == Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS || unicodeBlock == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || unicodeBlock == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT || unicodeBlock == Character.UnicodeBlock.BOPOMOFO || unicodeBlock == Character.UnicodeBlock.HIRAGANA || unicodeBlock == Character.UnicodeBlock.KATAKANA || unicodeBlock == Character.UnicodeBlock.HANGUL_SYLLABLES || unicodeBlock == Character.UnicodeBlock.HANGUL_JAMO;
    }
    
    static boolean d(final int n) {
        return Character.isLetter(n) && a(Character.UnicodeBlock.of(n));
    }
    
    public static int o(final String s) {
        return kb.a(s.getBytes(), 0, s.length(), 0);
    }
    
    public static String[] p(final String s) {
        if (s == null) {
            return null;
        }
        final ArrayList<String> list = new ArrayList<String>();
        final char[] charArray = s.toCharArray();
        final int length = s.length();
        int n = 0;
        int n2 = 0;
        int i;
        int n6;
        for (i = 0; i < length; i = n6) {
            final int codePoint = Character.codePointAt(charArray, i);
            final int charCount = Character.charCount(codePoint);
            int n3;
            int n4;
            if (d(codePoint)) {
                if (n != 0) {
                    list.add(new String(charArray, n2, i - n2));
                }
                list.add(new String(charArray, i, charCount));
                final boolean b = false;
                n3 = n2;
                n4 = (b ? 1 : 0);
            }
            else if (Character.isLetterOrDigit(codePoint)) {
                if (n == 0) {
                    n2 = i;
                }
                n3 = n2;
                n4 = 1;
            }
            else if (n != 0) {
                list.add(new String(charArray, n2, i - n2));
                n3 = n2;
                n4 = 0;
            }
            else {
                final int n5 = n2;
                n4 = n;
                n3 = n5;
            }
            n6 = i + charCount;
            final int n7 = n3;
            n = n4;
            n2 = n7;
        }
        if (n != 0) {
            list.add(new String(charArray, n2, i - n2));
        }
        return list.toArray(new String[list.size()]);
    }
}
