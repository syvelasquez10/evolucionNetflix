// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import android.os.Bundle;
import com.google.android.gms.dynamic.d;
import android.os.IInterface;

public interface IStreetViewPanoramaViewDelegate extends IInterface
{
    IStreetViewPanoramaDelegate getStreetViewPanorama();
    
    d getView();
    
    void onCreate(final Bundle p0);
    
    void onDestroy();
    
    void onLowMemory();
    
    void onPause();
    
    void onResume();
    
    void onSaveInstanceState(final Bundle p0);
}
