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

public class MapView extends FrameLayout
{
    private GoogleMap aiG;
    private final MapView$b aiJ;
    
    public MapView(final Context context) {
        super(context);
        this.aiJ = new MapView$b((ViewGroup)this, context, null);
    }
    
    public MapView(final Context context, final AttributeSet set) {
        super(context, set);
        this.aiJ = new MapView$b((ViewGroup)this, context, GoogleMapOptions.createFromAttributes(context, set));
    }
    
    public MapView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.aiJ = new MapView$b((ViewGroup)this, context, GoogleMapOptions.createFromAttributes(context, set));
    }
    
    public MapView(final Context context, final GoogleMapOptions googleMapOptions) {
        super(context);
        this.aiJ = new MapView$b((ViewGroup)this, context, googleMapOptions);
    }
    
    public final GoogleMap getMap() {
        if (this.aiG != null) {
            return this.aiG;
        }
        this.aiJ.my();
        if (this.aiJ.it() == null) {
            return null;
        }
        try {
            return this.aiG = new GoogleMap(this.aiJ.it().mz().getMap());
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final void onCreate(final Bundle bundle) {
        this.aiJ.onCreate(bundle);
        if (this.aiJ.it() == null) {
            final MapView$b aiJ = this.aiJ;
            a.b(this);
        }
    }
    
    public final void onDestroy() {
        this.aiJ.onDestroy();
    }
    
    public final void onLowMemory() {
        this.aiJ.onLowMemory();
    }
    
    public final void onPause() {
        this.aiJ.onPause();
    }
    
    public final void onResume() {
        this.aiJ.onResume();
    }
    
    public final void onSaveInstanceState(final Bundle bundle) {
        this.aiJ.onSaveInstanceState(bundle);
    }
}
