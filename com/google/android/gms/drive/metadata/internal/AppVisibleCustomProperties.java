// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.metadata.internal;

import java.util.HashMap;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import java.util.Map;
import android.os.Parcel;
import java.util.Iterator;
import java.util.ArrayList;
import com.google.android.gms.common.internal.n;
import java.util.Collection;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class AppVisibleCustomProperties implements SafeParcelable, Iterable<CustomProperty>
{
    public static final Parcelable$Creator<AppVisibleCustomProperties> CREATOR;
    public static final AppVisibleCustomProperties Py;
    final int BR;
    final List<CustomProperty> Pz;
    
    static {
        CREATOR = (Parcelable$Creator)new com.google.android.gms.drive.metadata.internal.a();
        Py = new a().im();
    }
    
    AppVisibleCustomProperties(final int br, final Collection<CustomProperty> collection) {
        this.BR = br;
        n.i(collection);
        this.Pz = new ArrayList<CustomProperty>(collection);
    }
    
    private AppVisibleCustomProperties(final Collection<CustomProperty> collection) {
        this(1, collection);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public Iterator<CustomProperty> iterator() {
        return this.Pz.iterator();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        com.google.android.gms.drive.metadata.internal.a.a(this, parcel, n);
    }
    
    public static class a
    {
        private final Map<CustomPropertyKey, CustomProperty> PA;
        
        public a() {
            this.PA = new HashMap<CustomPropertyKey, CustomProperty>();
        }
        
        public AppVisibleCustomProperties im() {
            return new AppVisibleCustomProperties(this.PA.values(), null);
        }
    }
}
