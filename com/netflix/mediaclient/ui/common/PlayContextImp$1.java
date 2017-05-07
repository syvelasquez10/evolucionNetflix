// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.os.Parcel;
import android.os.Parcelable$Creator;

final class PlayContextImp$1 implements Parcelable$Creator<PlayContextImp>
{
    public PlayContextImp createFromParcel(final Parcel parcel) {
        return new PlayContextImp(parcel);
    }
    
    public PlayContextImp[] newArray(final int n) {
        return new PlayContextImp[n];
    }
}
