// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.pin;

import com.netflix.mediaclient.util.ParcelUtils;
import android.os.Parcel;
import com.netflix.mediaclient.ui.Asset;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public class PinDialogVault implements Parcelable
{
    public static final Parcelable$Creator<PinDialogVault> CREATOR;
    public static final String NAME;
    private Asset mAsset;
    private String mInvokeLocation;
    private boolean mRemotePlayback;
    private String mUuid;
    
    static {
        NAME = PinDialogVault.class.getSimpleName();
        CREATOR = (Parcelable$Creator)new PinDialogVault$1();
    }
    
    PinDialogVault() {
    }
    
    PinDialogVault(final Parcel parcel) {
        this.mInvokeLocation = ParcelUtils.readString(parcel);
        this.mUuid = ParcelUtils.readString(parcel);
        this.mRemotePlayback = ParcelUtils.readBoolean(parcel);
        this.mAsset = (Asset)parcel.readParcelable(Asset.class.getClassLoader());
    }
    
    public PinDialogVault(final String s) {
        this(s, null, false, null);
    }
    
    public PinDialogVault(final String s, final Asset asset, final boolean b) {
        this(s, asset, b, null);
    }
    
    PinDialogVault(final String mInvokeLocation, final Asset mAsset, final boolean mRemotePlayback, final String mUuid) {
        this.mInvokeLocation = mInvokeLocation;
        this.mAsset = mAsset;
        this.mRemotePlayback = mRemotePlayback;
        this.mUuid = mUuid;
    }
    
    public PinDialogVault(final String s, final String s2) {
        this(s, null, false, s2);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public Asset getAsset() {
        return this.mAsset;
    }
    
    public String getInvokeLocation() {
        return this.mInvokeLocation;
    }
    
    public String getUuid() {
        return this.mUuid;
    }
    
    public boolean isRemotePlayback() {
        return this.mRemotePlayback;
    }
    
    @Override
    public String toString() {
        return "PinDialogVault [mInvokeLocation=" + this.mInvokeLocation + ", mAsset=" + this.mAsset + ", mRemotePlayback=" + this.mRemotePlayback + ", mUuid=" + this.mUuid + "]";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ParcelUtils.writeString(parcel, this.mInvokeLocation);
        ParcelUtils.writeString(parcel, this.mUuid);
        ParcelUtils.writeBoolean(parcel, this.mRemotePlayback);
        parcel.writeParcelable((Parcelable)this.mAsset, n);
    }
}
