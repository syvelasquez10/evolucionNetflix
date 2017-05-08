// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.org.json.zip;

class Huff$Symbol implements PostMortem
{
    public Huff$Symbol back;
    public final int integer;
    public Huff$Symbol next;
    public Huff$Symbol one;
    public long weight;
    public Huff$Symbol zero;
    
    public Huff$Symbol(final int integer) {
        this.integer = integer;
        this.weight = 0L;
        this.next = null;
        this.back = null;
        this.one = null;
        this.zero = null;
    }
    
    @Override
    public boolean postMortem(final PostMortem postMortem) {
        final Huff$Symbol huff$Symbol = (Huff$Symbol)postMortem;
        if (this.integer == huff$Symbol.integer && this.weight == huff$Symbol.weight) {
            int n;
            if (this.back != null) {
                n = 1;
            }
            else {
                n = 0;
            }
            int n2;
            if (huff$Symbol.back != null) {
                n2 = 1;
            }
            else {
                n2 = 0;
            }
            if (n == n2) {
                final Huff$Symbol zero = this.zero;
                final Huff$Symbol one = this.one;
                boolean b;
                if (zero == null) {
                    if (huff$Symbol.zero != null) {
                        return false;
                    }
                    b = true;
                }
                else {
                    b = zero.postMortem(huff$Symbol.zero);
                }
                if (one == null) {
                    if (huff$Symbol.one != null) {
                        return false;
                    }
                }
                else {
                    b = one.postMortem(huff$Symbol.one);
                }
                return b;
            }
        }
        return false;
    }
}
