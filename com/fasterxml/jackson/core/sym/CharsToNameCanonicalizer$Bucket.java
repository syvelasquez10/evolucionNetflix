// 
// Decompiled by Procyon v0.5.30
// 

package com.fasterxml.jackson.core.sym;

final class CharsToNameCanonicalizer$Bucket
{
    public final int length;
    public final CharsToNameCanonicalizer$Bucket next;
    public final String symbol;
    
    public CharsToNameCanonicalizer$Bucket(final String symbol, final CharsToNameCanonicalizer$Bucket next) {
        this.symbol = symbol;
        this.next = next;
        int length;
        if (next == null) {
            length = 1;
        }
        else {
            length = next.length + 1;
        }
        this.length = length;
    }
    
    public String has(final char[] array, final int n, final int n2) {
        if (this.symbol.length() != n2) {
            return null;
        }
        int n3 = 0;
        while (this.symbol.charAt(n3) == array[n + n3]) {
            if (++n3 >= n2) {
                return this.symbol;
            }
        }
        return null;
    }
}
