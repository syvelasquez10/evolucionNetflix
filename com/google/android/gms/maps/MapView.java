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
import com.google.android.gms.common.internal.n;
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
    private GoogleMap aiG;
    private final b aiJ;
    
    public MapView(final Context context) {
        super(context);
        this.aiJ = new b((ViewGroup)this, context, null);
    }
    
    public MapView(final Context context, final AttributeSet set) {
        super(context, set);
        this.aiJ = new b((ViewGroup)this, context, GoogleMapOptions.createFromAttributes(context, set));
    }
    
    public MapView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.aiJ = new b((ViewGroup)this, context, GoogleMapOptions.createFromAttributes(context, set));
    }
    
    public MapView(final Context context, final GoogleMapOptions googleMapOptions) {
        super(context);
        this.aiJ = new b((ViewGroup)this, context, googleMapOptions);
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
            final b aiJ = this.aiJ;
            com.google.android.gms.dynamic.a.b(this);
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
    
    static class a implements LifecycleDelegate
    {
        private final ViewGroup aiK;
        private final IMapViewDelegate aiL;
        private View aiM;
        
        public a(final ViewGroup viewGroup, final IMapViewDelegate mapViewDelegate) {
            this.aiL = n.i(mapViewDelegate);
            this.aiK = n.i(viewGroup);
        }
        
        public IMapViewDelegate mz() {
            return this.aiL;
        }
        
        @Override
        public void onCreate(final Bundle bundle) {
            try {
                this.aiL.onCreate(bundle);
                this.aiM = e.f(this.aiL.getView());
                this.aiK.removeAllViews();
                this.aiK.addView(this.aiM);
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
                this.aiL.onDestroy();
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
                this.aiL.onLowMemory();
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onPause() {
            try {
                this.aiL.onPause();
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onResume() {
            try {
                this.aiL.onResume();
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onSaveInstanceState(final Bundle bundle) {
            try {
                this.aiL.onSaveInstanceState(bundle);
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
        protected f<MapView.a> aiI;
        private final ViewGroup aiN;
        private final GoogleMapOptions aiO;
        private final Context mContext;
        
        b(final ViewGroup aiN, final Context mContext, final GoogleMapOptions aiO) {
            this.aiN = aiN;
            this.mContext = mContext;
            this.aiO = aiO;
        }
        
        @Override
        protected void a(final f<MapView.a> aiI) {
            this.aiI = aiI;
            this.my();
        }
        
        public void my() {
            if (this.aiI == null || this.it() != null) {
                return;
            }
            try {
                this.aiI.a(new MapView.a(this.aiN, u.R(this.mContext).a(e.k(this.mContext), this.aiO)));
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
            catch (GooglePlayServicesNotAvailableException ex2) {}
        }
    }
}
