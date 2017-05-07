// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import com.google.android.gms.maps.model.VisibleRegion;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.dynamic.d;
import android.os.IInterface;

public interface IProjectionDelegate extends IInterface
{
    LatLng fromScreenLocation(final d p0);
    
    VisibleRegion getVisibleRegion();
    
    d toScreenLocation(final LatLng p0);
}
