// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.internal.u;
import android.content.Context;
import com.google.android.gms.dynamic.f;
import com.google.android.gms.dynamic.a;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.maps.internal.t;
import com.google.android.gms.internal.fq;
import com.google.android.gms.dynamic.LifecycleDelegate;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.app.Activity;
import com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate;
import com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate;
import android.os.RemoteException;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import android.os.Parcelable;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public class SupportStreetViewPanoramaFragment extends Fragment
{
    private StreetViewPanorama Sj;
    private final b Sx;
    
    public SupportStreetViewPanoramaFragment() {
        this.Sx = new b(this);
    }
    
    public static SupportStreetViewPanoramaFragment newInstance() {
        return new SupportStreetViewPanoramaFragment();
    }
    
    public static SupportStreetViewPanoramaFragment newInstance(final StreetViewPanoramaOptions streetViewPanoramaOptions) {
        final SupportStreetViewPanoramaFragment supportStreetViewPanoramaFragment = new SupportStreetViewPanoramaFragment();
        final Bundle arguments = new Bundle();
        arguments.putParcelable("StreetViewPanoramaOptions", (Parcelable)streetViewPanoramaOptions);
        supportStreetViewPanoramaFragment.setArguments(arguments);
        return supportStreetViewPanoramaFragment;
    }
    
    public final StreetViewPanorama getStreetViewPanorama() {
        final IStreetViewPanoramaFragmentDelegate is = this.is();
        if (is != null) {
            try {
                final IStreetViewPanoramaDelegate streetViewPanorama = is.getStreetViewPanorama();
                if (streetViewPanorama != null) {
                    if (this.Sj == null || this.Sj.ir().asBinder() != streetViewPanorama.asBinder()) {
                        this.Sj = new StreetViewPanorama(streetViewPanorama);
                    }
                    return this.Sj;
                }
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        return null;
    }
    
    protected IStreetViewPanoramaFragmentDelegate is() {
        this.Sx.ip();
        if (this.Sx.fW() == null) {
            return null;
        }
        return this.Sx.fW().is();
    }
    
    @Override
    public void onActivityCreated(final Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(SupportStreetViewPanoramaFragment.class.getClassLoader());
        }
        super.onActivityCreated(bundle);
    }
    
    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        this.Sx.setActivity(activity);
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.Sx.onCreate(bundle);
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        return this.Sx.onCreateView(layoutInflater, viewGroup, bundle);
    }
    
    @Override
    public void onDestroy() {
        this.Sx.onDestroy();
        super.onDestroy();
    }
    
    @Override
    public void onDestroyView() {
        this.Sx.onDestroyView();
        super.onDestroyView();
    }
    
    @Override
    public void onInflate(final Activity activity, final AttributeSet set, final Bundle bundle) {
        super.onInflate(activity, set, bundle);
        this.Sx.setActivity(activity);
        this.Sx.onInflate(activity, new Bundle(), bundle);
    }
    
    @Override
    public void onLowMemory() {
        this.Sx.onLowMemory();
        super.onLowMemory();
    }
    
    @Override
    public void onPause() {
        this.Sx.onPause();
        super.onPause();
    }
    
    @Override
    public void onResume() {
        super.onResume();
        this.Sx.onResume();
    }
    
    @Override
    public void onSaveInstanceState(final Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(SupportStreetViewPanoramaFragment.class.getClassLoader());
        }
        super.onSaveInstanceState(bundle);
        this.Sx.onSaveInstanceState(bundle);
    }
    
    @Override
    public void setArguments(final Bundle arguments) {
        super.setArguments(arguments);
    }
    
    static class a implements LifecycleDelegate
    {
        private final Fragment Hz;
        private final IStreetViewPanoramaFragmentDelegate Sk;
        
        public a(final Fragment fragment, final IStreetViewPanoramaFragmentDelegate streetViewPanoramaFragmentDelegate) {
            this.Sk = fq.f(streetViewPanoramaFragmentDelegate);
            this.Hz = fq.f(fragment);
        }
        
        public IStreetViewPanoramaFragmentDelegate is() {
            return this.Sk;
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
                    if (arguments != null && arguments.containsKey("StreetViewPanoramaOptions")) {
                        t.a(bundle, "StreetViewPanoramaOptions", arguments.getParcelable("StreetViewPanoramaOptions"));
                    }
                    this.Sk.onCreate(bundle);
                }
                catch (RemoteException ex) {
                    throw new RuntimeRemoteException(ex);
                }
            }
        }
        
        @Override
        public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
            try {
                return e.d(this.Sk.onCreateView(e.h(layoutInflater), e.h(viewGroup), bundle));
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onDestroy() {
            try {
                this.Sk.onDestroy();
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onDestroyView() {
            try {
                this.Sk.onDestroyView();
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onInflate(final Activity activity, final Bundle bundle, final Bundle bundle2) {
            try {
                this.Sk.onInflate(e.h(activity), null, bundle2);
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onLowMemory() {
            try {
                this.Sk.onLowMemory();
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onPause() {
            try {
                this.Sk.onPause();
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onResume() {
            try {
                this.Sk.onResume();
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
        
        @Override
        public void onSaveInstanceState(final Bundle bundle) {
            try {
                this.Sk.onSaveInstanceState(bundle);
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
    
    static class b extends a<SupportStreetViewPanoramaFragment.a>
    {
        private final Fragment Hz;
        protected f<SupportStreetViewPanoramaFragment.a> RV;
        private Activity nS;
        
        b(final Fragment hz) {
            this.Hz = hz;
        }
        
        private void setActivity(final Activity ns) {
            this.nS = ns;
            this.ip();
        }
        
        @Override
        protected void a(final f<SupportStreetViewPanoramaFragment.a> rv) {
            this.RV = rv;
            this.ip();
        }
        
        public void ip() {
            if (this.nS == null || this.RV == null || this.fW() != null) {
                return;
            }
            try {
                MapsInitializer.initialize((Context)this.nS);
                this.RV.a(new SupportStreetViewPanoramaFragment.a(this.Hz, u.A((Context)this.nS).i(e.h(this.nS))));
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
            catch (GooglePlayServicesNotAvailableException ex2) {}
        }
    }
}
