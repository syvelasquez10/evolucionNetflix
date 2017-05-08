// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.org.json.zip;

import com.netflix.android.org.json.JSONException;

public class Huff implements None, PostMortem
{
    private final int domain;
    private final Huff$Symbol[] symbols;
    private Huff$Symbol table;
    private boolean upToDate;
    private int width;
    
    public Huff(final int domain) {
        int n = 0;
        this.upToDate = false;
        this.domain = domain;
        final int n2 = domain * 2 - 1;
        this.symbols = new Huff$Symbol[n2];
        int i;
        while (true) {
            i = domain;
            if (n >= domain) {
                break;
            }
            this.symbols[n] = new Huff$Symbol(n);
            ++n;
        }
        while (i < n2) {
            this.symbols[i] = new Huff$Symbol(-1);
            ++i;
        }
    }
    
    private boolean postMortem(final int n) {
        final int[] array = new int[this.domain];
        Huff$Symbol huff$Symbol = this.symbols[n];
        if (huff$Symbol.integer == n) {
            int n2 = 0;
            while (true) {
                final Huff$Symbol back = huff$Symbol.back;
                if (back == null) {
                    break;
                }
                if (back.zero == huff$Symbol) {
                    array[n2] = 0;
                }
                else {
                    if (back.one != huff$Symbol) {
                        return false;
                    }
                    array[n2] = 1;
                }
                ++n2;
                huff$Symbol = back;
            }
            if (huff$Symbol == this.table) {
                this.width = 0;
                Huff$Symbol huff$Symbol2 = this.table;
                while (huff$Symbol2.integer == -1) {
                    --n2;
                    if (array[n2] != 0) {
                        huff$Symbol2 = huff$Symbol2.one;
                    }
                    else {
                        huff$Symbol2 = huff$Symbol2.zero;
                    }
                }
                if (huff$Symbol2.integer == n && n2 == 0) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private void write(final Huff$Symbol huff$Symbol, final BitWriter bitWriter) {
        try {
            final Huff$Symbol back = huff$Symbol.back;
            if (back != null) {
                ++this.width;
                this.write(back, bitWriter);
                if (back.zero == huff$Symbol) {
                    bitWriter.zero();
                    return;
                }
                bitWriter.one();
            }
        }
        catch (Throwable t) {
            throw new JSONException(t);
        }
    }
    
    public void generate() {
        if (!this.upToDate) {
            Huff$Symbol next = this.symbols[0];
            this.table = null;
            next.next = null;
            Huff$Symbol huff$Symbol = next;
            for (int i = 1; i < this.domain; ++i) {
                final Huff$Symbol next2 = this.symbols[i];
                if (next2.weight < next.weight) {
                    next2.next = next;
                    next = next2;
                }
                else {
                    Huff$Symbol huff$Symbol2 = huff$Symbol;
                    if (next2.weight < huff$Symbol.weight) {
                        huff$Symbol2 = next;
                    }
                    Huff$Symbol next3;
                    while (true) {
                        next3 = huff$Symbol2.next;
                        if (next3 == null || next2.weight < next3.weight) {
                            break;
                        }
                        huff$Symbol2 = next3;
                    }
                    next2.next = next3;
                    huff$Symbol2.next = next2;
                    huff$Symbol = next2;
                }
            }
            int domain = this.domain;
            Huff$Symbol zero = next;
            Huff$Symbol huff$Symbol3;
            while (true) {
                final Huff$Symbol next4 = zero.next;
                final Huff$Symbol next5 = next4.next;
                huff$Symbol3 = this.symbols[domain];
                ++domain;
                huff$Symbol3.weight = zero.weight + next4.weight;
                huff$Symbol3.zero = zero;
                huff$Symbol3.one = next4;
                huff$Symbol3.back = null;
                zero.back = huff$Symbol3;
                next4.back = huff$Symbol3;
                if (next5 == null) {
                    break;
                }
                if (huff$Symbol3.weight < next5.weight) {
                    huff$Symbol3.next = next5;
                    next = huff$Symbol3;
                    zero = huff$Symbol3;
                }
                else {
                    Huff$Symbol next6 = null;
                    Label_0263: {
                        break Label_0263;
                        do {
                            next = next6;
                            next6 = next.next;
                        } while (next6 != null && huff$Symbol3.weight >= next6.weight);
                    }
                    huff$Symbol3.next = next6;
                    next.next = huff$Symbol3;
                    next = huff$Symbol3;
                    zero = next5;
                }
            }
            this.table = huff$Symbol3;
            this.upToDate = true;
        }
    }
    
    @Override
    public boolean postMortem(final PostMortem postMortem) {
        for (int i = 0; i < this.domain; ++i) {
            if (!this.postMortem(i)) {
                JSONzip.log("\nBad huff ");
                JSONzip.logchar(i, i);
                return false;
            }
        }
        return this.table.postMortem(((Huff)postMortem).table);
    }
    
    public int read(final BitReader bitReader) {
        try {
            this.width = 0;
            Huff$Symbol huff$Symbol = this.table;
            while (huff$Symbol.integer == -1) {
                ++this.width;
                if (bitReader.bit()) {
                    huff$Symbol = huff$Symbol.one;
                }
                else {
                    huff$Symbol = huff$Symbol.zero;
                }
            }
            this.tick(huff$Symbol.integer);
            return huff$Symbol.integer;
        }
        catch (Throwable t) {
            throw new JSONException(t);
        }
    }
    
    public void tick(final int n) {
        final Huff$Symbol huff$Symbol = this.symbols[n];
        ++huff$Symbol.weight;
        this.upToDate = false;
    }
    
    public void tick(int i, final int n) {
        while (i <= n) {
            this.tick(i);
            ++i;
        }
    }
    
    public void write(final int n, final BitWriter bitWriter) {
        this.width = 0;
        this.write(this.symbols[n], bitWriter);
        this.tick(n);
    }
}
