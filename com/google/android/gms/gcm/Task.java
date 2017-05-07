// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.gcm;

import android.os.Parcel;
import android.os.Parcelable;

public abstract class Task implements Parcelable
{
    private final String adq;
    private final boolean adr;
    private final boolean ads;
    private final String mTag;
    
    Task() {
        this.adq = null;
        this.mTag = null;
        this.adr = false;
        this.ads = false;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getServiceName() {
        return this.adq;
    }
    
    public String getTag() {
        return this.mTag;
    }
    
    public boolean isPersisted() {
        return this.ads;
    }
    
    public boolean isUpdateCurrent() {
        return this.adr;
    }
    
    public void writeToParcel(final Parcel parcel, int n) {
        final int n2 = 1;
        parcel.writeString(this.adq);
        parcel.writeString(this.mTag);
        if (this.adr) {
            n = 1;
        }
        else {
            n = 0;
        }
        parcel.writeInt(n);
        if (this.ads) {
            n = n2;
        }
        else {
            n = 0;
        }
        parcel.writeInt(n);
    }
}
