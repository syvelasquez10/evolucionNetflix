// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public class AvatarInfo implements Parcelable
{
    public static final Parcelable$Creator<AvatarInfo> CREATOR;
    private boolean bIsInDefaultSet;
    private String name;
    private String url;
    
    static {
        CREATOR = (Parcelable$Creator)new AvatarInfo$1();
    }
    
    protected AvatarInfo(final Parcel parcel) {
        final String[] array = new String[3];
        parcel.readStringArray(array);
        this.name = array[0];
        this.url = array[1];
        this.bIsInDefaultSet = Boolean.valueOf(array[2]);
    }
    
    public AvatarInfo(final String name, final String url, final boolean bIsInDefaultSet) {
        this.name = name;
        this.url = url;
        this.bIsInDefaultSet = bIsInDefaultSet;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = false;
        boolean b2;
        if (this == o) {
            b2 = true;
        }
        else {
            b2 = b;
            if (o != null) {
                b2 = b;
                if (this.getClass() == o.getClass()) {
                    final AvatarInfo avatarInfo = (AvatarInfo)o;
                    if (this.name != null || avatarInfo.getName() != null) {
                        b2 = b;
                        if (!this.name.equals(avatarInfo.getName())) {
                            return b2;
                        }
                    }
                    return true;
                }
            }
        }
        return b2;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public boolean isInDefaultSet() {
        return this.bIsInDefaultSet;
    }
    
    @Override
    public String toString() {
        return new StringBuffer("Name: ").append(this.name).append("; url: ").append(this.url).append("; isInDefaultSet: ").append(this.bIsInDefaultSet).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeStringArray(new String[] { this.name, this.url, String.valueOf(this.bIsInDefaultSet) });
    }
}
