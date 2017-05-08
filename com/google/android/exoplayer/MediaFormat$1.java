// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

import android.os.Parcel;
import android.os.Parcelable$Creator;

final class MediaFormat$1 implements Parcelable$Creator<MediaFormat>
{
    public MediaFormat createFromParcel(final Parcel parcel) {
        return new MediaFormat(parcel);
    }
    
    public MediaFormat[] newArray(final int n) {
        return new MediaFormat[n];
    }
}
