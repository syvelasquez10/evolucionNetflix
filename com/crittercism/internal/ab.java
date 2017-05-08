// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

public final class ab
{
    char[] a;
    int b;
    
    public ab(final int n) {
        this.a = new char[n];
    }
    
    private static boolean a(final char c) {
        return c == ' ' || c == '\t' || c == '\r' || c == '\n';
    }
    
    public final String a(final int n) {
        if (n > this.b) {
            throw new IndexOutOfBoundsException("endIndex: " + n + " > length: " + this.b);
        }
        if (n < 0) {
            throw new IndexOutOfBoundsException("beginIndex: 0 > endIndex: " + n);
        }
        int n2 = 0;
        int n3;
        while (true) {
            n3 = n;
            if (n2 >= n) {
                break;
            }
            n3 = n;
            if (!a(this.a[n2])) {
                break;
            }
            ++n2;
        }
        while (n3 > n2 && a(this.a[n3 - 1])) {
            --n3;
        }
        return new String(this.a, n2, n3 - n2);
    }
    
    @Override
    public final String toString() {
        return new String(this.a, 0, this.b);
    }
}
