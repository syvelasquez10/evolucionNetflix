// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import com.google.android.gms.maps.model.internal.a;
import com.google.android.gms.maps.StreetViewPanoramaOptions;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.dynamic.d;
import android.os.IInterface;

public interface c extends IInterface
{
    IMapViewDelegate a(final d p0, final GoogleMapOptions p1);
    
    IStreetViewPanoramaViewDelegate a(final d p0, final StreetViewPanoramaOptions p1);
    
    void a(final d p0, final int p1);
    
    void i(final d p0);
    
    IMapFragmentDelegate j(final d p0);
    
    IStreetViewPanoramaFragmentDelegate k(final d p0);
    
    ICameraUpdateFactoryDelegate mG();
    
    a mH();
}
