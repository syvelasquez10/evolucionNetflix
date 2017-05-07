// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.internal.u;
import com.google.android.gms.dynamic.f;
import com.google.android.gms.dynamic.a;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.maps.internal.t;
import com.google.android.gms.internal.fq;
import com.google.android.gms.dynamic.LifecycleDelegate;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.app.Activity;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.IMapFragmentDelegate;
import android.os.RemoteException;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import android.os.Parcelable;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public class SupportMapFragment extends Fragment
{
    private GoogleMap RT;
    private final b Sw;
    
    public SupportMapFragment() {
        this.Sw = new b(this);
    }
    
    public static SupportMapFragment newInstance() {
        return new SupportMapFragment();
    }
    
    public static SupportMapFragment newInstance(final GoogleMapOptions googleMapOptions) {
        final SupportMapFragment supportMapFragment = new SupportMapFragment();
        final Bundle arguments = new Bundle();
        arguments.putParcelable("MapOptions", (Parcelable)googleMapOptions);
        supportMapFragment.setArguments(arguments);
        return supportMapFragment;
    }
    
    public final GoogleMap getMap() {
        final IMapFragmentDelegate io = this.io();
        if (io != null) {
            try {
                final IGoogleMapDelegate map = io.getMap();
                if (map != null) {
                    if (this.RT == null || this.RT.if().asBinder() != map.asBinder()) {
                        this.RT = new GoogleMap(map);
                    }
                    return this.RT;
                }
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        return null;
    }
    
    protected IMapFragmentDelegate io() {
        this.Sw.ip();
        if (this.Sw.fW() == null) {
            return null;
        }
        return this.Sw.fW().io();
    }
    
    @Override
    public void onActivityCreated(final Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(SupportMapFragment.class.getClassLoader());
        }
        super.onActivityCreated(bundle);
    }
    
    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        this.Sw.setActivity(activity);
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.Sw.onCreate(bundle);
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        return this.Sw.onCreateView(layoutInflater, viewGroup, bundle);
    }
    
    @Override
    public void onDestroy() {
        this.Sw.onDestroy();
        super.onDestroy();
    }
    
    @Override
    public void onDestroyView() {
        this.Sw.onDestroyView();
        super.onDestroyView();
    }
    
    @Override
    public void onInflate(final Activity activity, final AttributeSet set, final Bundle bundle) {
        super.onInflate(activity, set, bundle);
        this.Sw.setActivity(activity);
        final GoogleMapOptions fromAttributes = GoogleMapOptions.createFromAttributes((Context)activity, set);
        final Bundle bundle2 = new Bundle();
        bundle2.putParcelable("MapOptions", (Parcelable)fromAttributes);
        this.Sw.onInflate(activity, bundle2, bundle);
    }
    
    @Override
    public void onLowMemory() {
        this.Sw.onLowMemory();
        super.onLowMemory();
    }
    
    @Override
    public void onPause() {
        this.Sw.onPause();
        super.onPause();
    }
    
    @Override
    public void onResume() {
        super.onResume();
        this.Sw.onResume();
    }
    
    @Override
    public void onSaveInstanceState(final Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(SupportMapFragment.class.getClassLoader());
        }
        super.onSaveInstanceState(bundle);
        this.Sw.onSaveInstanceState(bundle);
    }
    
    @Override
    public void setArguments(final Bundle arguments) {
        super.setArguments(arguments);
    }
    
    static class a implements LifecycleDelegate
    {
        private final Fragment Hz;
        private final IMapFragmentDelegate RU;
        
        public a(final Fragment fragment, final IMapFragmentDelegate mapFragmentDelegate) {
            this.RU = fq.f(mapFragmentDelegate);
            this.Hz = fq.f(fragment);
        }
        
        public IMapFragmentDelegate io() {
            return this.RU;
        }
        
        @Override
        public void onCreate(Bundle arguments) {
            Bundle bundle = arguments;
            Label_0014: {
                if (arguments != null) {
                    break Label_0014;
                }
                try {
                    bundle = new Bundle();
                    arguments = this.Hz.getArguments();
                    if (arguments != null && arguments.containsKey("MapOptions")) {
                        t.a(bundle, "MapOptions", arguments.getParcelable("MapOptions"));
                    }
                    this.RU.onCreate(bundle);
                }
                catch (RemoteException ex) {
                    throw new RuntimeRemoteException(ex);
                }
            }
        }
        
        @Override
        public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
            try {
                return e.d(this.RU.onCreateView(e.h(layoutInflater), e.h(viewGroup), bundle));
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onDestroy() {
            try {
                this.RU.onDestroy();
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onDestroyView() {
            try {
                this.RU.onDestroyView();
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onInflate(final Activity activity, final Bundle bundle, final Bundle bundle2) {
            final GoogleMapOptions googleMapOptions = (GoogleMapOptions)bundle.getParcelable("MapOptions");
            try {
                this.RU.onInflate(e.h(activity), googleMapOptions, bundle2);
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onLowMemory() {
            try {
                this.RU.onLowMemory();
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onPause() {
            try {
                this.RU.onPause();
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onResume() {
            try {
                this.RU.onResume();
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onSaveInstanceState(final Bundle bundle) {
            try {
                this.RU.onSaveInstanceState(bundle);
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
    
    static class b extends a<SupportMapFragment.a>
    {
        private final Fragment Hz;
        protected f<SupportMapFragment.a> RV;
        private Activity nS;
        
        b(final Fragment hz) {
            this.Hz = hz;
        }
        
        private void setActivity(final Activity ns) {
            this.nS = ns;
            this.ip();
        }
        
        @Override
        protected void a(final f<SupportMapFragment.a> rv) {
            this.RV = rv;
            this.ip();
        }
        
        public void ip() {
            if (this.nS == null || this.RV == null || this.fW() != null) {
                return;
            }
            try {
                MapsInitializer.initialize((Context)this.nS);
                this.RV.a(new SupportMapFragment.a(this.Hz, u.A((Context)this.nS).h(e.h(this.nS))));
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
            catch (GooglePlayServicesNotAvailableException ex2) {}
        }
    }
}
