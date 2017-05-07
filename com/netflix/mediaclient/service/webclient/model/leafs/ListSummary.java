// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import android.os.Parcel;

public class ListSummary
{
    private int length;
    
    protected ListSummary(final int length) {
        this.length = length;
    }
    
    protected ListSummary(final Parcel parcel) {
        this.length = parcel.readInt();
    }
    
    public int getLength() {
        return this.length;
    }
    
    public void setLength(final int length) {
        this.length = length;
    }
    
    protected void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeInt(this.length);
    }
}
