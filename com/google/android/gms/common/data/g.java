// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

import java.util.ArrayList;

public abstract class g<T> extends DataBuffer<T>
{
    private boolean Ki;
    private ArrayList<Integer> Kj;
    
    protected g(final DataHolder dataHolder) {
        super(dataHolder);
        this.Ki = false;
    }
    
    private void gF() {
        while (true) {
            while (true) {
                int ar = 0;
                Label_0145: {
                    synchronized (this) {
                        if (!this.Ki) {
                            final int count = this.IC.getCount();
                            this.Kj = new ArrayList<Integer>();
                            if (count > 0) {
                                this.Kj.add(0);
                                final String ge = this.gE();
                                ar = this.IC.ar(0);
                                final String c = this.IC.c(ge, 0, ar);
                                ar = 1;
                                if (ar < count) {
                                    if (!this.IC.c(ge, ar, this.IC.ar(ar)).equals(c)) {
                                        this.Kj.add(ar);
                                    }
                                    break Label_0145;
                                }
                            }
                            this.Ki = true;
                        }
                        return;
                    }
                }
                ++ar;
                continue;
            }
        }
    }
    
    int au(final int n) {
        if (n < 0 || n >= this.Kj.size()) {
            throw new IllegalArgumentException("Position " + n + " is out of bounds for this buffer");
        }
        return this.Kj.get(n);
    }
    
    protected int av(int au) {
        int n;
        if (au < 0 || au == this.Kj.size()) {
            n = 0;
        }
        else {
            int n2;
            if (au == this.Kj.size() - 1) {
                n2 = this.IC.getCount() - this.Kj.get(au);
            }
            else {
                n2 = this.Kj.get(au + 1) - this.Kj.get(au);
            }
            n = n2;
            if (n2 == 1) {
                au = this.au(au);
                final int ar = this.IC.ar(au);
                final String gg = this.gG();
                n = n2;
                if (gg != null) {
                    n = n2;
                    if (this.IC.c(gg, au, ar) == null) {
                        return 0;
                    }
                }
            }
        }
        return n;
    }
    
    protected abstract T f(final int p0, final int p1);
    
    protected abstract String gE();
    
    protected String gG() {
        return null;
    }
    
    @Override
    public final T get(final int n) {
        this.gF();
        return this.f(this.au(n), this.av(n));
    }
    
    @Override
    public int getCount() {
        this.gF();
        return this.Kj.size();
    }
}
