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
    String mName;
    private final int xH;
    String xI;
    List<WebImage> xJ;
    List<String> xK;
    String xL;
    Uri xM;
    
    static {
        CREATOR = (Parcelable$Creator)new a();
    }
    
    private ApplicationMetadata() {
        this.xH = 1;
        this.xJ = new ArrayList<WebImage>();
        this.xK = new ArrayList<String>();
    }
    
    ApplicationMetadata(final int xh, final String xi, final String mName, final List<WebImage> xj, final List<String> xk, final String xl, final Uri xm) {
        this.xH = xh;
        this.xI = xi;
        this.mName = mName;
        this.xJ = xj;
        this.xK = xk;
        this.xL = xl;
        this.xM = xm;
    }
    
    public boolean areNamespacesSupported(final List<String> list) {
        return this.xK != null && this.xK.containsAll(list);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public Uri dz() {
        return this.xM;
    }
    
    public String getApplicationId() {
        return this.xI;
    }
    
    public List<WebImage> getImages() {
        return this.xJ;
    }
    
    public String getName() {
        return this.mName;
    }
    
    public String getSenderAppIdentifier() {
        return this.xL;
    }
    
    int getVersionCode() {
        return this.xH;
    }
    
    public boolean isNamespaceSupported(final String s) {
        return this.xK != null && this.xK.contains(s);
    }
    
    @Override
    public String toString() {
        return this.mName;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        a.a(this, parcel, n);
    }
}
