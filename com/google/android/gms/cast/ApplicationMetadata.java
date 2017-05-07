// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import android.os.Parcel;
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
    private final int kg;
    String kh;
    List<WebImage> ki;
    List<String> kj;
    String kk;
    Uri kl;
    String mName;
    
    static {
        CREATOR = (Parcelable$Creator)new a();
    }
    
    private ApplicationMetadata() {
        this.kg = 1;
        this.ki = new ArrayList<WebImage>();
        this.kj = new ArrayList<String>();
    }
    
    ApplicationMetadata(final int kg, final String kh, final String mName, final List<WebImage> ki, final List<String> kj, final String kk, final Uri kl) {
        this.kg = kg;
        this.kh = kh;
        this.mName = mName;
        this.ki = ki;
        this.kj = kj;
        this.kk = kk;
        this.kl = kl;
    }
    
    public Uri aN() {
        return this.kl;
    }
    
    public boolean areNamespacesSupported(final List<String> list) {
        return this.kj != null && this.kj.containsAll(list);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getApplicationId() {
        return this.kh;
    }
    
    public List<WebImage> getImages() {
        return this.ki;
    }
    
    public String getName() {
        return this.mName;
    }
    
    public String getSenderAppIdentifier() {
        return this.kk;
    }
    
    int getVersionCode() {
        return this.kg;
    }
    
    public boolean isNamespaceSupported(final String s) {
        return this.kj != null && this.kj.contains(s);
    }
    
    @Override
    public String toString() {
        return this.mName;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        a.a(this, parcel, n);
    }
}
