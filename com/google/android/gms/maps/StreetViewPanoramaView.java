// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import android.app.Activity;
import android.view.LayoutInflater;
import com.google.android.gms.internal.fq;
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
    private StreetViewPanorama Sj;
    private final a Ss;
    
    public StreetViewPanoramaView(final Context context) {
        super(context);
        this.Ss = new a((ViewGroup)this, context, null);
    }
    
    public StreetViewPanoramaView(final Context context, final AttributeSet set) {
        super(context, set);
        this.Ss = new a((ViewGroup)this, context, null);
    }
    
    public StreetViewPanoramaView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.Ss = new a((ViewGroup)this, context, null);
    }
    
    public StreetViewPanoramaView(final Context context, final StreetViewPanoramaOptions streetViewPanoramaOptions) {
        super(context);
        this.Ss = new a((ViewGroup)this, context, streetViewPanoramaOptions);
    }
    
    public final StreetViewPanorama getStreetViewPanorama() {
        if (this.Sj != null) {
            return this.Sj;
        }
        this.Ss.ip();
        if (this.Ss.fW() == null) {
            return null;
        }
        try {
            return this.Sj = new StreetViewPanorama(this.Ss.fW().iw().getStreetViewPanorama());
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final void onCreate(final Bundle bundle) {
        this.Ss.onCreate(bundle);
        if (this.Ss.fW() == null) {
            final a ss = this.Ss;
            com.google.android.gms.dynamic.a.b(this);
        }
    }
    
    public final void onDestroy() {
        this.Ss.onDestroy();
    }
    
    public final void onLowMemory() {
        this.Ss.onLowMemory();
    }
    
    public final void onPause() {
        this.Ss.onPause();
    }
    
    public final void onResume() {
        this.Ss.onResume();
    }
    
    public final void onSaveInstanceState(final Bundle bundle) {
        this.Ss.onSaveInstanceState(bundle);
    }
    
    static class a extends com.google.android.gms.dynamic.a<b>
    {
        protected f<b> RV;
        private final ViewGroup Sa;
        private final StreetViewPanoramaOptions St;
        private final Context mContext;
        
        a(final ViewGroup sa, final Context mContext, final StreetViewPanoramaOptions st) {
            this.Sa = sa;
            this.mContext = mContext;
            this.St = st;
        }
        
        @Override
        protected void a(final f<b> rv) {
            this.RV = rv;
            this.ip();
        }
        
        public void ip() {
            if (this.RV == null || this.fW() != null) {
                return;
            }
            try {
                this.RV.a(new b(this.Sa, u.A(this.mContext).a(e.h(this.mContext), this.St)));
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
            catch (GooglePlayServicesNotAvailableException ex2) {}
        }
    }
    
    static class b implements LifecycleDelegate
    {
        private final ViewGroup RX;
        private final IStreetViewPanoramaViewDelegate Su;
        private View Sv;
        
        public b(final ViewGroup viewGroup, final IStreetViewPanoramaViewDelegate streetViewPanoramaViewDelegate) {
            this.Su = fq.f(streetViewPanoramaViewDelegate);
            this.RX = fq.f(viewGroup);
        }
        
        public IStreetViewPanoramaViewDelegate iw() {
            return this.Su;
        }
        
        @Override
        public void onCreate(final Bundle bundle) {
            try {
                this.Su.onCreate(bundle);
                this.Sv = e.d(this.Su.getView());
                this.RX.removeAllViews();
                this.RX.addView(this.Sv);
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
                this.Su.onDestroy();
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
                this.Su.onLowMemory();
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onPause() {
            try {
                this.Su.onPause();
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onResume() {
            try {
                this.Su.onResume();
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onSaveInstanceState(final Bundle bundle) {
            try {
                this.Su.onSaveInstanceState(bundle);
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
