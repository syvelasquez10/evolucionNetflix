// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import android.os.Parcel;
import android.os.Parcelable$Creator;

final class Request$ParcelFileDescriptorWithMimeType$1 implements Parcelable$Creator<Request$ParcelFileDescriptorWithMimeType>
{
    public Request$ParcelFileDescriptorWithMimeType createFromParcel(final Parcel parcel) {
        return new Request$ParcelFileDescriptorWithMimeType(parcel, null);
    }
    
    public Request$ParcelFileDescriptorWithMimeType[] newArray(final int n) {
        return new Request$ParcelFileDescriptorWithMimeType[n];
    }
}
