// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.accounts.Account;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class hm$a implements SafeParcelable
{
    public static final hn CREATOR;
    final int BR;
    public final Account Cj;
    
    static {
        CREATOR = new hn();
    }
    
    public hm$a() {
        this(null);
    }
    
    hm$a(final int br, final Account cj) {
        this.BR = br;
        this.Cj = cj;
    }
    
    public hm$a(final Account account) {
        this(1, account);
    }
    
    public int describeContents() {
        final hn creator = hm$a.CREATOR;
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final hn creator = hm$a.CREATOR;
        hn.a(this, parcel, n);
    }
}
