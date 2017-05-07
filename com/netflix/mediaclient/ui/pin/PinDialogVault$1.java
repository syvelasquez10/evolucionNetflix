// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.pin;

import android.os.Parcel;
import android.os.Parcelable$Creator;

final class PinDialogVault$1 implements Parcelable$Creator<PinDialogVault>
{
    public PinDialogVault createFromParcel(final Parcel parcel) {
        return new PinDialogVault(parcel);
    }
    
    public PinDialogVault[] newArray(final int n) {
        return new PinDialogVault[n];
    }
}
