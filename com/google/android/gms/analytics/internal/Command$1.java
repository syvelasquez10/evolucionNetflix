// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;

final class Command$1 implements Parcelable$Creator<Command>
{
    @Deprecated
    public Command[] zzab(final int n) {
        return new Command[n];
    }
    
    @Deprecated
    public Command zzr(final Parcel parcel) {
        return new Command(parcel);
    }
}
