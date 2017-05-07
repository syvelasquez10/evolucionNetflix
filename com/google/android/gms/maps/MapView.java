// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.internal.u;
import com.google.android.gms.dynamic.f;
import android.app.Activity;
import android.view.LayoutInflater;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.internal.fq;
import android.view.View;
import com.google.android.gms.maps.internal.IMapViewDelegate;
import com.google.android.gms.dynamic.LifecycleDelegate;
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
    private GoogleMap RT;
    private final b RW;
    
    public MapView(final Context context) {
        super(context);
        this.RW = new b((ViewGroup)this, context, null);
    }
    
    public MapView(final Context context, final AttributeSet set) {
        super(context, set);
        this.RW = new b((ViewGroup)this, context, GoogleMapOptions.createFromAttributes(context, set));
    }
    
    public MapView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.RW = new b((ViewGroup)this, context, GoogleMapOptions.createFromAttributes(context, set));
    }
    
    public MapView(final Context context, final GoogleMapOptions googleMapOptions) {
        super(context);
        this.RW = new b((ViewGroup)this, context, googleMapOptions);
    }
    
    public final GoogleMap getMap() {
        if (this.RT != null) {
            return this.RT;
        }
        this.RW.ip();
        if (this.RW.fW() == null) {
            return null;
        }
        try {
            return this.RT = new GoogleMap(this.RW.fW().iq().getMap());
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final void onCreate(final Bundle bundle) {
        this.RW.onCreate(bundle);
        if (this.RW.fW() == null) {
            final b rw = this.RW;
            com.google.android.gms.dynamic.a.b(this);
        }
    }
    
    public final void onDestroy() {
        this.RW.onDestroy();
    }
    
    public final void onLowMemory() {
        this.RW.onLowMemory();
    }
    
    public final void onPause() {
        this.RW.onPause();
    }
    
    public final void onResume() {
        this.RW.onResume();
    }
    
    public final void onSaveInstanceState(final Bundle bundle) {
        this.RW.onSaveInstanceState(bundle);
    }
    
    static class a implements LifecycleDelegate
    {
        private final ViewGroup RX;
        private final IMapViewDelegate RY;
        private View RZ;
        
        public a(final ViewGroup viewGroup, final IMapViewDelegate mapViewDelegate) {
            this.RY = fq.f(mapViewDelegate);
            this.RX = fq.f(viewGroup);
        }
        
        public IMapViewDelegate iq() {
            return this.RY;
        }
        
        @Override
        public void onCreate(final Bundle bundle) {
            try {
                this.RY.onCreate(bundle);
                this.RZ = e.d(this.RY.getView());
                this.RX.removeAllViews();
                this.RX.addView(this.RZ);
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
                this.RY.onDestroy();
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
                this.RY.onLowMemory();
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onPause() {
            try {
                this.RY.onPause();
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onResume() {
            try {
                this.RY.onResume();
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onSaveInstanceState(final Bundle bundle) {
            try {
                this.RY.onSaveInstanceState(bundle);
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onStart() {
        }
        
        @Override
        public void onStop() {
        }
    }
    
    static class b extends a<MapView.a>
    {
        protected f<MapView.a> RV;
        private final ViewGroup Sa;
        private final GoogleMapOptions Sb;
        private final Context mContext;
        
        b(final ViewGroup sa, final Context mContext, final GoogleMapOptions sb) {
            this.Sa = sa;
            this.mContext = mContext;
            this.Sb = sb;
        }
        
        @Override
        protected void a(final f<MapView.a> rv) {
            this.RV = rv;
            this.ip();
        }
        
        public void ip() {
            if (this.RV == null || this.fW() != null) {
                return;
            }
            try {
                this.RV.a(new MapView.a(this.Sa, u.A(this.mContext).a(e.h(this.mContext), this.Sb)));
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
            catch (GooglePlayServicesNotAvailableException ex2) {}
        }
    }
}
