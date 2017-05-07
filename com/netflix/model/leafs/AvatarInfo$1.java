// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import android.os.Parcel;
import android.os.Parcelable$Creator;

final class AvatarInfo$1 implements Parcelable$Creator<AvatarInfo>
{
    public AvatarInfo createFromParcel(final Parcel parcel) {
        return new AvatarInfo(parcel);
    }
    
    public AvatarInfo[] newArray(final int n) {
        return new AvatarInfo[n];
    }
}
