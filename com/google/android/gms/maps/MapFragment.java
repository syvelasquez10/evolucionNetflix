// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.internal.q;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.a;
import com.google.android.gms.dynamic.c;
import com.google.android.gms.maps.internal.p;
import com.google.android.gms.internal.eg;
import com.google.android.gms.dynamic.LifecycleDelegate;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.app.Activity;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import android.os.RemoteException;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.internal.IMapFragmentDelegate;
import android.os.Parcelable;
import android.os.Bundle;
import android.app.Fragment;

public class MapFragment extends Fragment
{
    private final b BT;
    private GoogleMap BU;
    
    public MapFragment() {
        this.BT = new b(this);
    }
    
    public static MapFragment newInstance() {
        return new MapFragment();
    }
    
    public static MapFragment newInstance(final GoogleMapOptions googleMapOptions) {
        final MapFragment mapFragment = new MapFragment();
        final Bundle arguments = new Bundle();
        arguments.putParcelable("MapOptions", (Parcelable)googleMapOptions);
        mapFragment.setArguments(arguments);
        return mapFragment;
    }
    
    protected IMapFragmentDelegate ew() {
        this.BT.ex();
        if (this.BT.cZ() == null) {
            return null;
        }
        return this.BT.cZ().ew();
    }
    
    public final GoogleMap getMap() {
        final IMapFragmentDelegate ew = this.ew();
        if (ew != null) {
            try {
                final IGoogleMapDelegate map = ew.getMap();
                if (map != null) {
                    if (this.BU == null || this.BU.en().asBinder() != map.asBinder()) {
                        this.BU = new GoogleMap(map);
                    }
                    return this.BU;
                }
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        return null;
    }
    
    public void onActivityCreated(final Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(MapFragment.class.getClassLoader());
        }
        super.onActivityCreated(bundle);
    }
    
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        this.BT.setActivity(activity);
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.BT.onCreate(bundle);
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        return this.BT.onCreateView(layoutInflater, viewGroup, bundle);
    }
    
    public void onDestroy() {
        this.BT.onDestroy();
        super.onDestroy();
    }
    
    public void onDestroyView() {
        this.BT.onDestroyView();
        super.onDestroyView();
    }
    
    public void onInflate(final Activity activity, final AttributeSet set, final Bundle bundle) {
        super.onInflate(activity, set, bundle);
        this.BT.setActivity(activity);
        final GoogleMapOptions fromAttributes = GoogleMapOptions.createFromAttributes((Context)activity, set);
        final Bundle bundle2 = new Bundle();
        bundle2.putParcelable("MapOptions", (Parcelable)fromAttributes);
        this.BT.onInflate(activity, bundle2, bundle);
    }
    
    public void onLowMemory() {
        this.BT.onLowMemory();
        super.onLowMemory();
    }
    
    public void onPause() {
        this.BT.onPause();
        super.onPause();
    }
    
    public void onResume() {
        super.onResume();
        this.BT.onResume();
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(MapFragment.class.getClassLoader());
        }
        super.onSaveInstanceState(bundle);
        this.BT.onSaveInstanceState(bundle);
    }
    
    public void setArguments(final Bundle arguments) {
        super.setArguments(arguments);
    }
    
    static class a implements LifecycleDelegate
    {
        private final Fragment BV;
        private final IMapFragmentDelegate BW;
        
        public a(final Fragment fragment, final IMapFragmentDelegate mapFragmentDelegate) {
            this.BW = eg.f(mapFragmentDelegate);
            this.BV = eg.f(fragment);
        }
        
        public IMapFragmentDelegate ew() {
            return this.BW;
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
                    arguments = this.BV.getArguments();
                    if (arguments != null && arguments.containsKey("MapOptions")) {
                        p.a(bundle, "MapOptions", arguments.getParcelable("MapOptions"));
                    }
                    this.BW.onCreate(bundle);
                }
                catch (RemoteException ex) {
                    throw new RuntimeRemoteException(ex);
                }
            }
        }
        
        @Override
        public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
            try {
                return c.b(this.BW.onCreateView(c.h(layoutInflater), c.h(viewGroup), bundle));
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onDestroy() {
            try {
                this.BW.onDestroy();
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onDestroyView() {
            try {
                this.BW.onDestroyView();
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onInflate(final Activity activity, final Bundle bundle, final Bundle bundle2) {
            final GoogleMapOptions googleMapOptions = (GoogleMapOptions)bundle.getParcelable("MapOptions");
            try {
                this.BW.onInflate(c.h(activity), googleMapOptions, bundle2);
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onLowMemory() {
            try {
                this.BW.onLowMemory();
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onPause() {
            try {
                this.BW.onPause();
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onResume() {
            try {
                this.BW.onResume();
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onSaveInstanceState(final Bundle bundle) {
            try {
                this.BW.onSaveInstanceState(bundle);
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    static class b extends a<MapFragment.a>
    {
        private final Fragment BV;
        protected d<MapFragment.a> BX;
        private Activity gs;
        
        b(final Fragment bv) {
            this.BV = bv;
        }
        
        private void setActivity(final Activity gs) {
            this.gs = gs;
            this.ex();
        }
        
        @Override
        protected void a(final d<MapFragment.a> bx) {
            this.BX = bx;
            this.ex();
        }
        
        public void ex() {
            if (this.gs == null || this.BX == null || this.cZ() != null) {
                return;
            }
            try {
                MapsInitializer.initialize((Context)this.gs);
                this.BX.a(new MapFragment.a(this.BV, q.u((Context)this.gs).f(c.h(this.gs))));
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
            catch (GooglePlayServicesNotAvailableException ex2) {}
        }
    }
}
