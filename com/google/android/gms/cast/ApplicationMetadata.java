// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.internal.ik;
import java.util.Collection;
import java.util.ArrayList;
import android.net.Uri;
import com.google.android.gms.common.images.WebImage;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class ApplicationMetadata implements SafeParcelable
{
    public static final Parcelable$Creator<ApplicationMetadata> CREATOR;
    private final int BR;
    List<WebImage> EA;
    List<String> EB;
    String EC;
    Uri ED;
    String Ez;
    String mName;
    
    static {
        CREATOR = (Parcelable$Creator)new a();
    }
    
    private ApplicationMetadata() {
        this.BR = 1;
        this.EA = new ArrayList<WebImage>();
        this.EB = new ArrayList<String>();
    }
    
    ApplicationMetadata(final int br, final String ez, final String mName, final List<WebImage> ea, final List<String> eb, final String ec, final Uri ed) {
        this.BR = br;
        this.Ez = ez;
        this.mName = mName;
        this.EA = ea;
        this.EB = eb;
        this.EC = ec;
        this.ED = ed;
    }
    
    public boolean areNamespacesSupported(final List<String> list) {
        return this.EB != null && this.EB.containsAll(list);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof ApplicationMetadata)) {
                return false;
            }
            final ApplicationMetadata applicationMetadata = (ApplicationMetadata)o;
            if (!ik.a(this.Ez, applicationMetadata.Ez) || !ik.a(this.EA, applicationMetadata.EA) || !ik.a(this.mName, applicationMetadata.mName) || !ik.a(this.EB, applicationMetadata.EB) || !ik.a(this.EC, applicationMetadata.EC) || !ik.a(this.ED, applicationMetadata.ED)) {
                return false;
            }
        }
        return true;
    }
    
    public Uri fv() {
        return this.ED;
    }
    
    public String getApplicationId() {
        return this.Ez;
    }
    
    public List<WebImage> getImages() {
        return this.EA;
    }
    
    public String getName() {
        return this.mName;
    }
    
    public String getSenderAppIdentifier() {
        return this.EC;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.BR, this.Ez, this.mName, this.EA, this.EB, this.EC, this.ED);
    }
    
    public boolean isNamespaceSupported(final String s) {
        return this.EB != null && this.EB.contains(s);
    }
    
    @Override
    public String toString() {
        return this.mName;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        a.a(this, parcel, n);
    }
}
