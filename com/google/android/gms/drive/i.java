// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import com.google.android.gms.common.internal.n;
import android.os.Parcel;
import android.os.Parcelable;

public abstract class i implements Parcelable
{
    private transient volatile boolean ND;
    
    public i() {
        this.ND = false;
    }
    
    protected abstract void I(final Parcel p0, final int p1);
    
    public final boolean hT() {
        return this.ND;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        n.I(!this.hT());
        this.ND = true;
        this.I(parcel, n);
    }
}
