// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class mm implements SafeParcelable
{
    public static final mn CREATOR;
    static final long afp;
    final int BR;
    private final long aeh;
    private final mi afq;
    private final int mPriority;
    
    static {
        CREATOR = new mn();
        afp = TimeUnit.HOURS.toMillis(1L);
    }
    
    public mm(final int br, final mi afq, final long aeh, final int mPriority) {
        this.BR = br;
        this.afq = afq;
        this.aeh = aeh;
        this.mPriority = mPriority;
    }
    
    public int describeContents() {
        final mn creator = mm.CREATOR;
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof mm)) {
                return false;
            }
            final mm mm = (mm)o;
            if (!m.equal(this.afq, mm.afq) || this.aeh != mm.aeh || this.mPriority != mm.mPriority) {
                return false;
            }
        }
        return true;
    }
    
    public long getInterval() {
        return this.aeh;
    }
    
    public int getPriority() {
        return this.mPriority;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.afq, this.aeh, this.mPriority);
    }
    
    public mi mf() {
        return this.afq;
    }
    
    @Override
    public String toString() {
        return m.h(this).a("filter", this.afq).a("interval", this.aeh).a("priority", this.mPriority).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final mn creator = mm.CREATOR;
        mn.a(this, parcel, n);
    }
}
