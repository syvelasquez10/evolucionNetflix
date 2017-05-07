// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public class Command implements Parcelable
{
    @Deprecated
    public static final Parcelable$Creator<Command> CREATOR;
    private String mValue;
    private String zzMI;
    private String zzwj;
    
    static {
        CREATOR = (Parcelable$Creator)new Command$1();
    }
    
    public Command() {
    }
    
    Command(final Parcel parcel) {
        this.readFromParcel(parcel);
    }
    
    @Deprecated
    private void readFromParcel(final Parcel parcel) {
        this.zzwj = parcel.readString();
        this.zzMI = parcel.readString();
        this.mValue = parcel.readString();
    }
    
    @Deprecated
    public int describeContents() {
        return 0;
    }
    
    public String getId() {
        return this.zzwj;
    }
    
    public String getValue() {
        return this.mValue;
    }
    
    @Deprecated
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeString(this.zzwj);
        parcel.writeString(this.zzMI);
        parcel.writeString(this.mValue);
    }
}
