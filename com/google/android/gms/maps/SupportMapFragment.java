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
import com.google.android.gms.common.internal.n;
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
    private GoogleMap aiG;
    private final b ajj;
    
    public SupportMapFragment() {
        this.ajj = new b(this);
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
        final IMapFragmentDelegate mx = this.mx();
        if (mx != null) {
            try {
                final IGoogleMapDelegate map = mx.getMap();
                if (map != null) {
                    if (this.aiG == null || this.aiG.mo().asBinder() != map.asBinder()) {
                        this.aiG = new GoogleMap(map);
                    }
                    return this.aiG;
                }
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        return null;
    }
    
    protected IMapFragmentDelegate mx() {
        this.ajj.my();
        if (this.ajj.it() == null) {
            return null;
        }
        return this.ajj.it().mx();
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
        this.ajj.setActivity(activity);
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.ajj.onCreate(bundle);
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        return this.ajj.onCreateView(layoutInflater, viewGroup, bundle);
    }
    
    @Override
    public void onDestroy() {
        this.ajj.onDestroy();
        super.onDestroy();
    }
    
    @Override
    public void onDestroyView() {
        this.ajj.onDestroyView();
        super.onDestroyView();
    }
    
    @Override
    public void onInflate(final Activity activity, final AttributeSet set, final Bundle bundle) {
        super.onInflate(activity, set, bundle);
        this.ajj.setActivity(activity);
        final GoogleMapOptions fromAttributes = GoogleMapOptions.createFromAttributes((Context)activity, set);
        final Bundle bundle2 = new Bundle();
        bundle2.putParcelable("MapOptions", (Parcelable)fromAttributes);
        this.ajj.onInflate(activity, bundle2, bundle);
    }
    
    @Override
    public void onLowMemory() {
        this.ajj.onLowMemory();
        super.onLowMemory();
    }
    
    @Override
    public void onPause() {
        this.ajj.onPause();
        super.onPause();
    }
    
    @Override
    public void onResume() {
        super.onResume();
        this.ajj.onResume();
    }
    
    @Override
    public void onSaveInstanceState(final Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(SupportMapFragment.class.getClassLoader());
        }
        super.onSaveInstanceState(bundle);
        this.ajj.onSaveInstanceState(bundle);
    }
    
    @Override
    public void setArguments(final Bundle arguments) {
        super.setArguments(arguments);
    }
    
    static class a implements LifecycleDelegate
    {
        private final Fragment Ll;
        private final IMapFragmentDelegate aiH;
        
        public a(final Fragment fragment, final IMapFragmentDelegate mapFragmentDelegate) {
            this.aiH = n.i(mapFragmentDelegate);
            this.Ll = n.i(fragment);
        }
        
        public IMapFragmentDelegate mx() {
            return this.aiH;
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
                    arguments = this.Ll.getArguments();
                    if (arguments != null && arguments.containsKey("MapOptions")) {
                        t.a(bundle, "MapOptions", arguments.getParcelable("MapOptions"));
                    }
                    this.aiH.onCreate(bundle);
                }
                catch (RemoteException ex) {
                    throw new RuntimeRemoteException(ex);
                }
            }
        }
        
        @Override
        public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
            try {
                return e.f(this.aiH.onCreateView(e.k(layoutInflater), e.k(viewGroup), bundle));
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onDestroy() {
            try {
                this.aiH.onDestroy();
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onDestroyView() {
            try {
                this.aiH.onDestroyView();
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onInflate(final Activity activity, final Bundle bundle, final Bundle bundle2) {
            final GoogleMapOptions googleMapOptions = (GoogleMapOptions)bundle.getParcelable("MapOptions");
            try {
                this.aiH.onInflate(e.k(activity), googleMapOptions, bundle2);
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onLowMemory() {
            try {
                this.aiH.onLowMemory();
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onPause() {
            try {
                this.aiH.onPause();
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onResume() {
            try {
                this.aiH.onResume();
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onSaveInstanceState(final Bundle bundle) {
            try {
                this.aiH.onSaveInstanceState(bundle);
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
        private final Fragment Ll;
        protected f<SupportMapFragment.a> aiI;
        private Activity nr;
        
        b(final Fragment ll) {
            this.Ll = ll;
        }
        
        private void setActivity(final Activity nr) {
            this.nr = nr;
            this.my();
        }
        
        @Override
        protected void a(final f<SupportMapFragment.a> aiI) {
            this.aiI = aiI;
            this.my();
        }
        
        public void my() {
            if (this.nr == null || this.aiI == null || this.it() != null) {
                return;
            }
            try {
                MapsInitializer.initialize((Context)this.nr);
                this.aiI.a(new SupportMapFragment.a(this.Ll, u.R((Context)this.nr).j(e.k(this.nr))));
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
            catch (GooglePlayServicesNotAvailableException ex2) {}
        }
    }
}
