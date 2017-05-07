// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.stream;

final class StringPool
{
    private final String[] pool;
    
    StringPool() {
        this.pool = new String[512];
    }
    
    public String get(final char[] array, final int n, final int n2) {
        final int n3 = 0;
        int i = n;
        int n4 = 0;
        while (i < n + n2) {
            n4 = n4 * 31 + array[i];
            ++i;
        }
        final int n5 = n4 >>> 20 ^ n4 >>> 12 ^ n4;
        final int n6 = this.pool.length - 1 & (n5 ^ (n5 >>> 7 ^ n5 >>> 4));
        final String s = this.pool[n6];
        if (s != null) {
            int n7 = n3;
            if (s.length() == n2) {
                Label_0134: {
                    break Label_0134;
                    do {
                        ++n7;
                        final String s2 = s;
                        if (n7 < n2) {
                            continue;
                        }
                        return s2;
                    } while (s.charAt(n7) == array[n + n7]);
                }
                return this.pool[n6] = new String(array, n, n2);
            }
        }
        final String s2 = new String(array, n, n2);
        this.pool[n6] = s2;
        return s2;
    }
}
