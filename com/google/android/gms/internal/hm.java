// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.List;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Result;
import android.os.Parcel;
import android.accounts.Account;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class hm
{
    public static class a implements SafeParcelable
    {
        public static final hn CREATOR;
        final int BR;
        public final Account Cj;
        
        static {
            CREATOR = new hn();
        }
        
        public a() {
            this(null);
        }
        
        a(final int br, final Account cj) {
            this.BR = br;
            this.Cj = cj;
        }
        
        public a(final Account account) {
            this(1, account);
        }
        
        public int describeContents() {
            final hn creator = a.CREATOR;
            return 0;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final hn creator = a.CREATOR;
            hn.a(this, parcel, n);
        }
    }
    
    public static class b implements Result, SafeParcelable
    {
        public static final ho CREATOR;
        final int BR;
        public Status Ck;
        public List<hs> Cl;
        
        static {
            CREATOR = new ho();
        }
        
        public b() {
            this.BR = 1;
        }
        
        b(final int br, final Status ck, final List<hs> cl) {
            this.BR = br;
            this.Ck = ck;
            this.Cl = cl;
        }
        
        public int describeContents() {
            final ho creator = b.CREATOR;
            return 0;
        }
        
        @Override
        public Status getStatus() {
            return this.Ck;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final ho creator = b.CREATOR;
            ho.a(this, parcel, n);
        }
    }
}
