// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;

final class BinderWrapper$1 implements Parcelable$Creator<BinderWrapper>
{
    public BinderWrapper zzV(final Parcel parcel) {
        return new BinderWrapper(parcel, null);
    }
    
    public BinderWrapper[] zzbq(final int n) {
        return new BinderWrapper[n];
    }
}
