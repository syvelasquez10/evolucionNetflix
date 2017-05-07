// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import com.google.android.gms.dynamic.a;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.FrameLayout;

public class StreetViewPanoramaView extends FrameLayout
{
    private StreetViewPanorama aiW;
    private final StreetViewPanoramaView$a ajf;
    
    public StreetViewPanoramaView(final Context context) {
        super(context);
        this.ajf = new StreetViewPanoramaView$a((ViewGroup)this, context, null);
    }
    
    public StreetViewPanoramaView(final Context context, final AttributeSet set) {
        super(context, set);
        this.ajf = new StreetViewPanoramaView$a((ViewGroup)this, context, null);
    }
    
    public StreetViewPanoramaView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.ajf = new StreetViewPanoramaView$a((ViewGroup)this, context, null);
    }
    
    public StreetViewPanoramaView(final Context context, final StreetViewPanoramaOptions streetViewPanoramaOptions) {
        super(context);
        this.ajf = new StreetViewPanoramaView$a((ViewGroup)this, context, streetViewPanoramaOptions);
    }
    
    public final StreetViewPanorama getStreetViewPanorama() {
        if (this.aiW != null) {
            return this.aiW;
        }
        this.ajf.my();
        if (this.ajf.it() == null) {
            return null;
        }
        try {
            return this.aiW = new StreetViewPanorama(this.ajf.it().mF().getStreetViewPanorama());
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final void onCreate(final Bundle bundle) {
        this.ajf.onCreate(bundle);
        if (this.ajf.it() == null) {
            final StreetViewPanoramaView$a ajf = this.ajf;
            a.b(this);
        }
    }
    
    public final void onDestroy() {
        this.ajf.onDestroy();
    }
    
    public final void onLowMemory() {
        this.ajf.onLowMemory();
    }
    
    public final void onPause() {
        this.ajf.onPause();
    }
    
    public final void onResume() {
        this.ajf.onResume();
    }
    
    public final void onSaveInstanceState(final Bundle bundle) {
        this.ajf.onSaveInstanceState(bundle);
    }
}
