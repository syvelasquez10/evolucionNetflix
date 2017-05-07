// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

import java.util.ArrayList;

public abstract class d<T> extends DataBuffer<T>
{
    private boolean BW;
    private ArrayList<Integer> BX;
    
    protected d(final DataHolder dataHolder) {
        super(dataHolder);
        this.BW = false;
    }
    
    private void eu() {
        while (true) {
            while (true) {
                int g = 0;
                Label_0145: {
                    synchronized (this) {
                        if (!this.BW) {
                            final int count = this.BB.getCount();
                            this.BX = new ArrayList<Integer>();
                            if (count > 0) {
                                this.BX.add(0);
                                final String primaryDataMarkerColumn = this.getPrimaryDataMarkerColumn();
                                g = this.BB.G(0);
                                final String string = this.BB.getString(primaryDataMarkerColumn, 0, g);
                                g = 1;
                                if (g < count) {
                                    if (!this.BB.getString(primaryDataMarkerColumn, g, this.BB.G(g)).equals(string)) {
                                        this.BX.add(g);
                                    }
                                    break Label_0145;
                                }
                            }
                            this.BW = true;
                        }
                        return;
                    }
                }
                ++g;
                continue;
            }
        }
    }
    
    int H(final int n) {
        if (n < 0 || n >= this.BX.size()) {
            throw new IllegalArgumentException("Position " + n + " is out of bounds for this buffer");
        }
        return this.BX.get(n);
    }
    
    protected int I(final int n) {
        if (n < 0 || n == this.BX.size()) {
            return 0;
        }
        if (n == this.BX.size() - 1) {
            return this.BB.getCount() - this.BX.get(n);
        }
        return this.BX.get(n + 1) - this.BX.get(n);
    }
    
    protected abstract T c(final int p0, final int p1);
    
    @Override
    public final T get(final int n) {
        this.eu();
        return this.c(this.H(n), this.I(n));
    }
    
    @Override
    public int getCount() {
        this.eu();
        return this.BX.size();
    }
    
    protected abstract String getPrimaryDataMarkerColumn();
}
