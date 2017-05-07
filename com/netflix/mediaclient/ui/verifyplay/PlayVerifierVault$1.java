// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.verifyplay;

import android.os.Parcel;
import android.os.Parcelable$Creator;

final class PlayVerifierVault$1 implements Parcelable$Creator<PlayVerifierVault>
{
    public PlayVerifierVault createFromParcel(final Parcel parcel) {
        return new PlayVerifierVault(parcel);
    }
    
    public PlayVerifierVault[] newArray(final int n) {
        return new PlayVerifierVault[n];
    }
}
