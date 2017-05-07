// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import android.app.Activity;
import android.view.LayoutInflater;
import com.google.android.gms.common.internal.n;
import android.view.View;
import com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate;
import com.google.android.gms.dynamic.LifecycleDelegate;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.maps.internal.u;
import com.google.android.gms.dynamic.f;
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
    private final a ajf;
    
    public StreetViewPanoramaView(final Context context) {
        super(context);
        this.ajf = new a((ViewGroup)this, context, null);
    }
    
    public StreetViewPanoramaView(final Context context, final AttributeSet set) {
        super(context, set);
        this.ajf = new a((ViewGroup)this, context, null);
    }
    
    public StreetViewPanoramaView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.ajf = new a((ViewGroup)this, context, null);
    }
    
    public StreetViewPanoramaView(final Context context, final StreetViewPanoramaOptions streetViewPanoramaOptions) {
        super(context);
        this.ajf = new a((ViewGroup)this, context, streetViewPanoramaOptions);
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
            final a ajf = this.ajf;
            com.google.android.gms.dynamic.a.b(this);
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
    
    static class a extends com.google.android.gms.dynamic.a<b>
    {
        protected f<b> aiI;
        private final ViewGroup aiN;
        private final StreetViewPanoramaOptions ajg;
        private final Context mContext;
        
        a(final ViewGroup aiN, final Context mContext, final StreetViewPanoramaOptions ajg) {
            this.aiN = aiN;
            this.mContext = mContext;
            this.ajg = ajg;
        }
        
        @Override
        protected void a(final f<b> aiI) {
            this.aiI = aiI;
            this.my();
        }
        
        public void my() {
            if (this.aiI == null || this.it() != null) {
                return;
            }
            try {
                this.aiI.a(new b(this.aiN, u.R(this.mContext).a(e.k(this.mContext), this.ajg)));
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
            catch (GooglePlayServicesNotAvailableException ex2) {}
        }
    }
    
    static class b implements LifecycleDelegate
    {
        private final ViewGroup aiK;
        private final IStreetViewPanoramaViewDelegate ajh;
        private View aji;
        
        public b(final ViewGroup viewGroup, final IStreetViewPanoramaViewDelegate streetViewPanoramaViewDelegate) {
            this.ajh = n.i(streetViewPanoramaViewDelegate);
            this.aiK = n.i(viewGroup);
        }
        
        public IStreetViewPanoramaViewDelegate mF() {
            return this.ajh;
        }
        
        @Override
        public void onCreate(final Bundle bundle) {
            try {
                this.ajh.onCreate(bundle);
                this.aji = e.f(this.ajh.getView());
                this.aiK.removeAllViews();
                this.aiK.addView(this.aji);
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
            throw new UnsupportedOperationException("onCreateView not allowed on StreetViewPanoramaViewDelegate");
        }
        
        @Override
        public void onDestroy() {
            try {
                this.ajh.onDestroy();
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onDestroyView() {
            throw new UnsupportedOperationException("onDestroyView not allowed on StreetViewPanoramaViewDelegate");
        }
        
        @Override
        public void onInflate(final Activity activity, final Bundle bundle, final Bundle bundle2) {
            throw new UnsupportedOperationException("onInflate not allowed on StreetViewPanoramaViewDelegate");
        }
        
        @Override
        public void onLowMemory() {
            try {
                this.ajh.onLowMemory();
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onPause() {
            try {
                this.ajh.onPause();
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onResume() {
            try {
                this.ajh.onResume();
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onSaveInstanceState(final Bundle bundle) {
            try {
                this.ajh.onSaveInstanceState(bundle);
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
}
