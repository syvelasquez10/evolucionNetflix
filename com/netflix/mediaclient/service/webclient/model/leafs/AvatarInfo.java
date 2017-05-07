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
    private String name;
    private String url;
    
    static {
        CREATOR = (Parcelable$Creator)new AvatarInfo$1();
    }
    
    protected AvatarInfo(final Parcel parcel) {
        final String[] array = new String[2];
        parcel.readStringArray(array);
        this.name = array[0];
        this.url = array[1];
    }
    
    public AvatarInfo(final String name, final String url) {
        this.name = name;
        this.url = url;
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
    
    @Override
    public String toString() {
        return new StringBuffer("Name: ").append(this.name).append("; url: ").append(this.url).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeStringArray(new String[] { this.name, this.url });
    }
}
