// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.dash.mpd;

import java.util.Locale;

public final class UrlTemplate
{
    private final int identifierCount;
    private final String[] identifierFormatTags;
    private final int[] identifiers;
    private final String[] urlPieces;
    
    private UrlTemplate(final String[] urlPieces, final int[] identifiers, final String[] identifierFormatTags, final int identifierCount) {
        this.urlPieces = urlPieces;
        this.identifiers = identifiers;
        this.identifierFormatTags = identifierFormatTags;
        this.identifierCount = identifierCount;
    }
    
    public static UrlTemplate compile(final String s) {
        final String[] array = new String[5];
        final int[] array2 = new int[4];
        final String[] array3 = new String[4];
        return new UrlTemplate(array, array2, array3, parseTemplate(s, array, array2, array3));
    }
    
    private static int parseTemplate(final String s, final String[] array, final int[] array2, final String[] array3) {
        array[0] = "";
        int n = 0;
        int i = 0;
        while (i < s.length()) {
            final int index = s.indexOf("$", i);
            if (index == -1) {
                array[n] += s.substring(i);
                i = s.length();
            }
            else if (index != i) {
                array[n] += s.substring(i, index);
                i = index;
            }
            else if (s.startsWith("$$", i)) {
                array[n] += "$";
                i += 2;
            }
            else {
                final int index2 = s.indexOf("$", i + 1);
                final String substring = s.substring(i + 1, index2);
                if (substring.equals("RepresentationID")) {
                    array2[n] = 1;
                }
                else {
                    final int index3 = substring.indexOf("%0");
                    String s2 = "%01d";
                    String substring2 = substring;
                    if (index3 != -1) {
                        final String s3 = s2 = substring.substring(index3);
                        if (!s3.endsWith("d")) {
                            s2 = s3 + "d";
                        }
                        substring2 = substring.substring(0, index3);
                    }
                    if (substring2.equals("Number")) {
                        array2[n] = 2;
                    }
                    else if (substring2.equals("Bandwidth")) {
                        array2[n] = 3;
                    }
                    else {
                        if (!substring2.equals("Time")) {
                            throw new IllegalArgumentException("Invalid template: " + s);
                        }
                        array2[n] = 4;
                    }
                    array3[n] = s2;
                }
                ++n;
                array[n] = "";
                i = index2 + 1;
            }
        }
        return n;
    }
    
    public String buildUri(final String s, final int n, final int n2, final long n3) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.identifierCount; ++i) {
            sb.append(this.urlPieces[i]);
            if (this.identifiers[i] == 1) {
                sb.append(s);
            }
            else if (this.identifiers[i] == 2) {
                sb.append(String.format(Locale.US, this.identifierFormatTags[i], n));
            }
            else if (this.identifiers[i] == 3) {
                sb.append(String.format(Locale.US, this.identifierFormatTags[i], n2));
            }
            else if (this.identifiers[i] == 4) {
                sb.append(String.format(Locale.US, this.identifierFormatTags[i], n3));
            }
        }
        sb.append(this.urlPieces[this.identifierCount]);
        return sb.toString();
    }
}
