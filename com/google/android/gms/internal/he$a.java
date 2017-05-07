// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.ArrayList;
import android.accounts.Account;
import java.util.List;

public class he$a
{
    private List<hi> BV;
    private String BW;
    private boolean BX;
    private Account BY;
    
    public he$a D(final boolean bx) {
        this.BX = bx;
        return this;
    }
    
    public he$a a(final hi hi) {
        if (this.BV == null) {
            this.BV = new ArrayList<hi>();
        }
        this.BV.add(hi);
        return this;
    }
    
    public he$a ar(final String bw) {
        this.BW = bw;
        return this;
    }
    
    public he fk() {
        final String bw = this.BW;
        final boolean bx = this.BX;
        final Account by = this.BY;
        hi[] array;
        if (this.BV != null) {
            array = this.BV.toArray(new hi[this.BV.size()]);
        }
        else {
            array = null;
        }
        return new he(bw, bx, by, array);
    }
}
