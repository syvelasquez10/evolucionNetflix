// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class hk implements SafeParcelable
{
    public static final hl CREATOR;
    static final long OF;
    private final hg OG;
    private final long Oc;
    private final int mPriority;
    final int xH;
    
    static {
        CREATOR = new hl();
        OF = TimeUnit.HOURS.toMillis(1L);
    }
    
    public hk(final int xh, final hg og, final long oc, final int mPriority) {
        this.xH = xh;
        this.OG = og;
        this.Oc = oc;
        this.mPriority = mPriority;
    }
    
    public int describeContents() {
        final hl creator = hk.CREATOR;
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof hk)) {
                return false;
            }
            final hk hk = (hk)o;
            if (!this.OG.equals(hk.OG) || this.Oc != hk.Oc || this.mPriority != hk.mPriority) {
                return false;
            }
        }
        return true;
    }
    
    public long getInterval() {
        return this.Oc;
    }
    
    public int getPriority() {
        return this.mPriority;
    }
    
    public hg hZ() {
        return this.OG;
    }
    
    @Override
    public int hashCode() {
        return fo.hashCode(this.OG, this.Oc, this.mPriority);
    }
    
    @Override
    public String toString() {
        return fo.e(this).a("filter", this.OG).a("interval", this.Oc).a("priority", this.mPriority).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final hl creator = hk.CREATOR;
        hl.a(this, parcel, n);
    }
}
