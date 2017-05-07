// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import android.location.Location;

public interface LocationSource
{
    void activate(final OnLocationChangedListener p0);
    
    void deactivate();
    
    public interface OnLocationChangedListener
    {
        void onLocationChanged(final Location p0);
    }
}
