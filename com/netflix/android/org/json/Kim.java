// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.org.json;

import java.util.Arrays;

public class Kim
{
    private byte[] bytes;
    private int hashcode;
    public int length;
    private String string;
    
    public Kim(final Kim kim, final int n, final int n2) {
        this(kim.bytes, n, n2);
    }
    
    public Kim(final String s) {
        final int n = 0;
        this.bytes = null;
        this.hashcode = 0;
        this.length = 0;
        this.string = null;
        final int length = s.length();
        this.hashcode = 0;
        this.length = 0;
        if (length > 0) {
            for (int i = 0; i < length; ++i) {
                final char char1 = s.charAt(i);
                if (char1 <= '\u007f') {
                    ++this.length;
                }
                else if (char1 <= '\u3fff') {
                    this.length += 2;
                }
                else {
                    int n2 = i;
                    if (char1 >= '\ud800') {
                        n2 = i;
                        if (char1 <= '\udfff') {
                            n2 = i + 1;
                            final char char2 = s.charAt(n2);
                            if (char1 > '\udbff' || char2 < '\udc00' || char2 > '\udfff') {
                                throw new JSONException("Bad UTF16");
                            }
                        }
                    }
                    this.length += 3;
                    i = n2;
                }
            }
            this.bytes = new byte[this.length];
            int n3 = 1;
            int n4 = 0;
            int n5;
            char c2;
            for (int j = n; j < length; ++j, n3 = c2, n4 = n5) {
                final char char3 = s.charAt(j);
                if (char3 <= '\u007f') {
                    this.bytes[n4] = (byte)char3;
                    final char c = (char)(char3 + n3);
                    this.hashcode += c;
                    n5 = n4 + 1;
                    c2 = c;
                }
                else if (char3 <= '\u3fff') {
                    final char c3 = (char)(char3 >>> 7 | '\u0080');
                    this.bytes[n4] = (byte)c3;
                    final char c4 = (char)(n3 + c3);
                    this.hashcode += c4;
                    final int n6 = n4 + 1;
                    final char c5 = (char)(char3 & '\u007f');
                    this.bytes[n6] = (byte)c5;
                    c2 = (char)(c5 + c4);
                    this.hashcode += c2;
                    n5 = n6 + 1;
                }
                else {
                    int n7 = char3;
                    int n8 = j;
                    if (char3 >= '\ud800') {
                        n7 = char3;
                        n8 = j;
                        if (char3 <= '\udbff') {
                            n8 = j + 1;
                            n7 = ((char3 & '\u03ff') << 10 | (s.charAt(n8) & '\u03ff')) + 65536;
                        }
                    }
                    final int n9 = n7 >>> 14 | 0x80;
                    this.bytes[n4] = (byte)n9;
                    final char c6 = (char)(n3 + n9);
                    this.hashcode += c6;
                    final int n10 = n4 + 1;
                    final int n11 = (n7 >>> 7 & 0xFF) | 0x80;
                    this.bytes[n10] = (byte)n11;
                    final char c7 = (char)(c6 + n11);
                    this.hashcode += c7;
                    final int n12 = n10 + 1;
                    final int n13 = n7 & 0x7F;
                    this.bytes[n12] = (byte)n13;
                    c2 = (char)(n13 + c7);
                    this.hashcode += c2;
                    final int n14 = n12 + 1;
                    j = n8;
                    n5 = n14;
                }
            }
            this.hashcode += n3 << 16;
        }
    }
    
    public Kim(final byte[] array, final int n) {
        this(array, 0, n);
    }
    
    public Kim(final byte[] array, final int n, int i) {
        final int n2 = 0;
        this.bytes = null;
        this.hashcode = 0;
        this.length = 0;
        this.string = null;
        int n3 = 1;
        this.hashcode = 0;
        this.length = i - n;
        if (this.length > 0) {
            this.bytes = new byte[this.length];
            int n4;
            for (i = n2; i < this.length; ++i) {
                n4 = (array[i + n] & 0xFF);
                n3 += n4;
                this.hashcode += n3;
                this.bytes[i] = (byte)n4;
            }
            this.hashcode += n3 << 16;
        }
    }
    
    public static int characterSize(final int n) {
        if (n < 0 || n > 1114111) {
            throw new JSONException("Bad character " + n);
        }
        if (n <= 127) {
            return 1;
        }
        if (n <= 16383) {
            return 2;
        }
        return 3;
    }
    
    public int characterAt(final int n) {
        int value = this.get(n);
        if ((value & 0x80) != 0x0) {
            final int value2 = this.get(n + 1);
            if ((value2 & 0x80) == 0x0) {
                if ((value = ((value & 0x7F) << 7 | value2)) > 127) {
                    return value;
                }
            }
            else {
                final int value3 = this.get(n + 2);
                final int n2 = (value & 0x7F) << 14 | (value2 & 0x7F) << 7 | value3;
                if ((value3 & 0x80) == 0x0 && n2 > 16383 && n2 <= 1114111) {
                    if ((value = n2) < 55296) {
                        return value;
                    }
                    if (n2 > 57343) {
                        return n2;
                    }
                }
            }
            throw new JSONException("Bad character at " + n);
        }
        return value;
    }
    
    public int copy(final byte[] array, final int n) {
        System.arraycopy(this.bytes, 0, array, n, this.length);
        return this.length + n;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof Kim) {
            final Kim kim = (Kim)o;
            if (this == kim) {
                return true;
            }
            if (this.hashcode == kim.hashcode) {
                return Arrays.equals(this.bytes, kim.bytes);
            }
        }
        return false;
    }
    
    public int get(final int n) {
        if (n < 0 || n > this.length) {
            throw new JSONException("Bad character at " + n);
        }
        return this.bytes[n] & 0xFF;
    }
    
    @Override
    public int hashCode() {
        return this.hashcode;
    }
    
    @Override
    public String toString() {
        if (this.string == null) {
            final char[] array = new char[this.length];
            int i = 0;
            int n = 0;
            while (i < this.length) {
                final int character = this.characterAt(i);
                if (character < 65536) {
                    array[n] = (char)character;
                    ++n;
                }
                else {
                    array[n] = (char)(0xD800 | character - 65536 >>> 10);
                    final int n2 = n + 1;
                    array[n2] = (char)(0xDC00 | (character & 0x3FF));
                    n = n2 + 1;
                }
                i += characterSize(character);
            }
            this.string = new String(array, 0, n);
        }
        return this.string;
    }
}
