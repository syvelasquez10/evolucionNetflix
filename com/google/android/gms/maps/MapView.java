// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.internal.q;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.a;
import android.app.Activity;
import android.view.LayoutInflater;
import com.google.android.gms.dynamic.c;
import com.google.android.gms.internal.eg;
import android.view.View;
import com.google.android.gms.maps.internal.IMapViewDelegate;
import com.google.android.gms.dynamic.LifecycleDelegate;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.FrameLayout;

public class MapView extends FrameLayout
{
    private GoogleMap BU;
    private final b BY;
    
    public MapView(final Context context) {
        super(context);
        this.BY = new b((ViewGroup)this, context, null);
    }
    
    public MapView(final Context context, final AttributeSet set) {
        super(context, set);
        this.BY = new b((ViewGroup)this, context, GoogleMapOptions.createFromAttributes(context, set));
    }
    
    public MapView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.BY = new b((ViewGroup)this, context, GoogleMapOptions.createFromAttributes(context, set));
    }
    
    public MapView(final Context context, final GoogleMapOptions googleMapOptions) {
        super(context);
        this.BY = new b((ViewGroup)this, context, googleMapOptions);
    }
    
    public final GoogleMap getMap() {
        if (this.BU != null) {
            return this.BU;
        }
        this.BY.ex();
        if (this.BY.cZ() == null) {
            return null;
        }
        try {
            return this.BU = new GoogleMap(this.BY.cZ().ey().getMap());
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final void onCreate(final Bundle bundle) {
        this.BY.onCreate(bundle);
        if (this.BY.cZ() == null) {
            this.BY.a(this);
        }
    }
    
    public final void onDestroy() {
        this.BY.onDestroy();
    }
    
    public final void onLowMemory() {
        this.BY.onLowMemory();
    }
    
    public final void onPause() {
        this.BY.onPause();
    }
    
    public final void onResume() {
        this.BY.onResume();
    }
    
    public final void onSaveInstanceState(final Bundle bundle) {
        this.BY.onSaveInstanceState(bundle);
    }
    
    static class a implements LifecycleDelegate
    {
        private final ViewGroup BZ;
        private final IMapViewDelegate Ca;
        private View Cb;
        
        public a(final ViewGroup viewGroup, final IMapViewDelegate mapViewDelegate) {
            this.Ca = eg.f(mapViewDelegate);
            this.BZ = eg.f(viewGroup);
        }
        
        public IMapViewDelegate ey() {
            return this.Ca;
        }
        
        @Override
        public void onCreate(final Bundle bundle) {
            try {
                this.Ca.onCreate(bundle);
                this.Cb = c.b(this.Ca.getView());
                this.BZ.removeAllViews();
                this.BZ.addView(this.Cb);
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
            throw new UnsupportedOperationException("onCreateView not allowed on MapViewDelegate");
        }
        
        @Override
        public void onDestroy() {
            try {
                this.Ca.onDestroy();
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onDestroyView() {
            throw new UnsupportedOperationException("onDestroyView not allowed on MapViewDelegate");
        }
        
        @Override
        public void onInflate(final Activity activity, final Bundle bundle, final Bundle bundle2) {
            throw new UnsupportedOperationException("onInflate not allowed on MapViewDelegate");
        }
        
        @Override
        public void onLowMemory() {
            try {
                this.Ca.onLowMemory();
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onPause() {
            try {
                this.Ca.onPause();
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onResume() {
            try {
                this.Ca.onResume();
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onSaveInstanceState(final Bundle bundle) {
            try {
                this.Ca.onSaveInstanceState(bundle);
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    static class b extends a<MapView.a>
    {
        protected d<MapView.a> BX;
        private final ViewGroup Cc;
        private final GoogleMapOptions Cd;
        private final Context mContext;
        
        b(final ViewGroup cc, final Context mContext, final GoogleMapOptions cd) {
            this.Cc = cc;
            this.mContext = mContext;
            this.Cd = cd;
        }
        
        @Override
        protected void a(final d<MapView.a> bx) {
            this.BX = bx;
            this.ex();
        }
        
        public void ex() {
            if (this.BX == null || this.cZ() != null) {
                return;
            }
            try {
                this.BX.a(new MapView.a(this.Cc, q.u(this.mContext).a(c.h(this.mContext), this.Cd)));
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
            catch (GooglePlayServicesNotAvailableException ex2) {}
        }
    }
}
