// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

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
}
