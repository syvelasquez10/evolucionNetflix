// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer.realtime;

import android.os.Parcel;
import android.os.Parcelable$Creator;

final class RealTimeMessage$1 implements Parcelable$Creator<RealTimeMessage>
{
    public RealTimeMessage cn(final Parcel parcel) {
        return new RealTimeMessage(parcel, null);
    }
    
    public RealTimeMessage[] dU(final int n) {
        return new RealTimeMessage[n];
    }
}
