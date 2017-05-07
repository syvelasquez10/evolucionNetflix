// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import com.google.android.gms.maps.StreetViewPanoramaOptions;
import com.google.android.gms.dynamic.d;
import android.os.Bundle;
import android.os.IInterface;

public interface IStreetViewPanoramaFragmentDelegate extends IInterface
{
    IStreetViewPanoramaDelegate getStreetViewPanorama();
    
    boolean isReady();
    
    void onCreate(final Bundle p0);
    
    d onCreateView(final d p0, final d p1, final Bundle p2);
    
    void onDestroy();
    
    void onDestroyView();
    
    void onInflate(final d p0, final StreetViewPanoramaOptions p1, final Bundle p2);
    
    void onLowMemory();
    
    void onPause();
    
    void onResume();
    
    void onSaveInstanceState(final Bundle p0);
}
