// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

import java.util.ArrayList;

public abstract class d<T> extends DataBuffer<T>
{
    private boolean nZ;
    private ArrayList<Integer> oa;
    
    protected d(final DataHolder dataHolder) {
        super(dataHolder);
        this.nZ = false;
    }
    
    private int E(final int n) {
        if (n < 0 || n == this.oa.size()) {
            return 0;
        }
        if (n == this.oa.size() - 1) {
            return this.nE.getCount() - this.oa.get(n);
        }
        return this.oa.get(n + 1) - this.oa.get(n);
    }
    
    private void by() {
        while (true) {
            while (true) {
                int c = 0;
                Label_0145: {
                    synchronized (this) {
                        if (!this.nZ) {
                            final int count = this.nE.getCount();
                            this.oa = new ArrayList<Integer>();
                            if (count > 0) {
                                this.oa.add(0);
                                final String primaryDataMarkerColumn = this.getPrimaryDataMarkerColumn();
                                c = this.nE.C(0);
                                final String string = this.nE.getString(primaryDataMarkerColumn, 0, c);
                                c = 1;
                                if (c < count) {
                                    if (!this.nE.getString(primaryDataMarkerColumn, c, this.nE.C(c)).equals(string)) {
                                        this.oa.add(c);
                                    }
                                    break Label_0145;
                                }
                            }
                            this.nZ = true;
                        }
                        return;
                    }
                }
                ++c;
                continue;
            }
        }
    }
    
    int D(final int n) {
        if (n < 0 || n >= this.oa.size()) {
            throw new IllegalArgumentException("Position " + n + " is out of bounds for this buffer");
        }
        return this.oa.get(n);
    }
    
    protected abstract T a(final int p0, final int p1);
    
    @Override
    public final T get(final int n) {
        this.by();
        return this.a(this.D(n), this.E(n));
    }
    
    @Override
    public int getCount() {
        this.by();
        return this.oa.size();
    }
    
    protected abstract String getPrimaryDataMarkerColumn();
}
