// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import java.util.BitSet;
import android.accounts.Account;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class he implements SafeParcelable
{
    public static final hf CREATOR;
    final int BR;
    final hi[] BS;
    public final String BT;
    public final boolean BU;
    public final Account account;
    
    static {
        CREATOR = new hf();
    }
    
    he(final int br, final hi[] bs, final String bt, final boolean bu, final Account account) {
        this.BR = br;
        this.BS = bs;
        this.BT = bt;
        this.BU = bu;
        this.account = account;
    }
    
    he(final String s, final boolean b, final Account account, final hi... array) {
        this(1, array, s, b, account);
        final BitSet set = new BitSet(hp.fm());
        for (int i = 0; i < array.length; ++i) {
            final int cg = array[i].Cg;
            if (cg != -1) {
                if (set.get(cg)) {
                    throw new IllegalArgumentException("Duplicate global search section type " + hp.O(cg));
                }
                set.set(cg);
            }
        }
    }
    
    public int describeContents() {
        final hf creator = he.CREATOR;
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final hf creator = he.CREATOR;
        hf.a(this, parcel, n);
    }
    
    public static class a
    {
        private List<hi> BV;
        private String BW;
        private boolean BX;
        private Account BY;
        
        public a D(final boolean bx) {
            this.BX = bx;
            return this;
        }
        
        public a a(final hi hi) {
            if (this.BV == null) {
                this.BV = new ArrayList<hi>();
            }
            this.BV.add(hi);
            return this;
        }
        
        public a ar(final String bw) {
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
}
